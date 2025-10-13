package com.knc.nasachallenge.recycler_view

import androidx.recyclerview.widget.DiffUtil
import com.knc.domain.models.ApodModel

class RVDiffUtil: DiffUtil.ItemCallback<ApodModel>() {
    override fun areItemsTheSame(
        oldItem: ApodModel,
        newItem: ApodModel
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: ApodModel,
        newItem: ApodModel
    ): Boolean {
        return oldItem == newItem
    }
}