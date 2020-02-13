package com.example.liberatingdelta_kotlin1.pl_relations

import com.example.liberatingdelta_kotlin1.basic_classes.PL
import com.example.liberatingdelta_kotlin1.basic_classes.main_character
import com.example.liberatingdelta_kotlin1.pl_relations.playerLevel_1

object PL_VendingMachine {
    //The Ultimate Chapter List Complete with matching PL's

    //LEVEL FUNCTION : EXP(level)  y = x ^ (5/9)
    private val EXPERIENCE_EXPONENT = 5/9.0
    private val LEVEL_EXPONENT = 9/5.0
    private val MAX_LEVEL: Int = 100

    private val pl_1: PL = playerLevel_1()
    private val PL_List: Array<PL> = arrayOf<PL>(pl_1)
    fun getPL(pl: Int): PL {
        return PL_List[pl - 1]
    }

    //stuff regarding character levels
//
    fun getInitLevel(totalLvlExp: Int): Int {
        //var level = 1
        //val upperbound = MAX_LEVEL
        /*
        val upperboundExp = exp2next(upperbound.toDouble())
        if (totalLvlExp > upperboundExp) {
            throw RuntimeException("Cannot be greater than level 100")
        }
        if (totalLvlExp == 0) {
            return level
        }
        var stillGoing = true
        while (stillGoing) { //level is current
            totalExp = totalLvlExp - exp2next(level.toDouble())
            if (totalLvlExp < 0) {
                stillGoing = false
            } else if (totalLvlExp == 0) {
                level++
                stillGoing = false
            } else {
                level++
            }
        }
        */
        var level: Int = Math.pow(totalLvlExp.toDouble(), EXPERIENCE_EXPONENT).toInt()
        if (level > MAX_LEVEL) level = MAX_LEVEL
        return level
    }

    //tell me how much exp is from this level to the next
    fun exp2next(level: Double): Int {
        return (Math.pow(level, LEVEL_EXPONENT) - Math.pow(level-1, LEVEL_EXPONENT)).toInt()
    }


    //tell me the y coor of this x coor (the level)...how much exp is total
    fun exp2level(level: Double): Int {
        return Math.pow(level, LEVEL_EXPONENT).toInt()
    }

    fun updateLevel(mainCharacter: main_character) {
        while (exp2next(mainCharacter.level.toDouble()) < mainCharacter.total_exp - exp2level(mainCharacter.level.toDouble()) ) {
            mainCharacter.level = mainCharacter.level + 1
        }
    }
}