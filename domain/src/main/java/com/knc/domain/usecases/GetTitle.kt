package com.knc.domain.usecases

import com.knc.domain.interfaces.ApodRepoItf

class GetTitle(val apodRepo: ApodRepoItf) {
    fun execute(): ArrayList<String> {
        val apods = apodRepo.getItems()
        val result = ArrayList<String>()

        for (i in apods) {
            result.add(i.title!!)
        }

        return result
    }
}