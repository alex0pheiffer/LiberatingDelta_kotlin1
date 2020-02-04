package com.example.liberatingdelta_kotlin1

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_up_button.*
import kotlinx.android.synthetic.main.fragment_up_button.view.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class UpButtonFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private var listener: upButtonListener? = null

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
        val viewyy = inflater.inflate(R.layout.fragment_up_button, container, false)
        viewyy.uppityBtn.text="helloooo"
        viewyy.uppityBtn.setOnClickListener {
            upPressed()
        }
        return viewyy
    }

    fun upPressed() {
        listener?.upPressed()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is upButtonListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement upButtonListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface upButtonListener {
        fun upPressed()
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            UpButtonFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
