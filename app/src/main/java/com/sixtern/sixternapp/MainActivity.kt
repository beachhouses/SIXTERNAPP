package com.sixtern.sixternapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.postDelayed

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "SPLASH_DEBUG"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG, "MainActivity onCreate dimulai")
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)

        findViewById<android.view.View>(android.R.id.content).postDelayed({
            Log.d(TAG, "Timer 3 detik SELESAI. Mencoba pindah ke MahasiswaActivity...")

            try {
                val intent = Intent(this, MahasiswaActivity::class.java)
                startActivity(intent)
                finish()
                Log.d(TAG, "Perintah startActivity berhasil dikirim")
            } catch (e: Exception) {
                Log.e(TAG, "GAGAL pindah ke MahasiswaActivity: ${e.message}", e)
                Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }, 3000)
    }
}