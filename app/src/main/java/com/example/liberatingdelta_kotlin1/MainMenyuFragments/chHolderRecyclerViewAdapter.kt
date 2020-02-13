package com.example.liberatingdelta_kotlin1.MainMenyuFragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.liberatingdelta_kotlin1.MainMenyuFragments.chHolderRecycler.onRegionChaptersSelectedListener
import com.example.liberatingdelta_kotlin1.R
import com.example.liberatingdelta_kotlin1.basic_classes.Chapter
import com.example.liberatingdelta_kotlin1.basic_classes.PL
import com.example.liberatingdelta_kotlin1.pl_relations.PL_VendingMachine

class chHolderRecyclerViewAdapter(
    items: List<Chapter>,
    listener: onRegionChaptersSelectedListener?,
    thisPL: PL
) : RecyclerView.Adapter<chHolderRecyclerViewAdapter.ViewHolder>(), updateAllPL {
    private val mChapters: List<Chapter>?
    private val mListener: onRegionChaptersSelectedListener?
    private var pl: Int
    private var this_pl: PL
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        println("onCreateViewHolder")
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_ch_holder, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        println("onBindViewHolder")
        if (mChapters != null) {
            println("setting up holder: $position")
            holder.mItem = mChapters[position]
            holder.plView.text = holder.plView.context.getString(R.string.chPLlabel, mChapters[position].coor_PL)
            holder.mContentView.setText(mChapters[position].nom)
            holder.mView.setOnClickListener {
                if (null != mListener) {
                    mListener.chapterSelected(this_pl.getRegion(holder.mItem.region), holder.mItem)
                }
            }
        }
        else {
            holder.plView.text = holder.plView.context.getString(R.string.NoChapters)
        }
    }

    override fun getItemCount(): Int {
        println("getting size: $mChapters")
        return mChapters!!.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val mView: View
        val plView: TextView
        val mContentView: TextView
        lateinit var mItem: Chapter
        val cURRENT_LAYOUT = "REGION_MAP_CHAPTER"

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }



        init {
            println("new ViewHolder")
            mView = view
            plView = view.findViewById<View>(R.id.item_number) as TextView
            mContentView = view.findViewById<View>(R.id.content) as TextView
        }
    }

    init {
        println("set mChapters: $items")
        //Thread.dumpStack();
        mChapters = items
        mListener = listener
        this_pl = thisPL
        pl = this_pl.PL
    }

    override fun lemmeupdatethatpl(pl:Int) {
        this.pl = pl
        this_pl = PL_VendingMachine.getPL(pl)
    }
}