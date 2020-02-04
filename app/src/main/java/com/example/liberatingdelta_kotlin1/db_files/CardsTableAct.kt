package com.example.liberatingdelta_kotlin1.db_files

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.liberatingdelta_kotlin1.dbCheckerActivity
import com.example.liberatingdelta_kotlin1.db_files.adapter_classes.cards_adapter
import com.example.liberatingdelta_kotlin1.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CardsTableAct : AppCompatActivity() {
    private var rpgViewModel: RPG_ViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_values_table)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = cards_adapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        rpgViewModel = ViewModelProvider(this).get(RPG_ViewModel::class.java)
        rpgViewModel!!.getlUserCards()!!.observe(
            this,
            Observer { vals ->
                // Update the cached copy of the words in the adapter.
                adapter.setlData(vals)
            })
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@CardsTableAct, dbCheckerActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}