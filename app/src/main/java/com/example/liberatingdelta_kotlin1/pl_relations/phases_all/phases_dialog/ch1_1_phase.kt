package com.example.liberatingdelta_kotlin1.pl_relations.phases_all.phases_dialog

import com.example.liberatingdelta_kotlin1.R
import com.example.liberatingdelta_kotlin1.basic_classes.Characters
import com.example.liberatingdelta_kotlin1.basic_classes.Phase

class ch1_1_phase : Phase(1, 0) {
    init {
        dialog =
            arrayOf(
                "Yeah this is the first dialog",
                "Then theres this",
                "and now this."
            )
        characters = arrayOf<Characters>()
        background =
            arrayOf<Int>(
                R.drawable.region_1_3_zoomed,
                R.drawable.region_1_3_zoomed,
                R.drawable.region_1_3_zoomed
            )
    }
}