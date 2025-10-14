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
import com.knc.domain.usecases.LoadApodApi
import com.knc.domain.usecases.LoadApodDb
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
import kotlinx.coroutines.Job
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

        appViewModel.modelFetchingId.value = 1

        viewBinding.rgrpMain.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId){
                viewBinding.rbtnApod.id -> appViewModel.modelFetchingId.value = 0
                viewBinding.rbtnDjsn.id -> appViewModel.modelFetchingId.value = 1
            }
        }

        viewBinding.rvMain.layoutManager = LinearLayoutManager(requireContext())

        appViewModel.modelFetchingId.observe(viewLifecycleOwner) {
            when (it) {
                0 -> {
                    checkInternet()
                    rvHelperApod.retry()
                    initRvAdapter(rvHelperApod)
                    updateData(rvHelperApod)
                    checkFail(rvHelperApod)
                }
                1 -> {
                    checkInternet()
                    rvHelperDjsnPrd.retry()
                    initRvAdapter(rvHelperDjsnPrd)
                    updateData(rvHelperDjsnPrd)
                    checkFail(rvHelperDjsnPrd)
                }
            }
        }
    }

    fun initRvAdapter(rvHelper: Any) {
        when(rvHelper){
            is RVHelperApod -> {
                viewBinding.rvMain.adapter = rvHelper.withLoadStateFooter(
                    RVLoadMore{
                        checkInternet()
                        rvHelper.retry()
                    }
                )
                viewBinding.btnRetry.setOnClickListener {
                    checkInternet()
                    rvHelper.retry()
                }
            }
            is RVHelperDjsnProduct -> {
                viewBinding.rvMain.adapter = rvHelper.withLoadStateFooter(
                    RVLoadMore{
                        checkInternet()
                        rvHelper.retry()
                    }
                )
                viewBinding.btnRetry.setOnClickListener {
                    checkInternet()
                    rvHelper.retry()
                }
            }
        }
    }

    fun updateData(rvHelper: Any) {
        when (rvHelper) {
            is RVHelperApod -> {
                appViewModel.isConnected.observe(viewLifecycleOwner) {
                    viewLifecycleOwner.lifecycleScope.launch {
                        when (it) {
                            true -> LoadApodApi(appViewModel.apodRepo).execute().collectLatest {
                                appViewModel.pagingApodApi.collect {
                                    rvHelper.submitData(it)
                                }
                            }
                            false -> LoadApodDb(appViewModel.apodRepo).execute().collectLatest {
                                appViewModel.pagingApodDb.collect {
                                    rvHelper.submitData(it)
                                }
                            }
                        }
                    }
                }
            }
            is RVHelperDjsnProduct -> {
                appViewModel.isConnected.observe(viewLifecycleOwner) {
                    viewLifecycleOwner.lifecycleScope.launch {
                        when (it) {
                            true -> LoadDjsnProductApi(appViewModel.djsnRepo).execute().collectLatest {
                                appViewModel.pagingDjsnPrdApi.collect {
                                    rvHelper.submitData(it)
                                }
                            }
                            false -> LoadDjsnProductDb(appViewModel.djsnRepo).execute().collectLatest {
                                appViewModel.pagingDjsnPrdDb.collect {
                                    rvHelper.submitData(it)
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private var jobCheckFail: Job? = null
    fun checkFail(rvHelper: Any) {
        jobCheckFail?.cancel()
        when(rvHelper){
            is RVHelperApod -> {
                jobCheckFail = lifecycleScope.launch {
                    rvHelper.loadStateFlow.collect {
                        viewBinding.prgbarRv.isVisible = it.refresh is LoadState.Loading
                        viewBinding.txtConnectionFailed.isVisible = it.refresh is LoadState.Error
                        viewBinding.btnRetry.isVisible = it.refresh is LoadState.Error
                    }
                }
            }
            is RVHelperDjsnProduct -> {
                jobCheckFail = lifecycleScope.launch {
                    rvHelper.loadStateFlow.collect {
                        viewBinding.prgbarRv.isVisible = it.refresh is LoadState.Loading
                        viewBinding.txtConnectionFailed.isVisible = it.refresh is LoadState.Error
                        viewBinding.btnRetry.isVisible = it.refresh is LoadState.Error
                    }
                }
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