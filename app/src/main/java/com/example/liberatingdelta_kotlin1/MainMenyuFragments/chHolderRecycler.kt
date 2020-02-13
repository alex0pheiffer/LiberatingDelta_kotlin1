package com.example.liberatingdelta_kotlin1.MainMenyuFragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.liberatingdelta_kotlin1.R
import com.example.liberatingdelta_kotlin1.basic_classes.Chapter
import com.example.liberatingdelta_kotlin1.basic_classes.PL
import com.example.liberatingdelta_kotlin1.basic_classes.cityPt
import com.example.liberatingdelta_kotlin1.basic_classes.regions
import com.example.liberatingdelta_kotlin1.pl_relations.PL_VendingMachine
import java.util.*

class chHolderRecycler : Fragment(), updateAllPL {
    lateinit var this_region: regions
        private set
    lateinit var this_cityPt: cityPt
        private set
    private var pl: Int = 0
        private set
    private lateinit var this_pl: PL
    private val chapters: MutableList<Chapter> = ArrayList<Chapter>()
    private var mListener: onRegionChaptersSelectedListener? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            pl = arguments!!.getInt(PlayerLevel)
            this_pl = PL_VendingMachine.getPL(pl)
            this_region = this_pl.getRegion(arguments!!.getString(REGION)!!) ?: this_pl.getRegion(0)
            this_cityPt = this_region.getCityPt(arguments!!.getString(CITYPT)!!) ?: this_region.getCityPt(0)
            println("region: $this_region, city: $this_cityPt")
            System.out.println("setChapters+ " + this_cityPt.chapterAmt)
            for (i in 0 until this_cityPt.chapterAmt) {
                chapters.add(i, this_cityPt.getChapter(i))
            }
            //System.out.println("Chapters 1&2: "+chapters.get(0).getNom()+" "+chapters.get(1).getNom());
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.recycler_ch_holder, container, false)
        // Set the adapter
        if (view is RecyclerView) {
            val context = view.getContext()
            val recyclerView = view
            recyclerView.layoutManager = LinearLayoutManager(context)
            val adapter = chHolderRecyclerViewAdapter(chapters, mListener, this_pl)
            recyclerView.adapter = adapter
        }
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mListener = if (context is onRegionChaptersSelectedListener) {
            context
        } else {
            throw RuntimeException(
                context.toString()
                        + " must implement onRegionChaptersSelectedListener"
            )
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    fun setChapterFragmentCityPt(city: cityPt) {
        this_cityPt = city
        chapters.clear()
        for (i in 0 until city.chapterAmt) {
            chapters.add(i, this_cityPt.getChapter(i))
        }
    }

    override fun lemmeupdatethatpl(pl:Int) {
        this.pl = pl
        this_pl = PL_VendingMachine.getPL(pl)
    }

    interface onRegionChaptersSelectedListener {
        fun chapterSelected(region: regions?, chapter: Chapter?)
    }

    override fun toString(): String {
        return this.javaClass.simpleName
    }

    companion object {
        private const val REGION = "this_region"
        private const val CITYPT = "this_cityPt"
        private const val PlayerLevel = "pl"
        const val cURRENT_LAYOUT = "REGION_MAP_CHAPTER"
        fun newInstance(region: String, cityPt: String, pl: Int): chHolderRecycler
        {
            val fragment = chHolderRecycler()
            val args = Bundle()
            args.putString(REGION, region)
            args.putString(CITYPT, cityPt)
            args.putInt(PlayerLevel, pl)
            fragment.arguments = args
            return fragment
        }

    }
}