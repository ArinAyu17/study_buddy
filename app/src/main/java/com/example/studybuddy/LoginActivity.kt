package com.example.studybuddy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.studybuddy.ui.theme.StudyBuddyTheme

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btnUser1 = findViewById<Button>(R.id.btnUser1)
        val btnUser2 = findViewById<Button>(R.id.btnUser2)

        btnUser1.setOnClickListener {
            // Login sebagai User1 (anak)
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("userType", "User1")
            startActivity(intent)
        }

        btnUser2.setOnClickListener {
            // Login sebagai User2 (orang tua)
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("userType", "User2")
            startActivity(intent)
        }
    }
}