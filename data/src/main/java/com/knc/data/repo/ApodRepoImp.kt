package com.knc.data.repo

import androidx.lifecycle.MutableLiveData
import com.knc.data.interfaces.ApodApiItf
import com.knc.data.remote.ApodApiUtils
import com.knc.domain.interfaces.ApodRepoItf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ApodRepoImp: ApodRepoItf {
    val liveData = ApodLD()

    override fun loadItems() {
        CoroutineScope(Dispatchers.IO).launch {
            val apodApi = ApodApiUtils.getInstance().create(ApodApiItf::class.java)
            val apods = apodApi.getApod()
            liveData.postValue(apods)
        }
    }
}