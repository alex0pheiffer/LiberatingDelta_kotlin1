package com.example.liberatingdelta_kotlin1

import com.example.liberatingdelta_kotlin1.basic_classes.Card
import com.example.liberatingdelta_kotlin1.basic_classes.battle_character
import com.example.liberatingdelta_kotlin1.basic_classes.fighting_character
import java.util.*

class battleClass {
    var scanner = Scanner(System.`in`)
    var allCharacters: ArrayList<battle_character>
    var allies: ArrayList<battle_character>? = null
    var enemies: ArrayList<battle_character>? = null
    var isAlive = true
    var hasWon = false
    var character_it: battle_character? = null
    var chosenCard: Card? = null
    var chosenTargets: ArrayList<battle_character>? = null
    var maxTurns: Int? = null
    var turnCounter: Int = 0

    //in the order in which you'd like the characters to fight init (turns)
    constructor(characters: ArrayList<fighting_character?>, allyList: ArrayList<Boolean>)
    {
        allCharacters = ArrayList<battle_character>()
        allies = ArrayList<battle_character>()
        enemies = ArrayList<battle_character>()
        var curChar: battle_character
        for (i in characters.indices) {
            if (characters[i] == null) continue
            curChar = battle_character(
                characters[i]!!,
                i * (Math.random() * 100).toInt(),
                allyList[i]
            )
            allCharacters.add(curChar)
            if (allyList[i]) {
                allies!!.add(curChar)
            } else enemies!!.add(curChar)
        }
    }

    constructor(characters: ArrayList<fighting_character?>, initTime: ArrayList<Int>, allyList: ArrayList<Boolean>)
    {
        allCharacters = ArrayList<battle_character>()
        for (i in characters.indices) {
            if (characters[i] == null) continue
            allCharacters.add(battle_character(characters[i]!!, initTime[i]!!, allyList[i]!!))
        }
    }

