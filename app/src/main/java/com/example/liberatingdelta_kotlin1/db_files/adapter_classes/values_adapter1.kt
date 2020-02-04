package com.example.liberatingdelta_kotlin1.db_files.adapter_classes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.liberatingdelta_kotlin1.db_files.User_Values
import com.example.liberatingdelta_kotlin1.R

class values_adapter(context: Context?) :
    RecyclerView.Adapter<values_adapter.dataViewHolder>() {
    inner class dataViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val dataItemView: TextView

        init {
            dataItemView = itemView.findViewById(R.id.textView)
        }
    }

    private val mInflater: LayoutInflater
    private var lData //Cached copy of words
            : List<User_Values>? = null

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
            val current = lData!![position]
            holder.dataItemView.text = current.username
            /*
            else if (lData.toString().equals(User_Characters.class.toString())) {
                User_Characters current = (User_Characters)(lData.get(position));
                holder.dataItemView.setText(current.getName()+" lvl:"+current.getLevel());
            }
            else if (lData.toString().equals(User_Cards.class.toString())) {
                User_Cards current = (User_Cards)(lData.get(position));
                holder.dataItemView.setText(current.getName() + " [" + current.getPosition()+"]");
            }
            else if (lData.toString().equals(User_Decks.class.toString())) {
                User_Decks current = (User_Decks)(lData.get(position));
                holder.dataItemView.setText(current.getName()+" crds:"+current.getLength());
            }
            else if (lData.toString().equals(User_Inventory.class.toString())) {
                User_Inventory current = (User_Inventory)(lData.get(position));
                holder.dataItemView.setText(current.getName());
            }
            else if (lData.toString().equals(User_EQPlayed.class.toString())) {
                User_EQPlayed current = (User_EQPlayed)(lData.get(position));
                holder.dataItemView.setText(current.getName()+" comp:"+current.getCompleted());
            }
            else {
                holder.dataItemView.setText("Err: Invalid Data Object");
            }
            */
        } else { // covers the case of data not being ready yet
            holder.dataItemView.text = "No Data"
        }
    }

    fun setlData(vals: List<User_Values>?) {
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