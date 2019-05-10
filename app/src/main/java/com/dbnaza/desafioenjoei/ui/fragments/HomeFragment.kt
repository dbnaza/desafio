package com.dbnaza.desafioenjoei.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dbnaza.desafioenjoei.ItemDecorationAlbumColumns
import com.dbnaza.desafioenjoei.R
import com.dbnaza.desafioenjoei.api.ProductsDataSourceImpl
import com.dbnaza.desafioenjoei.ui.adapters.ProductsAdapter
import com.dbnaza.desafioenjoei.ui.viewmodels.HomeViewModel
import com.dbnaza.desafioenjoei.ui.viewmodels.HomeViewModelFactory
import com.dbnaza.desafioenjoei.utils.NetStatus
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by lazy {
        ViewModelProviders.of(this, HomeViewModelFactory(ProductsDataSourceImpl)).get(HomeViewModel::class.java)
    }

    private val netStatus: NetStatus by lazy {
        NetStatus(requireContext())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            getProducts()
        }

        viewModel.flipperChildLiveData.observe(this, Observer {
            listFlipper.displayedChild = it
        })

        viewModel.resultLiveData.observe(this, Observer {
            listExams.adapter = ProductsAdapter(it)
        })

        viewModel.headerVisibilityLiveData.observe(this, Observer {
            imageHeader.visibility = it
        })

        listExams.addItemDecoration(
            ItemDecorationAlbumColumns(
                resources.getDimensionPixelSize(R.dimen.default_margin_screen), 2
            )
        )

        btnRetry.setOnClickListener {
            if (netStatus.isNetworkAvailable) {
                getProducts()
            }
        }
    }

    private fun getProducts() {
        viewModel.getProducts()
    }

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }
}