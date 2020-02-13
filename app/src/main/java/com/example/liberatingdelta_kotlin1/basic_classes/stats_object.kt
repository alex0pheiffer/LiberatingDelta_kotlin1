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

    var attackM: Int? = null
        private set
    private var magicAff: Int? = null
    private var charStr: Int? = null
    val beforeAtkM: (charMagicAff: Int, charStr: Int) -> Unit = {
        charMagicAff: Int, charStr: Int ->
        this.magicAff = charMagicAff //these should be a value between 0 and 100, inclusive
        this.charStr = charStr
        if (this.magicAff!! < 0 || this.magicAff!! > 100 || charStr!! < 0 || charStr!! > 100) throw RuntimeException("Cannot compute attackM...the magicalAff: "+magicAff.toString()+" /strength: "+charStr.toString()+" is invalid.")
        attackM = ((charMagicAff / charStr.toDouble())*attackA).toInt()
    }
    val afterAtkM: () -> Unit = {
        attackM = ((magicAff!! / charStr!!.toDouble())*attackA).toInt()
        System.out.println("updating the atkM !")
    }
    fun setAttackM(charMagicAff: Int, charStr: Int): Unit {
        beforeAtkM(charMagicAff, charStr)
    }

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
        if (attackM != null) afterAtkM()
        System.out.println("Pl eas e make sure that the atkM is updating if it's been created...")
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
        if (attackM != null) afterAtkM()
        System.out.println("Pl eas e make sure that the atkM is updating if it's been created...")
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
        if (attackM != null) afterAtkM()
        System.out.println("Pl eas e make sure that the atkM is updating if it's been created...")
    }

    operator fun plus(stats: stats_object): stats_object {
        var newstaty = stats_object(0,0,0,0,0,0,0,0,0,0)
        newstaty.attackA = this.attackA+stats.attackA
        newstaty.health = this.health+stats.health
        newstaty.volatility = this.volatility+stats.volatility
        newstaty.evasiveA = this.evasiveA+stats.evasiveA
        newstaty.evasiveM = this.evasiveM+stats.evasiveM
        newstaty.aDefense = this.aDefense+stats.aDefense
        newstaty.fireDefense = this.fireDefense+stats.fireDefense
        newstaty.waterDefense = this.waterDefense+stats.waterDefense
        newstaty.landDefense = this.landDefense+stats.landDefense
        newstaty.airDefense = this.airDefense+stats.airDefense
        return newstaty
    }

    override fun toString(): String {
        return "stats"
    }

}