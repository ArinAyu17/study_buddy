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

class MainActivity : AppCompatActivity() {

    private var timer: CountDownTimer? = null
    private var isTimerRunning = false
    private var userType: String? = null
    private val studyTimeInMillis = 1800000L // Set timer ke 30 menit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userType = intent.getStringExtra("userType")

        val btnStart = findViewById<Button>(R.id.btnStart)
        val btnPause = findViewById<Button>(R.id.btnPause)
        val timerTextView = findViewById<TextView>(R.id.timerTextView)

        btnStart.setOnClickListener {
            if (!isTimerRunning) {
                startTimer(studyTimeInMillis, timerTextView)
            }
        }

        btnPause.setOnClickListener {
            pauseTimer()
        }
    }

    private fun startTimer(timeInMillis: Long, timerTextView: TextView) {
        timer = object : CountDownTimer(timeInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val minutes = (millisUntilFinished / 1000) / 60
                val seconds = (millisUntilFinished / 1000) % 60
                timerTextView.text = String.format("%02d:%02d", minutes, seconds)
            }

            override fun onFinish() {
                isTimerRunning = false
                timerTextView.text = "Waktu Habis!"
                sendNotification()
            }
        }
        timer?.start()
        isTimerRunning = true
    }

    private fun pauseTimer() {
        timer?.cancel()
        isTimerRunning = false
    }

    private fun sendNotification() {
        val notificationBuilder = NotificationCompat.Builder(this, "studyChannel")
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle("Timer Selesai")
            .setContentText("Waktu belajar selesai!")
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(1, notificationBuilder.build())
    }
}

private fun lockApp() {
    startLockTask()
}

private fun unlockApp() {
    stopLockTask()
}
