package com.example.liberatingdelta_kotlin1.MainMenyuFragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController

import com.example.liberatingdelta_kotlin1.R
import com.example.liberatingdelta_kotlin1.databinding.FragmentCharViewRankBinding
import com.example.liberatingdelta_kotlin1.databinding.FragmentCharViewRegionBinding
import kotlinx.android.synthetic.main.fragment_char_view_bar.*
import kotlinx.android.synthetic.main.fragment_char_view_bar.view.*
import kotlinx.android.synthetic.main.fragment_up_button.*
import kotlinx.android.synthetic.main.fragment_up_button.view.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class CharviewFragment_region : Fragment(), CharViewBarFragment.charViewBarListener, MMCFragment.mmcFragmentListener {
    private var param1: String? = null
    private var param2: String? = null
    //private var listener: charViewFragmentRegionListener? = null

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
        val binding = DataBindingUtil.inflate<FragmentCharViewRegionBinding>(inflater,R.layout.fragment_char_view_region, container, false)
        /*
        val viewyy = inflater.inflate(R.layout.fragment_char_view_region, container, false)
        viewyy.uppityBtn.setOnClickListener {
            it.findNavController().navigate(R.id.action_charviewFragment_region_to_mainMenyuFragment)
        }
        viewyy.charViewBar_stats_btn.setOnClickListener {
            it.findNavController().navigate(R.id.action_charviewFragment_region_to_charViewFragment)
        }
        viewyy.charViewBar_equip_btn.setOnClickListener {
            it.findNavController().navigate(R.id.action_charviewFragment_region_to_charViewFragment_equip)
        }
        viewyy.charViewBar_region_btn.setOnClickListener {
            //nothing
        }
        viewyy.charViewBar_rank_btn.setOnClickListener {
            it.findNavController().navigate(R.id.action_charviewFragment_region_to_charViewFragment_rank)
        }
        viewyy.charViewBar_info_btn.setOnClickListener {
            it.findNavController().navigate(R.id.action_charviewFragment_region_to_charViewFragment_info)
        }
        return viewyy
        */
        return binding.root
    }

    override fun statsPressed(it: View) {
        it.findNavController().navigate(R.id.action_charviewFragment_region_to_charViewFragment)
    }
    override fun equipPressed(it: View) {
        it.findNavController().navigate(R.id.action_charviewFragment_region_to_charViewFragment_equip)
    }
    override fun regionPressed(it: View) {
        //nothing
    }
    override fun rankPressed(it: View) {
        it.findNavController().navigate(R.id.action_charviewFragment_region_to_charViewFragment_rank)
    }
    override fun infoPressed(it: View) {
        it.findNavController().navigate(R.id.action_charviewFragment_region_to_charViewFragment_info)
    }

    /*
    fun onButtonPressed(uri: Uri) {
        //listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is charViewFragmentRegionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement charViewFragmentRegionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface charViewFragmentRegionListener {
        //fun onFragmentInteraction(uri: Uri)
    }
    */

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CharviewFragment_region().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}