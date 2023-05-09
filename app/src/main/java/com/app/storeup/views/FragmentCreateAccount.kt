package com.app.storeup.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.app.storeup.R
import com.app.storeup.databinding.FragmentCreateAccountBinding
import com.app.storeup.viewmodels.FragmentCreateAccountViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentCreateAccount.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentCreateAccount : Fragment(){
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

    private lateinit var binding: FragmentCreateAccountBinding
    private lateinit var viewModel: FragmentCreateAccountViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCreateAccountBinding.inflate(inflater, container, false)
        viewModel=ViewModelProvider(this).get()
        binding.model = viewModel
        binding.lifecycleOwner = this

        viewModel.removeFragmentEvent.observe(viewLifecycleOwner){
            // Remover el fragmento
            requireActivity().supportFragmentManager.beginTransaction()
                .remove(this)
                .commit()
        }

        viewModel.operationSuccesful.observe(viewLifecycleOwner){
            if(it){
                binding.edtMailCreateAccount.setText("")
                binding.edtPassCreateAccount.setText("")
                Toast.makeText(context,"Usuario registrado",Toast.LENGTH_SHORT).show()
            }else{
                binding.edtMailCreateAccount.error="Enter your mail"
                binding.edtPassCreateAccount.error="Enter your pass"
            }
        }
        val animation=AnimationUtils.loadAnimation(context,R.anim.anim_fragment_up)
        binding.cvCreateAccount.startAnimation(animation)
        return binding.root
    }
    override fun onResume() {
        super.onResume()
        // deshabilitar elementos de la actividad
        requireActivity().findViewById<View>(R.id.btnSingIn).isEnabled = false
        requireActivity().findViewById<View>(R.id.edtEmail).isEnabled = false
        requireActivity().findViewById<View>(R.id.edtPassword).isEnabled=false
        requireActivity().findViewById<View>(R.id.tvForgotPassword).isEnabled=false
    }

    override fun onPause() {
        super.onPause()
        // habilitar elementos de la actividad
        requireActivity().findViewById<View>(R.id.btnSingIn).isEnabled = true
        requireActivity().findViewById<View>(R.id.edtEmail).isEnabled = true
        requireActivity().findViewById<View>(R.id.edtPassword).isEnabled=true
        requireActivity().findViewById<View>(R.id.tvForgotPassword).isEnabled=true
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentCreateAccount.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentCreateAccount().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}