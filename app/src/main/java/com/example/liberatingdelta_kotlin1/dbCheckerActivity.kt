package com.example.liberatingdelta_kotlin1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.liberatingdelta_kotlin1.db_files.*
import kotlinx.android.synthetic.main.activity_db_checker.*

class dbCheckerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_db_checker)
        btnValues.setOnClickListener {
            val a = Intent(this@dbCheckerActivity, ValuesTableAct::class.java)
            startActivity(a)
            finish()
        }
        btnCharacters.setOnClickListener {
            val a = Intent(this@dbCheckerActivity, CharactersTableAct::class.java)
            startActivity(a)
            finish()
        }
        btnInventory.setOnClickListener {
            val a = Intent(this@dbCheckerActivity, InventoryTableAct::class.java)
            startActivity(a)
            finish()
        }
        btnCards.setOnClickListener {
            val a = Intent(this@dbCheckerActivity, CardsTableAct::class.java)
            startActivity(a)
            finish()
        }
        btnDecks.setOnClickListener {
            val a = Intent(this@dbCheckerActivity, DecksTableAct::class.java)
            startActivity(a)
            finish()
        }
        btnEQPlayed.setOnClickListener {
            val a = Intent(this@dbCheckerActivity, EQPlayedTableAct::class.java)
            startActivity(a)
            finish()
        }
    }
}