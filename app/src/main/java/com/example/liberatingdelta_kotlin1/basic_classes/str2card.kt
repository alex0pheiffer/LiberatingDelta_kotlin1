package com.example.liberatingdelta_kotlin1.basic_classes

import com.example.liberatingdelta_kotlin1.basic_classes.Cards.BoldTackle
import com.example.liberatingdelta_kotlin1.basic_classes.Cards.Distract
import com.example.liberatingdelta_kotlin1.basic_classes.Cards.RockToss
import com.example.liberatingdelta_kotlin1.basic_classes.Cards.Scare
import com.example.liberatingdelta_kotlin1.basic_classes.Cards.Shove
import com.example.liberatingdelta_kotlin1.basic_classes.Cards.SimpleDodge
import com.example.liberatingdelta_kotlin1.basic_classes.Cards.Splash
import com.example.liberatingdelta_kotlin1.basic_classes.Cards.StellaSpark
import com.example.liberatingdelta_kotlin1.basic_classes.Cards.Struggle
import com.example.liberatingdelta_kotlin1.basic_classes.Cards.TreeHide
import java.util.*

class str2card {

    //todo update this as you add more card classes
    private val cardClasses = arrayOf<Class<*>>(
        Distract::class.java,
        SimpleDodge::class.java,
        RockToss::class.java,
        Shove::class.java,
        Splash::class.java,
        Struggle::class.java,
        TreeHide::class.java,
        StellaSpark::class.java,
        BoldTackle::class.java,
        Scare::class.java
    )
    private val cardClassesNames: ArrayList<String>
    private var previousCard: Card?
    private fun alphabetizeClassCard() { //insertion sort
        var tempStr: String
        var tempCl: Class<*>
        for (i in cardClasses.indices) {
            for (j in i + 1 until cardClasses.size) {
                if (cardClassesNames[i].compareTo(cardClassesNames[j]) > 0) {
                    tempStr = cardClassesNames[i]
                    tempCl = cardClasses[i]
                    cardClassesNames[i] = cardClassesNames[j]
                    cardClasses[i] = cardClasses[j]
                    cardClassesNames[j] = tempStr
                    cardClasses[j] = tempCl
                }
            }
        }
    }

    fun getCard(str: String): Card {
        val card: Card
        var tempClass: Class<*>? = null
        tempClass = cardClasses[Collections.binarySearch(
            cardClassesNames,
            str
        )]
        card = try {
            tempClass.newInstance() as Card
        } catch (e: Exception) {
            throw RuntimeException("Class " + tempClass.name + " is not a valid card.")
        }
        previousCard = card
        return card
    }

    fun getPrevious(str: String): Card? {
        return if (str == previousCard.toString()) previousCard else throw RuntimeException(
            "given previous card " + str + " does not match str2card's previous card " + previousCard.toString()
        )
    }

    init {
        cardClassesNames = ArrayList()
        for (n in cardClasses) {
            cardClassesNames.add(n.simpleName)
        }
        alphabetizeClassCard()
        previousCard = null
    }
}