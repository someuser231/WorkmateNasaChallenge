package com.knc.nasachallenge.recycler_view

import androidx.recyclerview.widget.RecyclerView
import com.knc.domain.models.ApodModel
import com.knc.domain.models.DjsnProductModel
import com.knc.nasachallenge.databinding.RvItemBinding
import com.squareup.picasso.Picasso

class RVItem(val view: RvItemBinding): RecyclerView.ViewHolder(view.root) {
    fun bindApod(model: ApodModel) {
        view.txtTitle.text = model.title
        view.txtDate.text = model.date
        Picasso.get().load(model.url).into(view.imgSimple)
    }
    fun bindDjsnPrd(model: DjsnProductModel) {
        view.txtTitle.text = model.title
        view.txtDate.text = "Price: $${model.price}"
        Picasso.get().load(model.images[0]).into(view.imgSimple)
    }
}