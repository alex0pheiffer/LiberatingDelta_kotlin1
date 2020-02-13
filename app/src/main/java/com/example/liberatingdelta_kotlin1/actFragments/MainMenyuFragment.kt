package com.example.liberatingdelta_kotlin1.actFragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.liberatingdelta_kotlin1.MainMenyuFragments.MMCFragment
import com.example.liberatingdelta_kotlin1.MainMenyuFragments.MenyuBarFragment
import com.example.liberatingdelta_kotlin1.MainMenyuFragments.updateAllPL

import com.example.liberatingdelta_kotlin1.R
import com.example.liberatingdelta_kotlin1.basic_classes.PL
import com.example.liberatingdelta_kotlin1.basic_classes.main_character
import com.example.liberatingdelta_kotlin1.databinding.FragmentCharViewRegionBinding
import com.example.liberatingdelta_kotlin1.databinding.FragmentMainMenyuBinding
import com.example.liberatingdelta_kotlin1.pl_relations.PL_VendingMachine
import kotlinx.android.synthetic.main.fragment_main_menyu.*

private const val PlayerLevel = "PlayerLevel"
private const val ARG_PARAM2 = "param2"

class MainMenyuFragment : Fragment(),
    MenyuBarFragment.menyuBarListener, updateAllPL {
    private var param1: String? = null
    private var param2: String? = null
    private var mListener: mainMenyuFragmentListener? = null

    private var pl: Int = 0
    private lateinit var this_pl: PL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            pl = it.getInt(PlayerLevel)
            this_pl = PL_VendingMachine.getPL(pl)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentMainMenyuBinding>(inflater,R.layout.fragment_main_menyu, container, false)
        binding.regionBackground.setImageDrawable(activity?.getDrawable(mListener!!.grabRegion()))
        return binding.root
    }

    override fun settingsPressed(view: View) {
        Log.d("DEBUG","settingsPressed")
        view.findNavController().navigate(R.id.action_mainMenyuFragment_to_settingsFragment)
    }
    override fun characterPressed(view: View) {
        Log.d("DEBUG","characterPressed")
        view.findNavController().navigate(R.id.action_mainMenyuFragment_to_charViewFragment)
    }
    override fun journalPressed(view: View) {
        view.findNavController().navigate(R.id.action_mainMenyuFragment_to_journalFragment)
    }
    override fun deckPressed(view: View) {
        view.findNavController().navigate(R.id.action_mainMenyuFragment_to_deckViewFragment)
    }
    override fun inventoryPressed(view: View) {
        view.findNavController().navigate(R.id.action_mainMenyuFragment_to_inventoryViewFragment)
    }
    override fun mapPressed(view: View) {
        view.findNavController().navigate(R.id.action_mainMenyuFragment_to_wholeMapFragment)
    }

    /*
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }
    */

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is mainMenyuFragmentListener) {
            mListener = context
        } else {
            throw RuntimeException(context.toString() + " must implement mainMenyuFragmentListener")
        }
    }

    /*
    override fun onDetach() {
        super.onDetach()
        listener = null
    }
    */

    override fun lemmeupdatethatpl(pl:Int) {
        this.pl = pl
        this_pl = PL_VendingMachine.getPL(pl)
    }

    fun grabMMC(): String {
        return mListener!!.grabMMC()
    }

    fun grabCUR_PL(): PL {
        return mListener!!.grabCUR_PL()
    }

    interface mainMenyuFragmentListener {
        fun grabRegion(): Int
        fun grabMMC(): String
        fun grabCUR_PL(): PL
    }

    companion object {
        @JvmStatic
        fun newInstance(pl: Int, param2: String) =
            MainMenyuFragment().apply {
                arguments = Bundle().apply {
                    putInt(PlayerLevel, pl)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
