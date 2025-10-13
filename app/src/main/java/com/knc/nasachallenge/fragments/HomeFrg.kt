package com.knc.nasachallenge.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.knc.domain.usecases.LoadItems
import com.knc.nasachallenge.databinding.FrgHomeBinding
import com.knc.nasachallenge.recycler_view.RVHelper
import com.knc.nasachallenge.view_models.ApodViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

private const val ARG_FRG_HOLDER_ID = "frgHolderId"

class HomeFrg() : Fragment() {
    private var frgHolderId: Int? = null

    lateinit var viewBinding: FrgHomeBinding

    val apodViewModel: ApodViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            frgHolderId = it.getInt(ARG_FRG_HOLDER_ID)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FrgHomeBinding.inflate(inflater)
        return viewBinding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: Int) =
            HomeFrg().apply {
                arguments = Bundle().apply {
                    putInt(ARG_FRG_HOLDER_ID, param1)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rvHelper = RVHelper(apodViewModel,frgHolderId!!)
        viewBinding.rvMain.layoutManager = LinearLayoutManager(requireContext())
        viewBinding.rvMain.adapter = rvHelper

        lifecycleScope.launch {
            LoadItems(apodViewModel.apodRepo).execute().collectLatest {
                apodViewModel.pagingData.collect {
                    rvHelper.submitData(it)
                }
            }

        }
    }
}