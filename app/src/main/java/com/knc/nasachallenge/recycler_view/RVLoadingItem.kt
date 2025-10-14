package com.knc.nasachallenge.recycler_view

import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.knc.nasachallenge.databinding.LoaderPagingBinding
import com.knc.nasachallenge.databinding.RvItemBinding

class RVLoadingItem(val view: LoaderPagingBinding, retry: ()->Unit):
    RecyclerView.ViewHolder(view.root) {
    init {
        view.btnRetry.setOnClickListener { retry() }
    }

    fun bind(state: LoadState) {
        view.apply {
            prgbarPaging.isVisible = state is LoadState.Loading
            txtError.isVisible = state is LoadState.Error
            btnRetry.isVisible = state is LoadState.Error
        }
    }
}