package com.app.storeup.views

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.app.storeup.R
import com.app.storeup.databinding.ActivityLoginAdmiBinding
import com.app.storeup.viewmodels.LoginAdmiViewModel

class LoginAdmiActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginAdmiBinding
    private lateinit var viewModel:LoginAdmiViewModel
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLoginAdmiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel=ViewModelProvider(this).get()
        binding.model=viewModel
        binding.lifecycleOwner=this

        // Cambia el color degradado en el status bar
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.TRANSPARENT
        @SuppressLint("UseCompatLoadingForDrawables")
        val background: Drawable? = resources.getDrawable(R.drawable.statusbar_admi)
        window.setBackgroundDrawable(background)

        binding.btnBack.setOnClickListener {back()}
        binding.tvCreateAccountAdmi.setOnClickListener { createAccount() }

        sinIng()
    }
    private fun sinIng(){
        viewModel.operation.observe(this, Observer {
            if(it){
                val i=Intent(this, HomeAdmiActivity2::class.java)
                startActivity(i)
                finish()
            }else{
                binding.edtMailTextLayoutSingIn.error="Campo obligatorio"
                binding.edtPassTextLayoutSingIn.error="Campo obligatorio"
            }
        })

        viewModel.errorCredentials.observe(this, Observer {
            Toast.makeText(this,it,Toast.LENGTH_SHORT).show()
        })
    }
    private fun back(){
        val i=Intent(this, LoginActivity::class.java)
        startActivity(i)
        finish()
    }
    private fun createAccount(){
        val i=Intent(this,RegisterAccountAdmiActivity::class.java)
        startActivity(i)
        finish()
    }
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        val i=Intent(this, LoginActivity::class.java)
        startActivity(i)
        finish()
    }
}