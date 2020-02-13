package com.example.liberatingdelta_kotlin1.basic_classes

import com.example.liberatingdelta_kotlin1.basic_classes.ExtraQuest
import com.example.liberatingdelta_kotlin1.basic_classes.cityPt
import com.example.liberatingdelta_kotlin1.basic_classes.main_character
import com.example.liberatingdelta_kotlin1.basic_classes.the_MCs.Katherine
import com.example.liberatingdelta_kotlin1.pl_relations.PL_VendingMachine
import java.util.*

abstract class PL(
    val PL: Int,
    val chapter: Chapter,
    val all_regions: Array<regions>,                //remember that REGIONS WILL CHANGE AS THE GEOGRAPHY CHANGES
    val all_MC: Array<main_character>,              //the mc's accessible/that are shown for this pl...there may be 2 vivians etc
    val all_characters: Array<Characters>,          //all the characters that'll appear in this pl
    val description: String,                        //for journal page
    //are unlocked weapons/cards/items needed for a PL construction?
    val unlocked_weapons: Array<Weapon?>,           //new stuff
    val unlocked_cards: Array<Card?>,               //new stuff
    val unlocked_items: Array<inventI?>,            //new stuff
    val unlocked_eq: Array<ExtraQuest?>,            //EQ THAT CAN BE ACCESSED NOW THAT WERE NOT EXCESSABLE LAST PL
    val unlocked_region: regions?,                  //REGION THAT CAN BE ACCESSED NOW THAT WERE NOT EXCESSABLE LAST PL
    val unlocked_city: Array<cityPt?>,              //CITYPT THAT CAN BE ACCESSED NOW THAT WERE NOT EXCESSABLE LAST PL
    //why are these in the PL not CH? because you can still access eq etc but we want the plotline's restrictions to persist outside of the present CH
    val banned_weapons: Array<Weapon?>,             //cannot use this weapon...
    val banned_cards: Array<Card?>,                 //cannot use this card...
    val banned_items: Array<inventI?>,              //cannot use this item...
    val banned_eq: Array<ExtraQuest?>,              //cannot enter this EQ...
    val banned_region: Array<String?>,              //cannot enter this region...
    val banned_city: Array<String?>,                //cannot enter this city...
    val banned_char: Array<String?>,                //cannot play as this char...
    //must be checked in the prebattle screen
    val required_weapon: Array<Weapon?>,            //MUST USE
    val required_card: Array<Card?>,                //MUST USE
    val required_item: Array<inventI?>,             //MUST USE
    val required_char: Array<Characters>            //MUST USE
) {

    //if you're looking for the ch getter, access the region, the citypt, and then find the ch you seek in that array list...

    //LET US REMEMBER: THERE IS NO '0' PL; IF PL IS USED AS AN INDEX, YOU SUBTRACT 1

    fun getRegion(value: Int): regions {
        if (value > all_regions.size) {
            return all_regions[value]
        }
        else return all_regions[0]
    }

    fun getRegion(name: String): regions? {
        var the_region = -1
        for (i in all_regions.indices) {
            if (all_regions[i].nom == name) the_region = i
            break
        }
        return if (the_region == -1) {
            null
        } else {
            all_regions[the_region]
        }
    }

    fun getCityPt(name: String?, region: regions): cityPt? {
        var the_city: cityPt? = null
        for (i in 0 until region.cityAmt) {
            if (region.getCityPt(i).nom.equals(name)) {
                the_city = region.getCityPt(i)
                break
            }
        }
        return the_city
    }

    private fun getChar(name: String): Int? {
        var the_character: Int? = null
        for (n in all_MC.indices) {
            if (name == all_MC[n].name) {
                the_character = n
                break
            }
        }
        return the_character
    }

    fun getCharacter(name: String): main_character? {
        var index: Int? = getChar(name)
        if (index != null) return all_MC[index]
        else return null
    }

    fun getCharacter(index: Int): main_character? {
        if (index > all_MC.size) return all_MC[index]
        else return null
    }

    fun rotateCharacter(cur_char: String, isUp: Boolean, hasEmpty: Boolean): main_character?
    {
        //hasEmpty --> tells if we can return an "empty" character
        var index: Int
        if (cur_char.compareTo("empty") == 0) {
            index = if (isUp) all_MC.size - 1 else 0
        }
        else {
            index = getChar(cur_char) ?: 0 //could be a source of problems...
            if (isUp) {
                if (index == 0) {
                    index = if (hasEmpty) return null else all_MC.size - 1
                } else {
                    index--
                }
            }
            else {
                index++
                if (index >= all_MC.size) {
                    index = if (hasEmpty) return null else 0
                }
            }
        }
        return all_MC[index]
    }

    //public cityPt[] getAllCities() { return all_cities;}
    //returns all cities in that region...regardless of PL
    fun getAllCitiesRegion(name: String): Array<cityPt?> {
        val region = getRegion(name)
        val list: Array<cityPt?> = arrayOfNulls<cityPt>(region?.cities?.size ?: 0)
        if (region == null) return list
        for (i in list.indices) {
            list[i] = region.getCityPt(i)
        }
        return list
    }

    //the cities that can be see on screen (you can press the icon, doesn't mean you can play any of the chapters... (aka city may be banned)
    //ONLY SHOWS PL-accessable cities
    fun getCities(name: String): Array<cityPt?> {
        val region = getRegion(name)
        val list: Array<cityPt?> = arrayOfNulls<cityPt>(region?.cities?.size ?: 0)
        if (region == null) return list
        for (i in list.indices) {
            if (PL >= region.getCityPt(i).plDiscover) list[i] = region.getCityPt(i)
        }
        return list
    }

    private fun region2index(region: regions): Int {
        var index = -1
        for (n in all_regions.indices) {
            if (region.nom == all_regions[n].nom) {
                index = n
                break
            }
        }
        return index
    }

    fun addEXP(name: String, amt: Int) {
        var chara = getCharacter(name)
        if (chara != null) {
            chara.total_exp += amt
            PL_VendingMachine.updateLevel(chara)
        }
    }

    fun setAllEXP(katie: Int, delta: Int, vivian: Int, gamma: Int) {
        for (n in all_MC) {
            if (n.name.equals("Katherine")) n.total_exp = katie
            else if (n.name.equals("Vivian")) n.total_exp = vivian
            else if (n.name.equals("Delta")) n.total_exp = delta
            else if (n.name.equals("Gamma")) n.total_exp = gamma
            PL_VendingMachine.updateLevel(n)
        }
    }

}