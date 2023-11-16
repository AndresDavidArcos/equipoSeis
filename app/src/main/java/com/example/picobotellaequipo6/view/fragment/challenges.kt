package com.example.picobotellaequipo6.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.picobotellaequipo6.databinding.FragmentChallengesBinding
import com.example.picobotellaequipo6.model.Challenges
import com.example.picobotellaequipo6.view.adapter.ChallengesAdapter
import com.example.picobotellaequipo6.viewmodel.ChallengesViewModel

class challenges : Fragment() {
    private lateinit var binding: FragmentChallengesBinding
    private val challengesViewModel: ChallengesViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentChallengesBinding.inflate(inflater)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.contentToolbar.backToolbar.setOnClickListener{ findNavController().popBackStack() }
        controladores()
        observerViewModel()
    }

    private fun observerViewModel() {
        observerListInventory()
    }
    private fun observerListInventory() {
        challengesViewModel.getListInvetory()
        challengesViewModel.listInventory.observe(viewLifecycleOwner){ lista->
            val recycler = binding.recyclerview
            val layoutManager = LinearLayoutManager(context)
            recycler.layoutManager = layoutManager
            val adapter = ChallengesAdapter(lista)
            recycler.adapter = adapter
            adapter.notifyDataSetChanged()
        }

    }

    private fun controladores() {
//        recycler()
        alertDia()

    }
//    var textoIngresado:String,
    private fun alertDia(){
        binding.fbagregar.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Agregar reto")

            val input = EditText(requireContext())
            builder.setView(input)

            builder.setPositiveButton("Guardar") { _, _ ->
                val textoIngresado = input.text.toString()
                if (textoIngresado.isNotEmpty()){
                    Toast.makeText(requireContext(),textoIngresado,Toast.LENGTH_LONG).show()
                    saveChallenge(textoIngresado)
                    observerListInventory()
                }

            }

            builder.setNegativeButton("Cancelar") { dialog, _ ->
                dialog.cancel()
            }

            val alertDialog = builder.create()
            alertDialog.show()
        }
    }

    private fun saveChallenge(eltexto:String){
        val name = eltexto
        val challengeV = Challenges(name=name)
        challengesViewModel.saveInventory(challengeV)
//        Toast.makeText(requireContext(),"Articuasor",Toast.LENGTH_SHORT).show()
//        findNavController().popBackStack()
    }

//    fun recycler(){
//        val listaR =  mutableListOf(
//            Challenges(1,"1"),
//            Challenges(1,"2"),
//            Challenges(1,"3"),
//            Challenges(1,"4"),
//            Challenges(1,"5"),
//            Challenges(1,"6"),
//            Challenges(1,"7"),
//            Challenges(1,"8"),
//            Challenges(1,"9"),
//            Challenges(1,"10")
//        )
//        val recycler = binding.recyclerview
//        recycler.layoutManager = LinearLayoutManager(context)
//        val adapter = ChallengesAdapter(listaR)
//        recycler.adapter = adapter
//        adapter.notifyDataSetChanged()
//    }

}