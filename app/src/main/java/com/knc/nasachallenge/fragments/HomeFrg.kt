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
import com.knc.domain.usecases.LoadDjsnProductApi
import com.knc.domain.usecases.LoadDjsnProductDb
import com.knc.nasachallenge.databinding.FrgHomeBinding
import com.knc.nasachallenge.network.NetworkUtils
import com.knc.nasachallenge.recycler_view.RVHelperApod
import com.knc.nasachallenge.recycler_view.RVHelperDjsnProduct
import com.knc.nasachallenge.recycler_view.RVLoadMore
import com.knc.nasachallenge.view_models.AppViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
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
        checkInternet()
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

        val rvHelperApod = RVHelperApod(appViewModel,frgHolderId!!)
        val rvHelperDjsnPrd = RVHelperDjsnProduct(appViewModel, frgHolderId!!)

        viewBinding.rgrpMain.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId){
                viewBinding.rbtnApod.id -> appViewModel.modelFetchingId.value = 0
                viewBinding.rbtnDjsn.id -> appViewModel.modelFetchingId.value = 1
            }
        }

        viewBinding.rvMain.layoutManager = LinearLayoutManager(requireContext())
        viewBinding.rvMain.adapter = rvHelperDjsnPrd.withLoadStateFooter(
            RVLoadMore{
                checkInternet()
                rvHelperDjsnPrd.retry()
            }
        )
        viewBinding.btnRetry.setOnClickListener {
            checkInternet()
            rvHelperDjsnPrd.retry()
        }

        appViewModel.isConnected.observe(viewLifecycleOwner) {
            viewLifecycleOwner.lifecycleScope.launch {
                when (it) {
                    true -> LoadDjsnProductApi(appViewModel.djsnRepo).execute().collectLatest {
                        appViewModel.pagingDjsnPrdApi.collect {
                            rvHelperDjsnPrd.submitData(it)
                        }
                    }
                    false -> LoadDjsnProductDb(appViewModel.djsnRepo).execute().collectLatest {
                        appViewModel.pagingDjsnPrdDb.collect {
                            rvHelperDjsnPrd.submitData(it)
                        }
                    }
                }
            }
        }

//        LoadApod(appViewModel.apodRepo).execute().collectLatest {
//            appViewModel.pagingApod.collect {
//                rvHelperApod.submitData(it)
//            }
//        }

        lifecycleScope.launch {
            rvHelperDjsnPrd.loadStateFlow.collect {
                viewBinding.prgbarRv.isVisible = it.refresh is LoadState.Loading
                viewBinding.txtConnectionFailed.isVisible = it.refresh is LoadState.Error
                viewBinding.btnRetry.isVisible = it.refresh is LoadState.Error
            }
        }
    }

    fun checkInternet() {
        if (NetworkUtils.isNetworkAvailable(requireContext())){
            CoroutineScope(Dispatchers.Main).launch {
                if (NetworkUtils.isInternetAvailable()){
                    appViewModel.isConnected.value = true
                } else {
                    appViewModel.isConnected.value = false
                }
            }
        } else {
            appViewModel.isConnected.value = false
        }
    }
}