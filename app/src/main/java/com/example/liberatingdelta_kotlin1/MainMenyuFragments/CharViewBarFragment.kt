package com.example.liberatingdelta_kotlin1.MainMenyuFragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.liberatingdelta_kotlin1.R
import com.example.liberatingdelta_kotlin1.basic_classes.PL
import com.example.liberatingdelta_kotlin1.pl_relations.PL_VendingMachine
import kotlinx.android.synthetic.main.fragment_char_view_bar.view.*

private const val PlayerLevel = "pl"
private const val ARG_PARAM2 = "param2"

class CharViewBarFragment : Fragment(), updateAllPL{
    private var pl: Int = 0
    private lateinit var this_pl: PL
    private var param2: String? = null
    private var listener: charViewBarListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            pl = it.getInt(PlayerLevel)
            this_pl = PL_VendingMachine.getPL(pl)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewyy = inflater.inflate(R.layout.fragment_char_view_bar, container, false)

        viewyy.charViewBar_stats_btn.setOnClickListener{
            statsPressed(it)
        }
        viewyy.charViewBar_equip_btn.setOnClickListener{
            equipPressed(it)
        }
        viewyy.charViewBar_region_btn.setOnClickListener{
            regionPressed(it)
        }
        viewyy.charViewBar_rank_btn.setOnClickListener{
            rankPressed(it)
        }
        viewyy.charViewBar_info_btn.setOnClickListener{
            infoPressed(it)
        }

        return viewyy
    }


    fun statsPressed(view: View) {
        listener?.statsPressed(view)
    }
    fun equipPressed(view: View) {
        listener?.equipPressed(view)
    }
    fun regionPressed(view: View) {
        listener?.regionPressed(view)
    }
    fun rankPressed(view: View) {
        listener?.rankPressed(view)
    }
    fun infoPressed(view: View) {
        listener?.infoPressed(view)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = parentFragment as charViewBarListener?
        /*
        if (context is charViewBarListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement charViewBarListener")
        }
        */
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun lemmeupdatethatpl(pl:Int) {
        this.pl = pl
        this_pl = PL_VendingMachine.getPL(pl)
    }

    interface charViewBarListener {
        fun statsPressed(view: View)
        fun equipPressed(view: View)
        fun regionPressed(view: View)
        fun rankPressed(view: View)
        fun infoPressed(view: View)
    }

    companion object {
        @JvmStatic
        fun newInstance(pl: Int, param2: String) =
            CharViewBarFragment().apply {
                arguments = Bundle().apply {
                    putInt(PlayerLevel, pl)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
