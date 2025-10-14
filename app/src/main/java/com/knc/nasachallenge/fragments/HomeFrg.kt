package com.knc.nasachallenge.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.knc.domain.usecases.LoadApod
import com.knc.domain.usecases.LoadDjsnProduct
import com.knc.nasachallenge.databinding.FrgHomeBinding
import com.knc.nasachallenge.recycler_view.RVHelperApod
import com.knc.nasachallenge.recycler_view.RVHelperDjsnProduct
import com.knc.nasachallenge.view_models.AppViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

private const val ARG_FRG_HOLDER_ID = "frgHolderId"

class HomeFrg() : Fragment() {
    private var frgHolderId: Int? = null

    lateinit var viewBinding: FrgHomeBinding

    val appViewModel: AppViewModel by activityViewModels()

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
//        val rvHelperApod = RVHelperApod(appViewModel,frgHolderId!!)
        val rvHelperDjsnPrd = RVHelperDjsnProduct(appViewModel, frgHolderId!!)
        viewBinding.rvMain.layoutManager = LinearLayoutManager(requireContext())
        viewBinding.rvMain.adapter = rvHelperDjsnPrd

        lifecycleScope.launch {
//            LoadApod(appViewModel.apodRepo).execute().collectLatest {
//                appViewModel.pagingApod.collect {
//                    rvHelperApod.submitData(it)
//                }
//            }
            LoadDjsnProduct(appViewModel.djsnRepo).execute().collectLatest {
                appViewModel.pagingDjsnPrd.collect {
                    rvHelperDjsnPrd.submitData(it)
                }
            }

        }

        lifecycleScope.launch {
            rvHelperDjsnPrd.loadStateFlow.collect {
                viewBinding.prgbarRv.isVisible = it.refresh is LoadState.Loading
            }
        }
    }
}