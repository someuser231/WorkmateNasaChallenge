package com.knc.domain.usecases

import com.knc.domain.models.ApodModel

class GetItems (val items: ArrayList<ApodModel>) {
    fun execute(): ArrayList<ApodModel> {
        return items
    }
}