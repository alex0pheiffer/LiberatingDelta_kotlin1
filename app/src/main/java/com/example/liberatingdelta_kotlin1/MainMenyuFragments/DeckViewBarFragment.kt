package com.example.liberatingdelta_kotlin1.MainMenyuFragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.liberatingdelta_kotlin1.R
import com.example.liberatingdelta_kotlin1.basic_classes.Deck
import com.example.liberatingdelta_kotlin1.basic_classes.PL
import com.example.liberatingdelta_kotlin1.pl_relations.PL_VendingMachine

private const val PlayerLevel = "PlayerLevel"
private const val ARG_PARAM2 = "param2"

class DeckViewBarFragment : Fragment(), updateAllPL {
    private var pl: Int = 0
    private lateinit var this_pl: PL
    private var param2: String? = null
    private var listener: deckViewBarListener? = null

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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_deck_view_bar, container, false)
    }

    fun deletePressed(deck: Deck) {
        listener?.deletePressed(deck)
    }

    fun viewPressed(deck: Deck) {
        listener?.deletePressed(deck)
    }

    fun showValid(deck: Deck) {
        listener?.deletePressed(deck)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is deckViewBarListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement deckViewBarListener")
        }
    }

    override fun lemmeupdatethatpl(pl:Int) {
        this.pl = pl
        this_pl = PL_VendingMachine.getPL(pl)
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface deckViewBarListener {
        fun deletePressed(deck: Deck)
        fun viewPressed(deck: Deck)
        fun showValid(deck: Deck)
    }

    companion object {
        @JvmStatic
        fun newInstance(pl: Int, param2: String) =
            DeckViewBarFragment().apply {
                arguments = Bundle().apply {
                    putInt(PlayerLevel, pl)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
