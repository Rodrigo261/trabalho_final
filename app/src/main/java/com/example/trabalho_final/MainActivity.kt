package com.example.trabalhoFinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.trabalho_final.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonProteina = findViewById<Button>(R.id.button_proteina)
        val buttonHidratos = findViewById<Button>(R.id.button_hidratos)
        val buttonBebidas = findViewById<Button>(R.id.button_bebidas)
        val buttonVegetais = findViewById<Button>(R.id.button_vegetais)
        val buttonFrutas = findViewById<Button>(R.id.button_frutas)

        buttonProteina.setOnClickListener {
            val intent = Intent(this, ProteinaActivity::class.java)
            startActivity(intent)
        }

        buttonHidratos.setOnClickListener {
            val intent = Intent(this, HidratosActivity::class.java)
            startActivity(intent)
        }

        buttonBebidas.setOnClickListener {
            val intent = Intent(this, BebidasActivity::class.java)
            startActivity(intent)
        }

        buttonVegetais.setOnClickListener {
            val intent = Intent(this, VegetaisActivity::class.java)
            startActivity(intent)
        }

        buttonFrutas.setOnClickListener {
            val intent = Intent(this, FrutasActivity::class.java)
            startActivity(intent)
        }
    }

    private fun Intent(mainActivity: MainActivity, java: Any): Any {
        TODO("Not yet implemented")
    }
}