package com.knc.nasachallenge.recycler_view

import androidx.recyclerview.widget.DiffUtil
import com.knc.domain.models.ApodModel
import com.knc.domain.models.DjsnProductModel

class RVDiffUtilApod: DiffUtil.ItemCallback<ApodModel>() {
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

class RVDiffUtilDjsnPrd: DiffUtil.ItemCallback<DjsnProductModel>() {
    override fun areItemsTheSame(
        oldItem: DjsnProductModel,
        newItem: DjsnProductModel
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: DjsnProductModel,
        newItem: DjsnProductModel
    ): Boolean {
        return oldItem == newItem
    }
}