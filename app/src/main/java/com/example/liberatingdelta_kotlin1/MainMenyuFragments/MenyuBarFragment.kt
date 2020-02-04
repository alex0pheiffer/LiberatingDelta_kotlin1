package com.example.liberatingdelta_kotlin1.MainMenyuFragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.liberatingdelta_kotlin1.R
import kotlinx.android.synthetic.main.fragment_menyu_bar.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class MenyuBarFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private var listener: menyuBarListener? = null

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
        fun newInstance(param1: String, param2: String) =
            MenyuBarFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
