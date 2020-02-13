package com.example.liberatingdelta_kotlin1.MainMenyuFragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.liberatingdelta_kotlin1.R

class CharacterArrowFragment : Fragment() {
    private var isUp = false
    private var hasEmpty = false
    private var mListener: onCharacterArrowFragmentInteraction? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            isUp = arguments!!.getBoolean(ORIENTATION)
            hasEmpty = arguments!!.getBoolean(HASEMPTY)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { // Inflate the layout for this fragment
        val view: View =
            inflater.inflate(R.layout.fragment_character_arrow, container, false)
        view.setOnClickListener {
            //add a pressed animation to lighten then redarken the icon
//outro mainmenyu2characters (move mainmenyu icons off)
//intro mainmenyu2characters (move characters icons on)
//AGAIN, NOT A NEW ACTIVITY! YOU ARE ADDING FRAGEMENTS
//((FragmentActivity)getActivity()).setCURRENT_LAYOUT("CHARACTERS_MENYU_LAYOUT");
            mListener!!.characterArrowPressed(isUp, hasEmpty)
        }
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mListener = parentFragment as onCharacterArrowFragmentInteraction?
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    interface onCharacterArrowFragmentInteraction {
        fun characterArrowPressed(isUp: Boolean, hasEmpty: Boolean)
    }

    var mTag = this.toString()
    override fun toString(): String {
        return this.javaClass.simpleName
    }

    companion object {
        private const val ORIENTATION = "orientation"
        private const val HASEMPTY = "hasempty"
        fun newInstance(isUp: Boolean, hasEmpty: Boolean): CharacterArrowFragment {
            val fragment = CharacterArrowFragment()
            val args = Bundle()
            args.putBoolean(ORIENTATION, isUp)
            args.putBoolean(HASEMPTY, hasEmpty)
            fragment.arguments = args
            return fragment
        }
    }
}