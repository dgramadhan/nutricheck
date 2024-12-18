package com.example.uts_pemrograman_bergerak

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Rekomendasi : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_rekomendasi)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        val textKebutuhanKalori = findViewById<TextView>(R.id.tv_kebutuhan_kalori)
        val kaloriString = intent.getStringExtra("KALORI_HARIAN")
        val textRekomendasi = findViewById<TextView>(R.id.tv_rekomendasi_makanan)
        val kebutuhanKalori = kaloriString?.toFloatOrNull()

        val rekomendasiMakanan = when {
            kebutuhanKalori!! < 1500 -> """
                Sarapan: 
                Oatmeal + susu almond (150 kcal)
                
                Makan Siang: 
                Salad sayur dengan dada ayam panggang (350 kcal)
                
                Camilan: 
                Buah apel atau pisang (90 kcal)
                
                Makan Malam: 
                Sup sayur + tahu (300 kcal)
            """.trimIndent()
                    kebutuhanKalori!! in 1500.0..2000.0 -> """
                Sarapan: 
                Roti gandum + selai kacang (250 kcal)
                
                Makan Siang: 
                Nasi merah, ikan panggang, dan sayuran (500 kcal)
                
                Camilan: 
                Yoghurt rendah lemak (120 kcal)
                
                Makan Malam: 
                Ayam panggang + kentang rebus + brokoli (400 kcal)
            """.trimIndent()
                    kebutuhanKalori in 2000.0..2500.0 -> """
                Sarapan: 
                Omelet + roti panggang + jus jeruk (300 kcal)
                
                Makan Siang: 
                Nasi putih, tempe orek, sayur asem, dan ikan bakar (600 kcal)
                
                Camilan: 
                Kacang almond (200 kcal)
                
                Makan Malam: 
                Daging sapi panggang + kentang tumbuk + salad (500 kcal)
            """.trimIndent()
                    else -> """
                Sarapan: 
                Smoothie protein + telur dadar (400 kcal)
                
                Makan Siang: 
                Nasi putih, ayam bakar, sayur capcay, dan tahu goreng (700 kcal)
                
                Camilan: 
                Roti lapis isi tuna (300 kcal)
                
                Makan Malam: 
                Steak daging sapi + kentang panggang + salad (600 kcal)
            """.trimIndent()
        }

        textKebutuhanKalori.text = kaloriString
        textRekomendasi.text = rekomendasiMakanan

    }
}