package com.example.liberatingdelta_kotlin1.pl_relations.the_cities

import com.example.liberatingdelta_kotlin1.basic_classes.Chapter
import com.example.liberatingdelta_kotlin1.basic_classes.cityPt
import com.example.liberatingdelta_kotlin1.pl_relations.the_chapters.chapter_1
import com.example.liberatingdelta_kotlin1.pl_relations.the_chapters.chapter_2

class chipper_towne : cityPt(
    "Chipper Towne", 0, 0, arrayOf<Chapter>(chapter_1(), chapter_2()),
    "Veneland","One of the most bustling cities in the region. The city that Lithe first starts in.",
    1
)