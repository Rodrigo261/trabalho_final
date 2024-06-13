package com.example.trabalho_final

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class PeixeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_peixes)

        val rgPeixe = findViewById<RadioGroup>(R.id.rg_peixe)
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
            val calorias = when (rgPeixe.checkedRadioButtonId) {
                R.id.rb_salmao -> calcularCalorias(quantidade, 206)
                R.id.rb_atum -> calcularCalorias(quantidade, 144)
                R.id.rb_bacalhau -> calcularCalorias(quantidade, 82)
                R.id.rb_truta -> calcularCalorias(quantidade, 148)
                else -> 0
            }

            tvResultado.text = "Calorias: $calorias kcal"
        }
    }

    private fun calcularCalorias(quantidade: Int, caloriasPor100g: Int): Int {
        return (quantidade * caloriasPor100g) / 100
    }
}