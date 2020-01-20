package com.book.mybook.activities.home.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.book.mybook.R
import com.book.mybook.activities.home.adapter.ItemAdapter
import com.book.mybook.activities.home.contract.BestSellerContract
import com.book.mybook.activities.home.contract.TrendingContract
import com.book.mybook.activities.home.presenter.BestSellerPresenter
import com.book.mybook.activities.home.presenter.TrendingPresenter
import com.book.mybook.extension.isNetworkConnected
import com.book.mybook.model.Item
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment(), TrendingContract.View,
    BestSellerContract.View, View.OnClickListener {

    private lateinit var trendingPresenter: TrendingPresenter
    private lateinit var bestSellerPresenter: BestSellerPresenter
    private lateinit var viewFragment: View
    private lateinit var adapterTrending:  ItemAdapter
    private lateinit var adapterBestSeller:  ItemAdapter
    private var trendingItems: ArrayList<Item> = arrayListOf()
    private var bestSellerItems: ArrayList<Item> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapterTrending = ItemAdapter(context!!, trendingItems)
        adapterBestSeller = ItemAdapter(context!!, bestSellerItems)
        trendingPresenter = TrendingPresenter(this)
        bestSellerPresenter = BestSellerPresenter(this)
        getData()
    }

    private fun getData() {
        trendingPresenter.getTrendingBooks("Home Earth", 8, 0)
        bestSellerPresenter.getBestSellerBooks("Alien & Planet", 8, 0)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        viewFragment = inflater.inflate(R.layout.fragment_home, container, false)
        viewFragment.buttonSearch.setOnClickListener(this)
        viewFragment.buttonHomeRetry.setOnClickListener(this)
        setAdapterManager()
        return viewFragment
    }

    private fun setAdapterManager() {
        val layoutManagerBestSeller = LinearLayoutManager(context)
        layoutManagerBestSeller.orientation = LinearLayoutManager.HORIZONTAL
        viewFragment.layoutBestSeller.layoutManager = layoutManagerBestSeller
        viewFragment.layoutBestSeller.adapter = adapterBestSeller
        val layoutManagerTrending = LinearLayoutManager(context)
        layoutManagerTrending.orientation = LinearLayoutManager.HORIZONTAL
        viewFragment.layoutTrending.layoutManager = layoutManagerTrending
        viewFragment.layoutTrending.adapter = adapterTrending
    }

    private fun checkConnection() {
        activity?.runOnUiThread {
            context?.let {
                if(!it.isNetworkConnected){
                    viewFragment.cardOffline.visibility = View.VISIBLE
                }else{
                    viewFragment.cardOffline.visibility = View.GONE
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        checkConnection()
    }

    override fun onResume() {
        super.onResume()
        checkConnection()
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.buttonSearch -> {
                activity?.let {
                    (it as MainActivity).replaceFragment(SearchFragment())
                }
            }
            R.id.buttonHomeRetry ->{
                getData()
            }
        }
    }

    override fun viewTrendingData(items: ArrayList<Item>) {
        activity?.runOnUiThread {
            viewFragment.cardOffline.visibility = View.GONE
            if(items.isNotEmpty()) {
                this.trendingItems.addAll(items)
                adapterTrending.notifyDataSetChanged()
            }
        }
    }

    override fun showMessageErrorTrending(message: String) {
        activity?.runOnUiThread { Toast.makeText(context, message, Toast.LENGTH_LONG).show() }
    }

    override fun viewBestSellerData(items: ArrayList<Item>) {
        activity?.runOnUiThread {
            if(items.isNotEmpty()) {
                this.bestSellerItems.addAll(items)
                adapterBestSeller.notifyDataSetChanged()
            }
        }
    }

    override fun showMessageErrorBestSeller(message: String) {
        activity?.runOnUiThread { Toast.makeText(context, message, Toast.LENGTH_LONG).show() }
    }
}
