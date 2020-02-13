package com.example.liberatingdelta_kotlin1.pl_relations.the_chapters

import com.example.liberatingdelta_kotlin1.basic_classes.Chapter
import com.example.liberatingdelta_kotlin1.pl_relations.phases_all.phases_dialog.ch1_1_phase
import com.example.liberatingdelta_kotlin1.basic_classes.phase_objects

class chapter_1  //This chapter is ALL dialog.
    : Chapter(
    "Beginning_pt1",
    1,
    "Veneland",
    "Chipper Towne",
    0,
    arrayOf<phase_objects>(ch1_1_phase())
)