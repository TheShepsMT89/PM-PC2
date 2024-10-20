package com.santisteban.abel.ejercicio01

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.santisteban.abel.ejercicio01.databinding.ActivityDetail2Binding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetail2Binding

    private val NAME_KEY = "NAME_KEY"
    private val NUMBER_KEY = "NUMBER_KEY"
    private val PRODUCT_KEY = "PRODUCT_KEY"
    private val CITY_KEY = "CITY_KEY"
    private val ADDRESS_KEY = "ADDRESS_KEY"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetail2Binding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        showInformation(intent.extras)
        observeButtons(intent.extras)
    }

    fun showInformation(bundle: Bundle?){

        val name = bundle?.getString(NAME_KEY)
        val number = bundle?.getString(NUMBER_KEY)
        val product = bundle?.getString(PRODUCT_KEY)
        val city = bundle?.getString(CITY_KEY)
        val address = bundle?.getString(ADDRESS_KEY)

        if(bundle != null){
            binding.tvName.text = "Nombre completo: $name"
            binding.tvNumber.text = "Numero telefonico: $number"
            binding.tvProduct.text = "Producto: $product"
            binding.tvDireccion.text = "Ciudad: $city, $address"
        }
    }

    private fun observeButtons(bundle: Bundle?) {

        binding.btnCALL.setOnClickListener {
            goCall(bundle)
        }

        binding.btnWSP.setOnClickListener {
            goWsp(bundle)
        }

        binding.btnMAP.setOnClickListener {
            goMap(bundle)
        }
    }

    private fun goWsp(bundle: Bundle?) {

        val phone = "+51${bundle?.getString(NUMBER_KEY)}"
        val nombre = bundle?.getString(NAME_KEY)
        val product = bundle?.getString(PRODUCT_KEY)
        val city = bundle?.getString(CITY_KEY)
        val address = bundle?.getString(ADDRESS_KEY)
        val message = "Hola $nombre, Tu producto $product estan en camino a la direccion:  $city, $address"

        val intentWsp = Intent()
        intentWsp.action = Intent.ACTION_VIEW
        intentWsp.data = Uri.parse("https://wa.me/$phone?text=$message")

        startActivity(intentWsp)
    }

    private fun goCall(bundle: Bundle?) {

        val phone = bundle?.getString(NUMBER_KEY)

        val intentDial = Intent()
        intentDial.action = Intent.ACTION_DIAL
        intentDial.data = Uri.parse("tel:$phone")

        startActivity(intentDial)
    }

    private fun goMap(bundle: Bundle?) {
        val ciudad = bundle?.getString(CITY_KEY)
        val direccion = bundle?.getString(ADDRESS_KEY)
        val mapa = "$direccion, $ciudad"

        val formattedMap = mapa.replace(" ", "+")

        val intentMap = Intent(Intent.ACTION_VIEW)
        intentMap.data = Uri.parse("https://www.google.com/maps/place/$formattedMap")

        startActivity(intentMap)
    }



}