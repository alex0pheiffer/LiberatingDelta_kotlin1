package com.example.liberatingdelta_kotlin1.db_files

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.liberatingdelta_kotlin1.R

class NewValuesAct : AppCompatActivity() {
    private var mEditValueView: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_values)
        mEditValueView = findViewById(R.id.edit_value)
        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(mEditValueView?.getText())) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val input = mEditValueView?.getText().toString()
                replyIntent.putExtra(EXTRA_REPLY, input)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"
    }
}