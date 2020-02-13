package com.example.liberatingdelta_kotlin1.basic_classes

abstract class main_character(
    nom: String,
    var description: Array<String>?,        //please modify this as the story progresses. Note that the upper level classes WILL NOT UPDATE their descriptions
    descriptpl: Array<Int>?,
    var greetings: Array<String>?,
    gender: String,
    age: Int,
    height: Int,
    human: Boolean,
    magicalAff: Int,
    strength: Int,
    charType: String,
    magicType: String?,
    characterImgDrawable: Int,
    fightImg: Int,
    weapon: Weapon?,
    item: inventI?,
    deck: Deck?,
    thestats: stats_object,
    var total_exp: Int = 0,
    var level: Int = 1
) : fighting_character(
    nom,
    description,
    descriptpl,
    gender,
    age,
    height,
    human,
    magicalAff,
    strength,
    charType,
    magicType,
    characterImgDrawable,
    fightImg,
    weapon,
    item,
    deck,
    thestats
) {

    var rankEXP = 0
    var venelandEXP = 0
    var pietasEXP = 0
    var stonesEXP = 0
    var hdrEXP = 0
    var region6EXP = 0
    var region7EXP = 0
    var region89EXP = 0
    var region10EXP = 0
    var region11EXP = 0
    var nebulaEXP = 0
    var region13EXP = 0
    var icecubeEXP = 0
    var rupesEXP = 0
    var petraEXP = 0
    var juslynEXP = 0
    var maceriaEXP = 0
    var northEXP = 0

    override fun toString(): String {
        return "main_character"
    }

}