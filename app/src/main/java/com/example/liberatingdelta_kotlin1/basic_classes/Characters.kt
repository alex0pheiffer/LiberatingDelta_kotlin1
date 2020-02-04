package com.example.liberatingdelta_kotlin1.basic_classes

abstract class Characters(
    val name: String,
    val description_str: Array<String>?,
    val description_pl: Array<Int>?,
    val gender: String, //Female or Male or Neither
    val age: Int,
    val height: Int, //in centimeters
    val isHuman: Boolean,
    val magicalAffinity: Int,
    val strength: Int,
    val charType: String,
    val magicType: String?,
    var charImgMain: Int?
)
{
    //THERE IS NO LONGER A STATIC CHARACTER.
    //if you would like to create a non-fighting character, please directly insantiate a Characters object

    companion object {
        final val characterTypes = arrayOf(
            "Human",    //0
            "DragonA",
            "DragonB",  //2
            "DragonC",
            "DragonD",  //4
            "DragonE",
            "DragonF",  //6
            "DragonG",
            "DragonH",  //8
            "DragonI",
            "DragonJ",  //10
            "DragonK",
            "DragonL",  //12
            "DragonM",
            "DragonN",  //14
            "DragonO",
            "DragonP",  //16
            "Mage",
            "Witch",    //18
            "Animal"
        )
        final val magicTypes =
            arrayOf("None", "Fire", "Water", "Land", "Air", "???")
    }

}