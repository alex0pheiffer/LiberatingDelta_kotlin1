package com.example.liberatingdelta_kotlin1.basic_classes

import com.example.liberatingdelta_kotlin1.battleClass

//for battle

abstract class BattlePhase(pl: Int, phase: Int) : phase_objects(pl, phase) {
    var allies: Array<fighting_character?>? = null
    var enemies: Array<fighting_character?>? = null
    var addAlliesStartTimes: Array<Int>? = null
    var addEnemiesStartTimes: Array<Int>? = null
    var goal: String? = null
    var background: Int? = null
    var maxTurns: Int? = null //is this total turns? or turns of a certain mc?

    override fun toString(): String {
        return "Phase ${super.pl} : ${super.phase}"
    }

    fun playBattle(): Boolean {
        if (allies == null || enemies == null) return false
        else {
            var allmyCharacters = ArrayList<fighting_character?>()
            var areAllies = ArrayList<Boolean>()
            var startTimes: ArrayList<Int>? = null
            if (addAlliesStartTimes != null && addEnemiesStartTimes != null) startTimes = ArrayList<Int>()
            for (n in allies!!.indices) {
                allmyCharacters.add(allies!![n])
                areAllies.add(true)
                startTimes?.add(addAlliesStartTimes!![n])
            }
            for (n in enemies!!.indices) {
                allmyCharacters.add(enemies!![n])
                areAllies.add(false)
                startTimes?.add(addEnemiesStartTimes!![n])
            }
            var thisBattle: battleClass
            if (startTimes != null) thisBattle = battleClass(allmyCharacters, startTimes, areAllies)
            else thisBattle = battleClass(allmyCharacters, areAllies)
            if (maxTurns != null) thisBattle.maxTurns = maxTurns
            thisBattle.battle()
            return true
        }
    }
}