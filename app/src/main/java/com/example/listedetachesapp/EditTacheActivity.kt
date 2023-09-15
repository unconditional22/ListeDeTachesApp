package com.example.listedetachesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.listedetachesapp.ui.theme.ListeDeTachesAppTheme

import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import com.example.listedetachesapp.R
import Tache
import TachesAdapter
import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.activity.result.contract.ActivityResultContracts


class EditTacheActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_tache)

        // Get the position from the intent
        val position = intent.getIntExtra("position", -1)

        // Get the tasks array from the intent
        val tasks = intent.getParcelableArrayExtra("tasks") as? Array<Tache>

        if (position != -1 && tasks != null && position < tasks.size) {
            val tache = tasks[position]
            val editText = findViewById<EditText>(R.id.editText)
            editText.setText(tache.description)

            val saveButton = findViewById<Button>(R.id.saveButton)
            saveButton.setOnClickListener {
                val newDescription = editText.text.toString()
                tache.description = newDescription

                val resultIntent = Intent()
                resultIntent.putExtra("position", position)
                resultIntent.putExtra("tache", tache)
                setResult(Activity.RESULT_OK, resultIntent)

                finish()
            }
        } else {
            Toast.makeText(this, "Invalid task position", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}

    @Composable
    fun Greeting2(name: String, modifier: Modifier = Modifier) {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview2() {
        ListeDeTachesAppTheme {
            Greeting2("Android")
        }
    }
