package com.knc.domain.usecases

import com.knc.domain.models.ApodModel
import com.knc.domain.models.ListItemModel

class GetItems (val items: ArrayList<ApodModel>) {
    fun execute(): ArrayList<ApodModel> {
        return items
    }
}