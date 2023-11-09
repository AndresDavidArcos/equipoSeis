package com.example.picobotellaequipo6.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.picobotellaequipo6.R


/**
 * A simple [Fragment] subclass.
 * Use the [Retos.newInstance] factory method to
 * create an instance of this fragment.
 */
class Retos : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
//        return inflater.inflate(R.layout.fragment_retos, container, false)
        val view = inflater.inflate(R.layout.fragment_retos, container, false)

        val botonEliminarFragment = view.findViewById<Button>(R.id.backADD)
        botonEliminarFragment.setOnClickListener {
            backButton()
        }

        return view
    }


    companion object {
        const val ARG_PARAM1 = "param1"
        const val ARG_PARAM2 = "param2"

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Retos().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
//                }
                }
            }
    }
    private fun backButton() {
        val fragmentManager = requireActivity().supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.add(android.R.id.content, this)  // Usar android.R.id.content superpondrá el fragmento a la vista principal
        transaction.addToBackStack(null)  // Agregar a la pila de retroceso para poder volver
        transaction.commit()

    }
}