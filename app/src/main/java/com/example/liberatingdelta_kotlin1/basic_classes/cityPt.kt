package com.example.liberatingdelta_kotlin1.basic_classes

abstract class cityPt(
    val nom: String,
    val drawable_background: Int,
    val drawable_map_icon: Int,
    private val chapters: Array<Chapter>,
    val region: String,
    val description: String,
    val plDiscover: Int
) {
    val chapterAmt: Int

    fun getChapter(index: Int): Chapter {
        return chapters[index]
    }

    fun getChapter(name: String): Chapter? {
        var chapter: Chapter? = null
        for (i in 0 until chapterAmt) {
            if (name == chapters[i].nom) chapter = chapters[i]
        }
        return chapter
    }

    override fun toString(): String {
        return nom
    }

    init {
        chapterAmt = chapters.size
    }
}