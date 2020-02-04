package com.example.liberatingdelta_kotlin1.MainMenyuFragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.liberatingdelta_kotlin1.R

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class DeckViewBarFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: deckViewBarListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
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
        fun newInstance(param1: String, param2: String) =
            DeckViewBarFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
