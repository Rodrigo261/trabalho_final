package com.example.trabalho_final

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<View>(R.id.btnBebidas).setOnClickListener {
            startActivity(Intent(this, BebidasActivity::class.java))
        }

        findViewById<View>(R.id.btnPeixe).setOnClickListener {
            startActivity(Intent(this, PeixesActivity::class.java))
        }

        findViewById<View>(R.id.btnCarne).setOnClickListener {
            startActivity(Intent(this, CarnesActivity::class.java))
        }

        findViewById<View>(R.id.btnCarboidratos).setOnClickListener {
            startActivity(Intent(this, CarboidratosActivity::class.java))
        }

        findViewById<View>(R.id.btnFruta).setOnClickListener {
            startActivity(Intent(this, FrutaActivity::class.java))
        }

        findViewById<View>(R.id.btnVegetais).setOnClickListener {
            startActivity(Intent(this, VegetaisActivity::class.java))
        }

        findViewById<View>(R.id.btnObjetivo).setOnClickListener {
            startActivity(Intent(this, ObjetivoActivity::class.java))
        }
    }
}