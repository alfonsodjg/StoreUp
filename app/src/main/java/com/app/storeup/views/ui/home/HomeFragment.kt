package com.app.storeup.views.ui.home

import android.R
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.app.storeup.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var viewModel:HomeViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        val root: View = binding.root
        viewModel=ViewModelProvider(this).get()
        binding.model = viewModel
        binding.lifecycleOwner = this

        val spinner = binding.spCategorias

        //Creacion de adapter para agregar elementos al sppiner
        val adapter = ArrayAdapter<String>(
            requireContext(),
            R.layout.simple_spinner_dropdown_item,
            mutableListOf<String>()
        )
        spinner.adapter = adapter

        //Observamos como se comporta la variable categoriasList en nuestro viewmodel
        viewModel.categoriasList.observe(viewLifecycleOwner) { categorias ->
            val categoriasNombres = categorias.map { it.tipo }
            adapter.clear()
            adapter.addAll(categoriasNombres)
        }

        viewModel.operation.observe(viewLifecycleOwner){
            if(it){
                println("Operation value: ${viewModel.operation.value}")
                binding.edtName.setText("")
                binding.edtLocation.setText("")
                binding.edtProducts.setText("")
                binding.edtPromotion.setText("")
                binding.edtLocation.setText("")
                binding.edtTime.setText("")
                alert()
            }else{
                binding.TlayoutName.error="Campo obligatorio"
                binding.TlayoutTime.error="Campo obligatorio"
                binding.TlayoutLocation.error="Campo obligatorio"
                binding.TlayoutProducts.error="Campo obligatorio"
                binding.TlayoutPromotion.error="Campo obligatorio"
                Toast.makeText(context,"error",Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.getCategorias()
        return root
    }
    private fun alert(){
        val builder=AlertDialog.Builder(context)
        builder.setMessage("Datas successfuly register")
            .setNeutralButton("Ok"){ dialogInterface: DialogInterface, i: Int ->
                dialogInterface.cancel()
            }
        val alet:AlertDialog=builder.create()
        alet.setCanceledOnTouchOutside(false)
        alet.setTitle("¡Alert!")
        alet.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}