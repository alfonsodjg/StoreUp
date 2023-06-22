package com.app.storeup.views.ui.home

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.app.storeup.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val spinner = binding.spCategorias

        //Creacion de adapter para agregar elementos al sppiner
        val adapter = ArrayAdapter<String>(
            requireContext(),
            R.layout.simple_spinner_dropdown_item,
            mutableListOf<String>()
        )
        spinner.adapter = adapter

        //Observamos como se comporta la variable categoriasList en nuestro viewmodel
        homeViewModel.categoriasList.observe(viewLifecycleOwner) { categorias ->
            val categoriasNombres = categorias.map { it.tipo }
            adapter.clear()
            adapter.addAll(categoriasNombres)
        }

        homeViewModel.getCategorias()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}