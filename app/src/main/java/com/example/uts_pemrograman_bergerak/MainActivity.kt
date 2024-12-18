package com.example.uts_pemrograman_bergerak

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        val usiaInput = findViewById<EditText>(R.id.form_usia)
        val beratInput = findViewById<EditText>(R.id.form_berat_badan)
        val tinggiInput = findViewById<EditText>(R.id.form_tinggi_badan)
        val genderGroup = findViewById<RadioGroup>(R.id.radioGender)
        val aktivitasGroup = findViewById<RadioGroup>(R.id.radioAktifitas)
        val buttonSubmit = findViewById<Button>(R.id.btn_submit)

        buttonSubmit.setOnClickListener(){
            val usia = usiaInput.text.toString().toIntOrNull() ?: 0
            val berat = beratInput.text.toString().toFloatOrNull() ?: 0f
            val tinggi = tinggiInput.text.toString().toFloatOrNull() ?: 0f
            val genderId = genderGroup.checkedRadioButtonId
            val aktivitasId = aktivitasGroup.checkedRadioButtonId

            val faktorAktivitas = when (aktivitasId) {
                R.id.radioTidakAktif -> 1.2
                R.id.radioRingan -> 1.375
                R.id.radioSedang -> 1.55
                R.id.radioBerat -> 1.725
                R.id.radioSangatBerat -> 1.9
                else -> 1.0
            }

            //hitung BMR
            val bmr = if (genderId == R.id.radioButtonJkLk) {
                10 * berat + 6.25 * tinggi - 5 * usia + 5
            } else {
                10 * berat + 6.25 * tinggi - 5 * usia - 161
            }

            // hitung TDEE
            val tdee = bmr * faktorAktivitas

            Log.d("hasil", "Kalori Harian Anda: ${"%.2f".format(tdee)} kkal")


            Intent(this, Rekomendasi::class.java).also{
                it.putExtra("KALORI_HARIAN", "${"%.2f".format(tdee)}")
                startActivity(it)
            }
        }
    }
}