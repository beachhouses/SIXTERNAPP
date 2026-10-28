package com.sixtern.sixternapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Menyembunyikan Action Bar agar tampilan splash screen benar-benar penuh (full screen)
        supportActionBar?.hide()

        // Menghubungkan file logika ini dengan layout XML splash screen (activity_main.xml)
        setContentView(R.layout.activity_main)

        // Menggunakan Handler untuk memberikan jeda waktu (timer) selama 3 detik (3000 milidetik)
        Handler(Looper.getMainLooper()).postDelayed({
            // Membuat Intent untuk berpindah dari MainActivity ke HomeActivity
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)

            // Menutup MainActivity agar ketika pengguna menekan tombol 'Back' di HP,
            // aplikasi langsung keluar dan tidak kembali lagi ke halaman splash screen.
            finish()
        }, 3000) // 3000 milidetik = 3 detik
    }
}