package com.knc.nasachallenge.recycler_view


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.knc.domain.models.ApodModel
import com.knc.nasachallenge.databinding.RvItemBinding

class RVHelper: RecyclerView.Adapter<RVViewHolder>() {
    var dataItems = ArrayList<ApodModel>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RVViewHolder {
        val card = RvItemBinding.inflate(LayoutInflater.from(
            parent.context),
            parent,
            false
        )
        return RVViewHolder(card)
    }

    override fun onBindViewHolder(
        holder: RVViewHolder,
        position: Int
    ) {
        holder.view.txtTitle.text = dataItems[position].title
        holder.view.txtDate.text = dataItems[position].date
        holder.view.root.setOnClickListener {
            val frgManager = (holder.view.root.context as FragmentActivity).supportFragmentManager
        }
    }

    override fun getItemCount(): Int {
        return dataItems.size
    }
}