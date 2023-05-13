package com.app.storeup


import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.app.storeup.databinding.LoginMainBinding
import com.app.storeup.viewmodels.MainActivityViewModel
import com.app.storeup.views.FragmentCreateAccount
import com.app.storeup.views.HomeActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: LoginMainBinding
    private var createAccountFragment:FragmentCreateAccount?=null
    private lateinit var viewModel: MainActivityViewModel
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= LoginMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
       
        viewModel=ViewModelProvider(this).get() //Inicializacion de viewModel
        binding.model=viewModel
        binding.lifecycleOwner=this

        viewModel.operationSuccesful.observe(this, Observer {
            if(it){
                val i=Intent(this,HomeActivity::class.java)
                startActivity(i)
                finish()
            }else{
                binding.edtMailTextLayoutSingIn.error="Enter your mail"
                binding.edtMailTextLayoutSingIn.isHintEnabled=false
                binding.edtPassTextLayoutSingIn.error="Enter your pass"
                binding.edtPassTextLayoutSingIn.isHintEnabled=false
            }
        })

        val animationLiner=AnimationUtils.loadAnimation(this,R.anim.anim_fragment_up)
        binding.linerLogin.startAnimation(animationLiner)
        val animationCardLogin=AnimationUtils.loadAnimation(this,R.anim.anim_floor)
        binding.cvLogin.startAnimation(animationCardLogin)
        val imvAnimation2=AnimationUtils.loadAnimation(this,R.anim.anim_floor)
        binding.imV2.startAnimation(imvAnimation2)

        //Infla el fragmento para registrar cuenta
        binding.tvCreateAccount.setOnClickListener {
            createAccountFragment= FragmentCreateAccount()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, createAccountFragment!!)
                .commit()
        }
        binding.btnSingIn.setOnClickListener {
            Toast.makeText(this,"Sing in...", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onResume() {
        super.onResume()
        // Deshabilitar elementos de la actividad
        createAccountFragment?.let {
            if(it.isAdded) { //Condicion que verifica si el fragmento sigue agregado en la actividad, realizara lo siguiente
                it.requireActivity().findViewById<View>(R.id.btnSingIn).isEnabled = false
                it.requireActivity().findViewById<View>(R.id.edtEmail).isEnabled = false
                it.requireActivity().findViewById<View>(R.id.edtPassword).isEnabled = false
                it.requireActivity().findViewById<View>(R.id.tvForgotPassword).isEnabled = false
            }
        }
    }

    override fun onPause() {
        super.onPause()
        // Habilitar elementos de la actividad
        createAccountFragment?.let {
            if(it.isAdded) { //Condicion que verifica si el fragmento sigue agregado en la actividad, realizara lo siguiente
                it.requireActivity().findViewById<View>(R.id.btnSingIn).isEnabled = true
                it.requireActivity().findViewById<View>(R.id.edtEmail).isEnabled = true
                it.requireActivity().findViewById<View>(R.id.edtPassword).isEnabled = true
                it.requireActivity().findViewById<View>(R.id.tvForgotPassword).isEnabled = true
            }
        }
    }

}