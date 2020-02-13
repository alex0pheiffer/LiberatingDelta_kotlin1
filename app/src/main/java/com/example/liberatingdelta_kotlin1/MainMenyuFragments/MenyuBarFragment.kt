package com.example.liberatingdelta_kotlin1.MainMenyuFragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.liberatingdelta_kotlin1.R
import com.example.liberatingdelta_kotlin1.basic_classes.PL
import com.example.liberatingdelta_kotlin1.pl_relations.PL_VendingMachine
import kotlinx.android.synthetic.main.fragment_menyu_bar.view.*


private const val PlayerLevel = "PlayerLevel"
private const val ARG_PARAM2 = "param2"

class MenyuBarFragment : Fragment(), updateAllPL {
    private var pl: Int = 0
    private lateinit var this_pl: PL
    private var param2: String? = null
    private var listener: menyuBarListener? = null

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
        val viewyy = inflater.inflate(R.layout.fragment_menyu_bar, container, false)

        viewyy.menyu_settings_btn.setOnClickListener {

            Log.d("DEBUG","settingsPressed0")
            settingsPressed(it)
        }
        viewyy.menyu_characters_btn.setOnClickListener {
            Log.d("DEBUG","CharacterPressed0")
            characterPressed(it)
        }
        viewyy.menyu_journal_btn.setOnClickListener {
            journalPressed(it)
        }
        viewyy.menyu_decks_btn.setOnClickListener {
            deckPressed(it)
        }
        viewyy.menyu_map_btn.setOnClickListener {
            mapPressed(it)
        }

        return viewyy
    }

    fun settingsPressed(view: View) {
        listener?.settingsPressed(view)
        Log.d("DEBUG","settingsPressed1")
        Log.d("DEBUG",listener?.toString() ?: "null")
    }
    fun characterPressed(view: View) {
        Log.d("DEBUG","characterPressed1")
        Log.d("DEBUG",listener?.toString() ?: "null")
        listener?.characterPressed(view)
    }
    fun journalPressed(view: View) {
        listener?.journalPressed(view)
    }
    fun deckPressed(view: View) {
        listener?.deckPressed(view)
    }
    fun inventoryPressed(view: View) {
        listener?.inventoryPressed(view)
    }
    fun mapPressed(view: View) {
        listener?.mapPressed(view)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = parentFragment as menyuBarListener?
        /*
        if (context is menyuBarListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement menyuBarListener")
        }
        */
    }

    override fun lemmeupdatethatpl(pl:Int) {
        this.pl = pl
        this_pl = PL_VendingMachine.getPL(pl)
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface menyuBarListener {
        fun settingsPressed(view: View)
        fun characterPressed(view: View)
        fun journalPressed(view: View)
        fun deckPressed(view: View)
        fun inventoryPressed(view: View)
        fun mapPressed(view: View)
    }

    companion object {
        @JvmStatic
        fun newInstance(pl: Int, param2: String) =
            MenyuBarFragment().apply {
                arguments = Bundle().apply {
                    putInt(PlayerLevel, pl)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
