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

import com.example.liberatingdelta_kotlin1.R
import com.example.liberatingdelta_kotlin1.databinding.FragmentCharViewRegionBinding
import com.example.liberatingdelta_kotlin1.databinding.FragmentMainMenyuBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class MainMenyuFragment : Fragment(), MMCFragment.mmcFragmentListener,
    MenyuBarFragment.menyuBarListener {
    private var param1: String? = null
    private var param2: String? = null
    //private var listener: mainMenyuFragmentListener? = null

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
        val binding = DataBindingUtil.inflate<FragmentMainMenyuBinding>(inflater,R.layout.fragment_main_menyu, container, false)

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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is mainMenyuFragmentListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement mainMenyuFragmentListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface mainMenyuFragmentListener {
        //fun onFragmentInteraction(uri: Uri)
    }
    */

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainMenyuFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
