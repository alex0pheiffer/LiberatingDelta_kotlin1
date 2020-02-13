package com.example.liberatingdelta_kotlin1.MainMenyuFragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController

import com.example.liberatingdelta_kotlin1.R
import com.example.liberatingdelta_kotlin1.basic_classes.PL
import com.example.liberatingdelta_kotlin1.basic_classes.main_character
import com.example.liberatingdelta_kotlin1.databinding.FragmentCharViewEquipBinding
import com.example.liberatingdelta_kotlin1.databinding.FragmentCharViewRankBinding
import com.example.liberatingdelta_kotlin1.pl_relations.PL_VendingMachine
import kotlinx.android.synthetic.main.fragment_char_view_bar.*
import kotlinx.android.synthetic.main.fragment_char_view_bar.view.*
import kotlinx.android.synthetic.main.fragment_up_button.*
import kotlinx.android.synthetic.main.fragment_up_button.view.*

private const val PlayerLevel = "PlayerLevel"
private const val ARG_PARAM2 = "param2"

class CharViewFragment_rank : Fragment(), characterViewInterfaceIn, CharViewBarFragment.charViewBarListener, updateAllPL, deployArrowsInterface {
    private var param2: String? = null
    private var mListener: characterViewInterfaceOut? = null

    private var pl: Int = 0
    private lateinit var this_pl: PL
    private lateinit var character: main_character //the current character

    private lateinit var headerName: TextView
    private lateinit var headerLevel: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            pl = it.getInt(PlayerLevel)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentCharViewRankBinding>(inflater,R.layout.fragment_char_view_rank, container, false)
        this_pl = mListener?.grabCUR_PL() ?: PL_VendingMachine.getPL(0)
        pl = this_pl.PL
        character = this_pl.getCharacter(mListener?.grabMMC() ?: "") ?: this_pl.getCharacter(0)!!
        headerName = binding.characterViewCharacterName
        headerLevel = binding.characterViewCharacterLevel
        fillHeader()
        return binding.root
    }

    fun fillHeader() {
        headerName.text = character.name
        headerLevel.text = "Lv. "+character.level
    }

    override fun statsPressed(it: View) {
        it.findNavController().navigate(R.id.action_charViewFragment_rank_to_charViewFragment)
    }
    override fun equipPressed(it: View) {
        it.findNavController().navigate(R.id.action_charViewFragment_rank_to_charViewFragment_equip)
    }
    override fun regionPressed(it: View) {
        it.findNavController().navigate(R.id.action_charViewFragment_rank_to_charviewFragment_region)
    }
    override fun rankPressed(it: View) {
        //nothing
    }
    override fun infoPressed(it: View) {
        it.findNavController().navigate(R.id.action_charViewFragment_rank_to_charViewFragment_info)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is characterViewInterfaceOut) {
            mListener = context
        } else {
            throw RuntimeException(context.toString() + " must implement characterViewInterfaceOut")
        }
    }

    /*
    fun onButtonPressed(uri: Uri) {
        //listener?.onFragmentInteraction(uri)
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
    */

    override fun lemmeupdatethatpl(pl:Int) {
        this.pl = pl
        this_pl = PL_VendingMachine.getPL(pl)
    }

    override fun hasEmpty(): Boolean {
        return false
    }

    override fun updateMMC(mainCharacter: main_character) {
        character = mainCharacter
    }

    companion object {
        @JvmStatic
        fun newInstance(pl: Int, param2: String) =
            CharViewFragment_rank().apply {
                arguments = Bundle().apply {
                    putInt(PlayerLevel, pl)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
