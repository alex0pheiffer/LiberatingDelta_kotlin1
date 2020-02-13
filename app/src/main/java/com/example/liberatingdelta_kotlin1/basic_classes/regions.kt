package com.example.liberatingdelta_kotlin1.basic_classes

abstract class regions(
    val nom: String,
    val regionNum: Int,
    var drawable_background: Int,
    val cities: Array<cityPt>
) {
    val cityAmt = cities.size

    fun getCityPt(index: Int): cityPt {
        return cities[index]
    }

    fun getCityPt(name: String): cityPt? {
        var city: cityPt? = null
        for (i in 0 until cityAmt) {
            if (name == cities[i].nom) city = cities[i]
        }
        return city
    }

    override fun toString(): String {
        return nom
    }

    init {
        //println(nom + " has " + cityAmt + " cities.")
    }
}