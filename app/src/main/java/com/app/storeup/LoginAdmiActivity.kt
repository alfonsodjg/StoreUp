package com.app.storeup

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.app.storeup.databinding.ActivityLoginAdmiBinding

class LoginAdmiActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginAdmiBinding
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLoginAdmiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Cambia el color degradado en el status bar
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.TRANSPARENT
        @SuppressLint("UseCompatLoadingForDrawables")
        val background: Drawable? = resources.getDrawable(R.drawable.statusbar_admi)
        window.setBackgroundDrawable(background)

        binding.btnBack.setOnClickListener {
            val i=Intent(this,LoginActivity::class.java)
            startActivity(i)
            finish()
        }
    }
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        val i=Intent(this,LoginActivity::class.java)
        startActivity(i)
        finish()
    }
}