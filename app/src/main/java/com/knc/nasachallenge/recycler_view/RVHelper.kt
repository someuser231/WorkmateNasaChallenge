package com.knc.nasachallenge.recycler_view


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.paging.PagingDataAdapter
import com.knc.domain.models.ApodModel
import com.knc.nasachallenge.databinding.RvItemBinding
import com.knc.nasachallenge.fragments.AddlInfoFrg

class RVHelper(val frgHolderId: Int): PagingDataAdapter<ApodModel, RVItem>(RVDiffUtil()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RVItem {
        val card = RvItemBinding.inflate(LayoutInflater.from(
            parent.context),
            parent,
            false
        )
        return RVItem(card)
    }

    override fun onBindViewHolder(
        holder: RVItem,
        position: Int
    ) {
        holder.bind(getItem(position)!!)
        holder.view.root.setOnClickListener {
            (holder.view.root.context as FragmentActivity)
                .supportFragmentManager
                .beginTransaction().replace(
                    frgHolderId,
                    AddlInfoFrg(getItem(position)!!)
                )
                .addToBackStack(null)
                .commit()
        }
    }
}