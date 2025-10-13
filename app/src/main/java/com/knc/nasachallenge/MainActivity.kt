package com.knc.nasachallenge

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.knc.data.repo.ApodRepoImp
import com.knc.domain.usecases.LoadItems
import com.knc.nasachallenge.databinding.ActivityMainBinding
import com.knc.nasachallenge.fragments.HomeFrg

class MainActivity : FragmentActivity() {
    private lateinit var viewBinding: ActivityMainBinding
    val apodRepo = ApodRepoImp()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        LoadItems(apodRepo).execute()

        supportFragmentManager.beginTransaction()
            .replace(
                viewBinding.fragmentHolder.id,
                HomeFrg(apodRepo, viewBinding.fragmentHolder.id)
            )
            .commit()
    }
}