    //returns whether the player won or not
    fun battle(): Boolean {
        chosenTargets = ArrayList<battle_character>()
        //setting up the battle...
        for (n in allCharacters.indices) {
            allCharacters[n].shuffleDeck()
            allCharacters[n].fillHand()
            System.out.println(
                "Character " + allCharacters[n].nom + ": \tHP: " + allCharacters[n].statsStatic.health
            )
            System.out.println(
                "AtkP: " + allCharacters[n].statsStatic.attackA + " DefA: " + allCharacters[n].statsStatic.aDefense
            )
        }
        while (isAlive && !hasWon && (turnCounter < maxTurns ?: turnCounter+1)) {
            //update current character
            character_it = allCharacters[0]
            chosenTargets!!.clear()
            System.out.println("It's now " + character_it?.nom + "'s turn!")
            character_it?.applyEffectTodo()
            character_it?.weightPoints = character_it?.weightPoints ?: -1 + 1
            //check if character is still alive
            if (!(character_it?.isDead ?: true )&& !((character_it?.turnSkip) ?: true)) {
                //character_it picks a card
                //character_it picks a target (or selection of targets)
                // card is executed for every selected target
                if (character_it?.isAlly ?: false) {
                    //if it's an ally, wait for the user to select a card and target...
                    chosenCard = null
                    var chosenTarget = false
                    while (chosenCard == null || !chosenTarget) { //wait for input (requires both a chosen Card and a Target)
                        println("Please choose a card to play: ")
                        for (c in 0 until character_it!!.getHand()) {
                            System.out.println("\t" + character_it!!.getHandCard(c))
                        }
                        println()
                        var switchUnhappy = true
                        var card: String? = ""
                        while (switchUnhappy) {
                            card = scanner.nextLine()
                            for (i in 0 until character_it!!.getHand()) {
                                if ((character_it!!.getHandCard(i)?.nom ?: "").equals(card)) {
                                    chosenCard = character_it!!.getHandCard(i)
                                }
                            }
                            if (chosenCard == null) { //the input was invalid
                                println("Invalid. Please input again...")
                            } else switchUnhappy = false
                        }
                        System.out.println("The card " + chosenCard?.nom + " takes " + chosenCard?.targetAmt + " targets.")
                        var moreTargets = 0
                        while (moreTargets < (chosenCard?.targetAmt ?: 0) && moreTargets < enemies!!.size) {
                            println("Who would you like to attack?")
                            if (moreTargets == 0) {
                                for (c in enemies!!.indices) {
                                    System.out.println(
                                        "\t" + enemies!![c].nom + " HP: " + enemies!![c].hp
                                    )
                                }
                            }
                            println()
                            switchUnhappy = true
                            var target: String? = ""
                            while (switchUnhappy) {
                                target = scanner.nextLine()
                                for (i in enemies!!.indices) {
                                    if (enemies!![i].nom.equals(target)) {
                                        chosenTargets!!.add(enemies!![i])
                                    }
                                }
                                if (chosenCard == null) { //the input was invalid
                                    println("Invalid. Please input again...")
                                }
                                else switchUnhappy = false
                            }
                            moreTargets++
                        }
                        chosenTarget = true
                    }
                }
                else { //if it's the computer, find the best card.
                    chosenCard = chooseFight()
                    //display the chosen card and what it does for a few seconds
                    println(character_it?.nom ?: "NULL" + " has played " + chosenCard?.nom + ".")
                }
                //the card is executed
                var deaths = 0
                for (i in chosenTargets!!.indices) {
                    println(chosenCard?.nom + " has been used on " + chosenTargets!![i].nom + "!")
                    if (character_it != null && character_it!!.useCard(chosenCard!!, chosenTargets!![i])) {
                        deaths++
                        //if the dead char is an ally
                        if (chosenTargets!![i].isAlly) {
                            updateAlive()
                        } else updateWon()
                        println(chosenTargets!![i].nom + " has died!")
                        println("isAlive = $isAlive")
                        println("hasWon = $hasWon")
                    }
                }
                //updating weight
                //if the target(s) died, (+2 weight) * (# of dead enemies)
                character_it?.weightPoints = character_it!!.weightPoints + 2 * deaths
                //( ? ) if card was <4 weight, +1 weight
                if (chosenCard?.weight ?: 5 < 4) character_it!!.weightPoints = character_it!!.weightPoints + 1
                character_it?.weightPoints = character_it!!.weightPoints - (chosenCard?.weight ?: 0)
            }
            //check if character is dead
            if (character_it?.isDead ?: false) { //remove the character from allCharacters
                if (character_it!!.isAlly) {
                    if (updateAlive()) {
                        allCharacters.removeAt(0)
                        allies!!.remove(character_it!!)
                    }
                } else {
                    if (!updateWon()) {
                        allCharacters.removeAt(0)
                        enemies!!.remove(character_it!!)
                    }
                }
            }
            //shift the timeToTurn character list
            val decreaseWait: Int = allCharacters[1].wait
            allCharacters[0].increaseWait(chosenCard?.wait ?: 0)
            allCharacters.add(allCharacters[0])
            allCharacters.removeAt(0)
            for (i in allCharacters.indices) {
                allCharacters[i].reduceWait(decreaseWait)
                //and move them on the screen
            }
            //increment # of turns taken
            turnCounter++
            //clear the target list
            chosenTargets!!.clear()
            //turnSkip is false
            character_it?.turnSkip = false
        }
        if (hasWon || (isAlive && maxTurns ?: turnCounter+1 <= turnCounter)) return true
        return false
    }

