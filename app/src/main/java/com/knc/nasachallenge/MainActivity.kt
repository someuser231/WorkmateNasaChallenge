package com.knc.nasachallenge

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.knc.data.repo.ApodRepoImp
import com.knc.domain.usecases.LoadItems
import com.knc.nasachallenge.databinding.ActivityMainBinding
import com.knc.nasachallenge.fragments.HomeFrg
import com.knc.nasachallenge.view_models.ApodViewModel

class MainActivity : FragmentActivity() {
    private lateinit var viewBinding: ActivityMainBinding
    val apodRepo = ApodRepoImp()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        LoadItems(apodRepo).execute()
        val viewModel = ViewModelProvider(this).get(apodRepo::class.java)

        supportFragmentManager.beginTransaction()
            .replace(
                viewBinding.fragmentHolder.id,
                HomeFrg(viewModel, viewBinding.fragmentHolder.id)
            )
            .commit()
    }
}