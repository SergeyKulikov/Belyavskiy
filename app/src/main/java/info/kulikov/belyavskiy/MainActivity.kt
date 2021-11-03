package info.kulikov.belyavskiy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val al = Algorithm()

        val etPoints = findViewById<EditText>(R.id.etPoints)
        val etValue = findViewById<EditText>(R.id.etValue)
        val button = findViewById<Button>(R.id.button)

        button.setOnClickListener {
            var value = 0
            try {
                value = etValue.text.toString().toInt()
            } catch (ex: Exception) {0
                Log.e("MainActivity", ex.localizedMessage)
            }

            al.start(value)
            etPoints.setText(al.toString())
        }
    }
}