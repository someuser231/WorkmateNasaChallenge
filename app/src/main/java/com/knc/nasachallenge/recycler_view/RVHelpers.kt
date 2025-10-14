package com.knc.nasachallenge.recycler_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.paging.PagingDataAdapter
import com.knc.domain.models.ApodModel
import com.knc.domain.models.DjsnProductModel
import com.knc.domain.usecases.InsertIntoDjsnDb
import com.knc.nasachallenge.databinding.RvItemBinding
import com.knc.nasachallenge.fragments.AddlInfoFrg
import com.knc.nasachallenge.view_models.AppViewModel

private val createItemView = fun(parent: ViewGroup): RVItem{
    val card = RvItemBinding.inflate(LayoutInflater.from(
        parent.context),
        parent,
        false
    )
    return RVItem(card)
}

class RVHelperApod(val appViewModel: AppViewModel, val frgHolderId: Int):
    PagingDataAdapter<ApodModel, RVItem>(RVDiffUtilApod()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RVItem {
        return createItemView(parent)
    }

    override fun onBindViewHolder(
        holder: RVItem,
        position: Int
    ) {
        holder.bindApod(getItem(position)!!)
        holder.view.root.setOnClickListener {
            appViewModel.apodItem = getItem(position)!!
            (holder.view.root.context as FragmentActivity)
                .supportFragmentManager
                .beginTransaction().replace(
                    frgHolderId,
                    AddlInfoFrg()
                )
                .addToBackStack(null)
                .commit()
        }
    }
}

class RVHelperDjsnProduct(val appViewModel: AppViewModel, val frgHolderId: Int):
        PagingDataAdapter<DjsnProductModel, RVItem>(RVDiffUtilDjsnPrd()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RVItem {
        return createItemView(parent)
    }

    override fun onBindViewHolder(
        holder: RVItem,
        position: Int
    ) {
        holder.bindDjsnPrd(getItem(position)!!)
        InsertIntoDjsnDb(appViewModel.djsnRepo).execute(getItem(position)!!)
        holder.view.root.setOnClickListener {
            appViewModel.djsnPrdItem = getItem(position)!!
            (holder.view.root.context as FragmentActivity)
                .supportFragmentManager
                .beginTransaction().replace(
                    frgHolderId,
                    AddlInfoFrg()
                )
                .addToBackStack(null)
                .commit()
        }
    }
}