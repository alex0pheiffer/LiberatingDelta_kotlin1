package com.example.liberatingdelta_kotlin1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.liberatingdelta_kotlin1.basic_classes.sudoCard
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startstartbuttony.setOnClickListener {
            val intented = Intent(this, MainMenyuActivity::class.java)
            startActivity(intented)
            finish()
        }
    }
}
