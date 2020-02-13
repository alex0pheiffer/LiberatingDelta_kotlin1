package com.example.liberatingdelta_kotlin1.MainMenyuFragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.example.liberatingdelta_kotlin1.R
import com.example.liberatingdelta_kotlin1.basic_classes.Deck
import com.example.liberatingdelta_kotlin1.basic_classes.PL
import com.example.liberatingdelta_kotlin1.databinding.FragmentCharViewStatsBinding
import com.example.liberatingdelta_kotlin1.databinding.FragmentDeckViewBinding
import com.example.liberatingdelta_kotlin1.pl_relations.PL_VendingMachine

private const val PlayerLevel = "PlayerLevel"
private const val ARG_PARAM2 = "param2"

class DeckViewFragment : Fragment(), DeckViewBarFragment.deckViewBarListener, updateAllPL, deployArrowsInterface {
    private var pl: Int = 0
    private lateinit var this_pl: PL
    private var param2: String? = null

    //this needs to become a deck scrollling object later..
    //private lateinit var selectedDeck

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
        val binding = DataBindingUtil.inflate<FragmentDeckViewBinding>(inflater,R.layout.fragment_deck_view, container, false)
        //todo if a deck is pressed in the recycler, pull up the deckbar
        //if scrolled, remove the deckbar
        //if char is changed, remove the deckbar
        return binding.root
    }

    override fun deletePressed(deck: Deck) {

    }

    override fun viewPressed(deck: Deck) {

    }

    override fun showValid(deck: Deck) {

    }

    override fun lemmeupdatethatpl(pl:Int) {
        this.pl = pl
        this_pl = PL_VendingMachine.getPL(pl)
    }

    fun grabCUR_PL(): PL {
        //todo
        //return mListner.grabCUR_PL()
        return PL_VendingMachine.getPL(0)
    }

    /*
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }

    override fun updateDB_mmc(mainCharacter: main_character) {
        mListener?.updateDB_mmc(mainCharacter)
    }
    */


    override fun hasEmpty(): Boolean {
        return true
    }

    companion object {
        @JvmStatic
        fun newInstance(pl: Int, param2: String) =
            DeckViewFragment().apply {
                arguments = Bundle().apply {
                    putInt(PlayerLevel, pl)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
