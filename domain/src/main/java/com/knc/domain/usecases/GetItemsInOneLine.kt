package com.knc.domain.usecases

import com.knc.domain.models.ApodModel

class GetItemsInOneLine (val items: ArrayList<ApodModel>) {
    fun execute(): String {
        var result: String = ""
        for (i in items.indices) {
            result += "${i+1}) ${items[i].title}; ${items[i].date}\n"
        }
        return result
    }
}