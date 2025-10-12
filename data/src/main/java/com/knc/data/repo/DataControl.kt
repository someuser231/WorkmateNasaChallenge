package com.knc.data.repo

import com.knc.data.remote.ApodApi


class DataControl {
    fun testRetrofit() {
        ApodApi().getItems()
    }
}