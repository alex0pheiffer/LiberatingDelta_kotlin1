package com.example.liberatingdelta_kotlin1.db_files

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.liberatingdelta_kotlin1.R
import com.example.liberatingdelta_kotlin1.db_files.adapter_classes.values_adapter

class ValuesTableAct : AppCompatActivity() {
    private var rpgViewModel: RPG_ViewModel? = null
    //public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_values_table)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = values_adapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        rpgViewModel = ViewModelProvider(this).get(RPG_ViewModel::class.java)
        rpgViewModel!!.getlUserValues()!!.observe(
            this,
            Observer {
                // Update the cached copy of the words in the adapter.
                println("updating values")
                //adapter.setlData(vals);
            })
    } /*
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ValuesTableAct.this, dbCheckerActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    */
/*
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            User_Values useval = new User_Values(0,0,0,"Thalass",data.getStringExtra(NewValuesAct.EXTRA_REPLY),"pass");
            rpgViewModel.insert(useval);
        }
        else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }
 */
}