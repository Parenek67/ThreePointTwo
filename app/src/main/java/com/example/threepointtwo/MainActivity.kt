package com.example.threepointtwo

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val editText: EditText = findViewById(R.id.editText)
        val ok: Button = findViewById(R.id.ok)
        val g: RadioGroup = findViewById(R.id.group)
        ok.setOnClickListener{
            if(editText.text.contains("""[a-zA-Z]+""".toRegex())){
                startIntent(editText.text.toString())
            }
            if(editText.text.matches("""\+?[0-9]+""".toRegex())){
                startIntent("tel: " + editText.text)
            }
            if(editText.text.matches("""-?[0-9.]+; -?[0-9.]+""".toRegex())){
                startIntent("geo: " + editText.text)
            }
        }
        g.setOnCheckedChangeListener{group, checkedId ->
            when(findViewById<RadioButton>(checkedId).text){
                "Url" -> startIntent("https://www.google.ru/")
                "Number" -> startIntent("tel: 999999999")
                "Location" -> startIntent("geo: -1; -1")
            }
        }
    }
    fun startIntent(uri: String){
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
        startActivity(intent)
    }
}

