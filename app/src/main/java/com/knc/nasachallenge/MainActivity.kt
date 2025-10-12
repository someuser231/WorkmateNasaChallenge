package com.knc.nasachallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.Observer
import com.knc.data.repo.ApodRepoImp
import com.knc.domain.usecases.GetItemsInOneLine
import com.knc.domain.usecases.LoadItems
import com.knc.nasachallenge.databinding.ActivityMainBinding

class MainActivity : ComponentActivity() {
    private lateinit var viewBinding: ActivityMainBinding
    val apodRepo = ApodRepoImp()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        LoadItems(apodRepo).execute()

        apodRepo.liveData.observe(this, Observer {

            viewBinding.txtMain.setText(GetItemsInOneLine(it).execute())
        })
    }
}