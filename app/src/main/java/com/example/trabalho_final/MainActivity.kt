package com.example.trabalhofinal

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.trabalho_final.R


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonCarboidratos.setOnClickListener {
            val intent = Intent(this, CarboidratosActivity::class.java)
            startActivity(intent)
        }

        buttonFruta.setOnClickListener {
            val intent = Intent(this, FrutaActivity::class.java)
            startActivity(intent)
        }

        buttonVegetais.setOnClickListener {
            val intent = Intent(this, VegetaisActivity::class.java)
            startActivity(intent)
        }

        buttonCarne.setOnClickListener {
            val intent = Intent(this, CarneActivity::class.java)
            startActivity(intent)
        }

        buttonPeixe.setOnClickListener {
            val intent = Intent(this, PeixeActivity::class.java)
            startActivity(intent)
        }

        buttonBebidas.setOnClickListener {
            val intent = Intent(this, BebidasActivity::class.java)
            startActivity(intent)
        }
    }
}