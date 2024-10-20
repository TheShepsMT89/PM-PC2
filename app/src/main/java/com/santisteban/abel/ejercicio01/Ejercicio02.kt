package com.santisteban.abel.ejercicio01

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.santisteban.abel.ejercicio01.databinding.ActivityEjercicio02Binding

class Ejercicio02 : AppCompatActivity() {

    private lateinit var binding: ActivityEjercicio02Binding

    private val NAME_KEY = "NAME_KEY"
    private val NUMBER_KEY = "NUMBER_KEY"
    private val PRODUCT_KEY = "PRODUCT_KEY"
    private val CITY_KEY = "CITY_KEY"
    private val ADDRESS_KEY = "ADDRESS_KEY"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEjercicio02Binding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        observeButtons()
    }

    private fun observeButtons(){
        binding.btnEnviar.setOnClickListener {
            goDetailActivity()
        }
    }

    private fun goDetailActivity(){
        val name = binding.edtName.text.toString()
        val number = binding.edtNumber.text.toString()
        val producto = binding.edtProduct.text.toString()
        val city = binding.edtCity.text.toString()
        val direccion = binding.edtDireccion.text.toString()

        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(NAME_KEY, name)
        intent.putExtra(NUMBER_KEY, number)
        intent.putExtra(PRODUCT_KEY, producto)
        intent.putExtra(CITY_KEY, city)
        intent.putExtra(ADDRESS_KEY, direccion)
        startActivity(intent)
    }
}