package com.example.trabalho_final

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class CarnesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carnes)

        val rgCarnes = findViewById<RadioGroup>(R.id.rg_carnes)
        val etQuantidade = findViewById<EditText>(R.id.et_quantidade)
        val btnCalcular = findViewById<Button>(R.id.btn_calcular)
        val tvResultado = findViewById<TextView>(R.id.tv_resultado)

        btnCalcular.setOnClickListener {
            val quantidadeStr = etQuantidade.text.toString()

            if (quantidadeStr.isEmpty()) {
                Toast.makeText(this, "Por favor, insira a quantidade", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val quantidade = quantidadeStr.toInt()
            val calorias = when (rgCarnes.checkedRadioButtonId) {
                R.id.rb_bife_vaca -> calcularCalorias(quantidade, 250)
                R.id.rb_bife_peru -> calcularCalorias(quantidade, 150)
                R.id.rb_bife_frango -> calcularCalorias(quantidade, 200)
                R.id.rb_almondegas -> calcularCalorias(quantidade, 300)
                else -> 0
            }

            tvResultado.text = "Calorias: $calorias kcal"
        }
    }

    private fun calcularCalorias(quantidade: Int, caloriasPor100g: Int): Int {
        return (quantidade * caloriasPor100g) / 100
    }
}