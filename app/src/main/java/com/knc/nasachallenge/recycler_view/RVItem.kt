package com.knc.nasachallenge.recycler_view

import androidx.recyclerview.widget.RecyclerView
import com.knc.domain.models.ApodModel
import com.knc.nasachallenge.databinding.RvItemBinding
import com.squareup.picasso.Picasso

class RVItem(val view: RvItemBinding): RecyclerView.ViewHolder(view.root) {
    fun bind(apodModel: ApodModel) {
        view.txtTitle.text = apodModel.title
        view.txtDate.text = apodModel.date
        Picasso.get().load(apodModel.url).into(view.imgSimple)
    }
}