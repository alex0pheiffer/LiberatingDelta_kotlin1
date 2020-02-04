package com.example.liberatingdelta_kotlin1.db_files.adapter_classes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.liberatingdelta_kotlin1.db_files.User_Inventory
import com.example.liberatingdelta_kotlin1.R

class inventory_adapter(context: Context?) :
    RecyclerView.Adapter<inventory_adapter.dataViewHolder>() {
    inner class dataViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val dataItemView: TextView

        init {
            dataItemView = itemView.findViewById(R.id.textView)
        }
    }

    private val mInflater: LayoutInflater
    private var lData //Cached copy of words
            : List<User_Inventory>? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): dataViewHolder {
        val itemView: View =
            mInflater.inflate(R.layout.db_recycle_item, parent, false)
        return dataViewHolder(
            itemView
        )
    }

    override fun onBindViewHolder(
        holder: dataViewHolder,
        position: Int
    ) {
        if (lData != null) {
            holder.dataItemView.text = lData!![position].name
        } else { // covers the case of data not being ready yet
            holder.dataItemView.text = "No Data"
        }
    }

    fun setlData(vals: List<User_Inventory>?) {
        lData = vals
        notifyDataSetChanged()
    }

    // getItemCount() is called many times, and when it is first called,
// mWords has not been updated (means intially, it's null, and we can't return null
    override fun getItemCount(): Int {
        return if (lData != null) {
            lData!!.size
        } else {
            0
        }
    }

    init {
        mInflater = LayoutInflater.from(context)
    }
}