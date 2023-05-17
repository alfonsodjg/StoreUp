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
import com.app.storeup.LoginAdmiActivity
import com.app.storeup.R
import com.app.storeup.databinding.ActivityRegisterAccountAdmiBinding
import com.app.storeup.viewmodels.ActivityCreateAccountAdmiViewModel

class RegisterAccountAdmiActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterAccountAdmiBinding
    private lateinit var viewModel: ActivityCreateAccountAdmiViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityRegisterAccountAdmiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel=ViewModelProvider(this).get() //Inicializacion del viewModel
        binding.model=viewModel
        binding.lifecycleOwner=this

        viewModel.operationSuccessful.observe(this, Observer {
            if (it){
                binding.edtNameAdmi.setText("")
                binding.edtEmailAdmi.setText("")
                binding.edtPasswordAdmi.setText("")
                Toast.makeText(this,"Register successful",Toast.LENGTH_SHORT).show()
            }else{
                binding.edtLayoutNameAdmi.error="Enter your name"
                binding.edtLayoutNameAdmi.isHintEnabled=false
                binding.edtLayoutMailAdmi.error="Enter your mail"
                binding.edtLayoutMailAdmi.isHintEnabled=false
                binding.edtLayoutPassAdmi.error="Enter your pass"
                binding.edtLayoutPassAdmi.isHintEnabled=false
            }
        })

        binding.btnBack.setOnClickListener {back()}
        // Cambia el color degradado en el status bar
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.TRANSPARENT
        @SuppressLint("UseCompatLoadingForDrawables")
        val background: Drawable? = resources.getDrawable(R.drawable.statusbar_admi)
        window.setBackgroundDrawable(background)
    }
    private fun back(){
        val i=Intent(this,LoginAdmiActivity::class.java)
        startActivity(i)
        finish()
    }
    override fun onBackPressed() {
        val i= Intent(this, LoginAdmiActivity::class.java)
        startActivity(i)
        finish()
    }
}