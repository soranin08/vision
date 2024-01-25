package com.example.visiontest

import android.Manifest.permission.RECORD_AUDIO
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.speech.RecognitionListener
import android.view.View
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import android.widget.ImageView
import kotlin.random.Random
import java.util.*
import kotlin.concurrent.thread
import kotlin.concurrent.timerTask
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {

    private lateinit var startBotton: Button
    private lateinit var sampletext: TextView
    private lateinit var randle_img: ImageView
    private lateinit var textVision: TextView

    private var mspeechRecognizer : SpeechRecognizer? = null

    private var speechRecognizerIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)

    private val imageResources = listOf(
        R.drawable.dw,
        R.drawable.le,
        R.drawable.ri,
        R.drawable.up
    )

    private val answerkanji = listOf(
        "下",
        "左",
        "右",
        "上"
    )
    private val answerhiragana = listOf(
        "した",
        "ひだり",
        "みぎ",
        "うえ"
    )

    private var currentIndex = 0

    private var num = 0

    private var isListening = false

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("DEBUG", "activity作成")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        randle_img = findViewById(R.id.imageView)
        startBotton = findViewById(R.id.recognize_start_button)
        sampletext = findViewById(R.id.recognize_text_view)
        textVision = findViewById(R.id.textvision)

        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)

        startBotton.setOnClickListener{
            startBotton.visibility = View.INVISIBLE
            textVision.visibility = View.INVISIBLE
            sampletext.visibility = View.VISIBLE
            num = randamcreate()
            randle_img.setImageResource(imageResources[num])
            randle_img.visibility = View.VISIBLE
            startActivityForResult(speechRecognizerIntent, SPEECH_REQUEST_CODE)
            Log.d("DEBUG", "ボタンを押した")
        }

    }

    val SPEECH_REQUEST_CODE = 0

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("DEBUG","point A")

        if (requestCode == 0 && resultCode == Activity.RESULT_OK) {
            Log.d("DEBUG","point B")

            Log.d("DEBUG","point C")
            val spokenText: String? =
                data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)?.let { results ->
                    results[0]
                }

            Log.d("DEBUG","point D")
            // Do something with spokenText.

            if(answerkanji[num] == spokenText || answerhiragana[num] == spokenText){
                sampletext.setText("正解")
                Log.d("DEBUG","正解")
            }else{
                sampletext.text = "不正解"
                Log.d("DEBUG","不正解")
            }

            num = randamcreate()

            Log.d("DEBUG","point F")

            // 音声認識を起動
            Handler(Looper.getMainLooper()).postDelayed({
                randle_img.setImageResource(imageResources[num])
                Log.d("DEBUG","音声認識もういっかい")
                startActivityForResult(speechRecognizerIntent, 0)
            }, 3000)

        }
    }

    private fun randamcreate() : Int {
        return (0..3).random()
    }

    companion object{
        private const val PERMISSION_RECORD_AUDIO = 1000
    }
}
