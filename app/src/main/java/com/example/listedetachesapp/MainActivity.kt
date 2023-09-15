package com.example.listedetachesapp

import Tache
import TachesAdapter
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.listedetachesapp.ui.theme.ListeDeTachesAppTheme

import androidx.appcompat.app.AppCompatActivity

//android:theme="@style/Theme.ListeDeTachesApp"
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import java.util.ArrayList
//import java.util.List
import kotlin.collections.List
import kotlin.collections.MutableList

class MainActivity : AppCompatActivity() {

    private val EDIT_TASK_REQUEST_CODE = 1 // You can choose any integer value

    private val taches = mutableListOf<Tache>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var tachesAdapter: TachesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        tachesAdapter = TachesAdapter(taches)
        recyclerView.adapter = tachesAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)


        val ajouterButton = findViewById<Button>(R.id.button2)
        val descriptionEditText = findViewById<EditText>(R.id.editTextText)

        ajouterButton.setOnClickListener {
            val description = descriptionEditText.text.toString()
            if (description.isNotEmpty()) {
                val tache = Tache(description, false)
                taches.add(tache)
                tachesAdapter.notifyItemInserted(taches.size - 1)
                descriptionEditText.setText("")
            }
        }

        /*val editButton = findViewById<Button>(R.id.button)
        editButton.setOnClickListener { itemView ->
            // Get the position of the clicked item
            val position = recyclerView.getChildAdapterPosition(itemView)
            val intent = Intent(this, EditTacheActivity::class.java)
            intent.putExtra("position", position)
            startActivity(intent)
        }*/

        /*val editButton: Button = findViewById(R.id.button)
       editButton.setOnClickListener {view ->
           val position = recyclerView.getChildAdapterPosition(view)
           val intent = Intent(this, EditTacheActivity::class.java)
           intent.putExtra("position", position)
           startActivity(intent)
       }*/

        val editButton = findViewById<Button>(R.id.button)
        editButton.setOnClickListener { view ->
            // Get the position of the clicked item
            val position = recyclerView.getChildAdapterPosition(view)
            val intent = Intent(this, EditTacheActivity::class.java)

            // Pass the position to EditTacheActivity
            intent.putExtra("position", position)

            // Pass the list of tasks (taches) to EditTacheActivity
            val tasksArray = taches.toTypedArray()
            intent.putExtra("tasks", tasksArray)

            startActivity(intent)
        }




    }

    private val editTaskLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data
            val position = data?.getIntExtra("position", -1) ?: -1
            if (position != -1) {
                // Update the task data in the list based on the result
                tachesAdapter.notifyItemChanged(position)
            }
        }
    }


}



@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ListeDeTachesAppTheme {
        Greeting("Android")
    }
}