    //the computer decides which card is best to use.
    private fun chooseFight(): Card { //first, determine which would produce the best amount of damage.
        var maxMaxCard = 0
        var maxMaxDamage = 0
        var maxMaxTargets = ArrayList<Int?>()
        var cTargets = 0
        var dead = false
        //list of the index in the opponents list
        var maxCardDamage: Int
        for (c in 0 until (character_it?.getHand() ?: 0 )) {
            val opponents: ArrayList<battle_character> = ArrayList<battle_character>()
            for (b in allies!!.indices) {
                opponents.add(battle_character(allies!![b]))
            }
            println("opponent is testing their options....\n")
            println("opponents: ")
            for (i in opponents.indices) {
                System.out.println("\t" + opponents[i].nom)
            }
            val cTargetList = ArrayList<Int?>()
            cTargets = character_it?.getHandCard(c)?.targetAmt ?: 0
            maxCardDamage = 0
            println("test " + c + ": " + character_it?.getHandCard(c))
            println("requires $cTargets targets.")
            //the number of targets the card requires is greater than the number of targets available.
            if (cTargets >= opponents.size) { //no need to check different potential targets: automatically selects all.
                println("more targets req than available.")
                //test each target to see if anyone dies, and to find the max HP damage done
                for (i in opponents.indices) {
                    println("card used on " + opponents[i])
                    dead = character_it?.testCard(character_it!!.getHandCard(c)!!, opponents[i]) ?: false
                    if (dead) {
                        println("Opponent died.")
                        for (j in opponents.indices) {
                            chosenTargets!!.add(allies!![j])
                        }
                        return character_it!!.getHandCard(c)!!
                    }
                    //change maxDamage is this damage is greater
                    if (maxCardDamage < allies!![i].hp - opponents[i].hp) {
                        maxCardDamage = allies!![i].hp - opponents[i].hp
                    }
                }
                //update the target list for this card
                for (i in opponents.indices) {
                    cTargetList.add(i)
                }
            }
            else { //just loop through the opponents and see if anyone dies...
                println("targets required <= targets available")
                for (a in opponents.indices) {
                    println("card used on " + opponents[a])
                    dead = character_it?.testCard(character_it!!.getHandCard(c)!!, opponents[a]) ?: false
                    if (dead) {
                        println("Opponent died.")
                        //firstly, add the char that dies.
                        chosenTargets!!.add(allies!![a])
                        var j = 0
                        while (j < cTargets - 1) {
                            //dont wanna repeat the char we already put in
                            if (j == a) j++
                            //add another random character
                            chosenTargets!!.add(allies!![j])
                            j++
                        }
                        return character_it!!.getHandCard(c)!!
                    }
                    //change maxDamage is this damage is greater
                    if (maxCardDamage < allies!![a].hp - opponents[a].hp) {
                        maxCardDamage = allies!![a].hp - opponents[a].hp
                        //add the character to the list to be attacked
                        if (cTargetList.size < cTargets) {
                            cTargetList.add(a)
                        } else {
                            cTargetList.removeAt(0)
                            cTargetList.add(a)
                        }
                    } else if (cTargetList.size < cTargets) {
                        cTargetList.add(0, a)
                    }
                }
            }
            //is this max larger than maxMax?
            println("maxDmg: $maxCardDamage maxMaxDmg: $maxMaxDamage")
            if (maxCardDamage > maxMaxDamage) {
                println(character_it?.getHandCard(c).toString() + "'s damage is greater. It is the new saved maxCard.")
                maxMaxCard = c
                maxMaxDamage = maxCardDamage
                maxMaxTargets = cTargetList
            }
        }
        //otherwise... return the max damage card
        for (j in maxMaxTargets.indices) {
            chosenTargets!!.add(j, allies!![maxMaxTargets[j]!!])
        }
        return character_it!!.getHandCard(maxMaxCard)!!
    }

    //call if an ally dies
    fun updateAlive(): Boolean {
        for (i in allies!!.indices) {
            if (!allies!![i].isDead) {
                isAlive = true
                return true
            }
        }
        isAlive = false
        return false
    }

    fun updateWon(): Boolean {
        for (i in enemies!!.indices) {
            if (!enemies!![i].isDead) {
                hasWon = false
                return false
            }
        }
        hasWon = true
        return true
    }
}