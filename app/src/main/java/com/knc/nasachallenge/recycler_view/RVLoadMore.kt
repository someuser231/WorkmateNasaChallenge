package com.knc.nasachallenge.recycler_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.knc.nasachallenge.databinding.LoaderPagingBinding

class RVLoadMore(val retry: () -> Unit): LoadStateAdapter<RVLoadingItem>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): RVLoadingItem {
        val card = LoaderPagingBinding.inflate(LayoutInflater.from(
            parent.context),
            parent,
            false
        )
        return RVLoadingItem(card, retry)
    }

    override fun onBindViewHolder(
        holder: RVLoadingItem,
        loadState: LoadState
    ) {
        holder.bind(loadState)
    }
}