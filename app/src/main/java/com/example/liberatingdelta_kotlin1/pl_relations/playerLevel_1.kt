package com.example.liberatingdelta_kotlin1.pl_relations

import com.example.liberatingdelta_kotlin1.basic_classes.*
import com.example.liberatingdelta_kotlin1.basic_classes.the_MCs.Katherine
import com.example.liberatingdelta_kotlin1.basic_classes.the_MCs.Vivian
import com.example.liberatingdelta_kotlin1.pl_relations.the_chapters.chapter_1
import com.example.liberatingdelta_kotlin1.pl_relations.the_regions.GreatNorth
import com.example.liberatingdelta_kotlin1.pl_relations.the_regions.HDR
import com.example.liberatingdelta_kotlin1.pl_relations.the_regions.IceCube
import com.example.liberatingdelta_kotlin1.pl_relations.the_regions.Juslyn
import com.example.liberatingdelta_kotlin1.pl_relations.the_regions.MaceriaUnion
import com.example.liberatingdelta_kotlin1.pl_relations.the_regions.Nebula
import com.example.liberatingdelta_kotlin1.pl_relations.the_regions.Petrasepire
import com.example.liberatingdelta_kotlin1.pl_relations.the_regions.Pietas
import com.example.liberatingdelta_kotlin1.pl_relations.the_regions.Rupes
import com.example.liberatingdelta_kotlin1.pl_relations.the_regions.TheTossedStones
import com.example.liberatingdelta_kotlin1.pl_relations.the_regions.Veneland

class playerLevel_1: PL(
    1,
    chapter_1(),
    arrayOf<regions>(
        Veneland(), Pietas(), TheTossedStones(), HDR(),
        Nebula(), IceCube(), Rupes(), Petrasepire(),
        Juslyn(), MaceriaUnion(), GreatNorth()
    ),
    arrayOf<main_character>(Katherine(0), Vivian(0)),
    arrayOf<Characters>(Katherine(0)),
    "a test of sotrs",
    arrayOf<Weapon?>(null),
    arrayOf<Card?>(null),
    arrayOf<inventI?>(null),
    arrayOf<ExtraQuest?>(null),
    null,
    arrayOf<cityPt?>(null),
    arrayOf<Weapon?>(null),
    arrayOf<Card?>(null),
    arrayOf<inventI?>(null),
    arrayOf<ExtraQuest?>(null),
    arrayOf<String?>(null),
    arrayOf<String?>(null),
    arrayOf<String?>(null),
    arrayOf<Weapon?>(null),
    arrayOf<Card?>(null),
    arrayOf<inventI?>(null),
    arrayOf<Characters>(Katherine(0))
)