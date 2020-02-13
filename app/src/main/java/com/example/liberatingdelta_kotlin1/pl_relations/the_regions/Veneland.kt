package com.example.liberatingdelta_kotlin1.pl_relations.the_regions

import com.example.liberatingdelta_kotlin1.R
import com.example.liberatingdelta_kotlin1.basic_classes.cityPt
import com.example.liberatingdelta_kotlin1.basic_classes.regions
import com.example.liberatingdelta_kotlin1.pl_relations.the_cities.chipper_towne
import com.example.liberatingdelta_kotlin1.pl_relations.the_cities.maleficere_mansion

class Veneland  //aka region1
    : regions(
    "Veneland",
    1,
    R.drawable.region_1_3_zoomed,
    arrayOf<cityPt>(maleficere_mansion(), chipper_towne())
)