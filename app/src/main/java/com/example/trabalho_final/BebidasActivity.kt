package com.example.trabalho_final

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class BebidasActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bebidas)

        val rgBebidas = findViewById<RadioGroup>(R.id.rg_bebidas)
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
            val calorias = when (rgBebidas.checkedRadioButtonId) {
                R.id.rb_coca_cola -> calcularCalorias(quantidade, 42)
                R.id.rb_sumo_laranja -> calcularCalorias(quantidade, 45)
                R.id.rb_agua -> calcularCalorias(quantidade, 0)
                R.id.rb_cha -> calcularCalorias(quantidade, 2)
                else -> 0
            }

            tvResultado.text = "Calorias: $calorias kcal"
        }
    }

    private fun calcularCalorias(quantidade: Int, caloriasPor100ml: Int): Int {
        return (quantidade * caloriasPor100ml) / 100
    }
}