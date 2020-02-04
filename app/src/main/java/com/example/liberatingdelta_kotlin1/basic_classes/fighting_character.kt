package com.example.liberatingdelta_kotlin1.basic_classes

abstract class fighting_character(
    nom: String,
    descriptstr: Array<String>?,
    descriptpl: Array<Int>?,
    gender: String,
    age: Int,
    height: Int,
    human: Boolean,
    magicalAff: Int,
    strength: Int,
    charType: String,
    magicType: String?,
    charImgMain: Int?,
    val fighting_img: Int,
    var weapon: Weapon?,
    var item: inventI?,
    val deck: Deck?,
    var stats: stats_object
) : Characters(
    nom,
    descriptstr,
    descriptpl,
    gender,
    age,
    height,
    human,
    magicalAff,
    strength,
    charType,
    magicType,
    charImgMain
)
{
    //please remember that if you want to create a character object, it is better to create a separate class for a specific level....

    val weaponStats: stats_object?
        get() = weapon?.stats ?: null

    val itemStats: stats_object?
        get() = item?.stats ?: null

    val itemUseForBuff: Boolean
        get() = item?.use_for_buff ?: false


}