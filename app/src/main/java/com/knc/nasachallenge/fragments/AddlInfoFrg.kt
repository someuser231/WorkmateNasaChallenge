package com.knc.nasachallenge.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import com.knc.nasachallenge.databinding.FrgAddlInfoBinding
import com.knc.nasachallenge.view_models.AppViewModel
import com.squareup.picasso.Picasso

class AddlInfoFrg() : Fragment() {
    lateinit var viewBinding: FrgAddlInfoBinding

    val appViewModel: AppViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FrgAddlInfoBinding.inflate(inflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        appViewModel.modelFetchingId.observe(viewLifecycleOwner) {
            when (it) {
                0 -> {
                    val item = appViewModel.apodItem
                    viewBinding.txtTitle.text = item.title
                    viewBinding.txtDate.text = item.date
                    viewBinding.txtDescription.text = item.explanation

                    Picasso.get().load(item.url).into(viewBinding.imgSimple)
                }
                1 -> {
                    val item = appViewModel.djsnPrdItem
                    viewBinding.txtTitle.text = item.title
                    viewBinding.txtDate.text = "Price: $${item.price}"
                    viewBinding.txtDescription.text = item.description
                    Picasso.get().load(item.images[0]).into(viewBinding.imgSimple)
                }
            }
        }

        viewBinding.toolbar.ibtnBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        requireActivity().onBackPressedDispatcher.addCallback(
            this,
            object: OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    parentFragmentManager.popBackStack()
                }
            }
        )
    }
}