package com.example.liberatingdelta_kotlin1.basic_classes

class stats_object
//eva+vola are in 100s... 564 == 5.64% == .0564.. max will always be 10% == 1000 == .1
//dont know why it says theres a max? i hope that isnt implemented somewhere.... there is no max other than 100% == 10000
//PERCENT
//if you're looking for the list that used to have the string types, use the one under Characters instead.
    (
    var attackA: Int, //brute strength                                                      VALUE
    var health: Int, //the health points amt                                                VALUE
    var volatility: Int, //how likely the character is to increase atk/magic output abruptly
                        //smaller % means less likely to produce magic                      PRECENT
    var evasiveA: Int, //how likely the character can miss a physical atk                   PRECENT
    var evasiveM: Int, //how likely the character can miss a magic atk                      PERCENT
    var aDefense: Int, //the affectiveness of a physical attack                             PERCENT
    var fireDefense: Int, //the affectiveness of a fire magic attack                        PERCENT
    var waterDefense: Int, //the affectiveness of a water magic attack                      PERCENT
    var landDefense: Int, //the affectiveness of a land magic attack                        PERCENT
    var airDefense: Int //the affectiveness of an air magic attack                          PERCENT
)
{

    fun addStats(stats: stats_object) {
        attackA = attackA + stats.attackA
        health = health + stats.health
        volatility = volatility + stats.volatility
        evasiveA = evasiveA + stats.evasiveA
        evasiveM = evasiveM + stats.evasiveM
        aDefense = aDefense + stats.aDefense
        fireDefense = fireDefense + stats.fireDefense
        waterDefense = waterDefense + stats.waterDefense
        landDefense = landDefense + stats.landDefense
        airDefense = airDefense + stats.airDefense
    }

    fun subtractStats(stats: stats_object) {
        attackA = attackA - stats.attackA
        health = health - stats.health
        volatility = volatility - stats.volatility
        evasiveA = evasiveA - stats.evasiveA
        evasiveM = evasiveM - stats.evasiveM
        aDefense = aDefense - stats.aDefense
        fireDefense = fireDefense - stats.fireDefense
        waterDefense = waterDefense - stats.waterDefense
        landDefense = landDefense - stats.landDefense
        airDefense = airDefense - stats.airDefense
    }

    fun setStats(stats: stats_object) {
        attackA = stats.attackA
        health = stats.health
        volatility = stats.volatility
        evasiveA = stats.evasiveA
        evasiveM = stats.evasiveM
        aDefense = stats.aDefense
        fireDefense = stats.fireDefense
        waterDefense = stats.waterDefense
        landDefense = stats.landDefense
        airDefense = stats.airDefense
    }

    override fun toString(): String {
        return "stats"
    }

}