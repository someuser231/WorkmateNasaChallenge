package com.knc.nasachallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import com.knc.data.repo.DataControl

class MainActivity : ComponentActivity() {
    val dataControl = DataControl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        dataControl.testRetrofit()
    }
}