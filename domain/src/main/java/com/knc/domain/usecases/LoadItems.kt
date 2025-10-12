package com.knc.domain.usecases

import com.knc.domain.interfaces.ApodRepoItf

class LoadItems(val apodRepo: ApodRepoItf) {
    fun execute() {
        apodRepo.loadItems()
    }
}