package com.example.mvvmsampleapp.Main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmsampleapp.R
import com.example.mvvmsampleapp.adapters.MainAdapter
import com.example.mvvmsampleapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()
    private val mainAdapter = MainAdapter()
    private var bundle: Bundle?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            bundle=savedInstanceState
        }
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        fetchUsersBtn()
        setupLoadingObserver()
        setupUsersObserver()
        setupEmptyObserver()
        setupFailureObserver()
    }

    fun fetchUsersBtn() {
        if(bundle==null) {
            mainViewModel.onFetchUsersBtnClicked()}
        else Log.d("youssef2","youssef25 $bundle")
        //binding.btnGetUsers.setOnClickListener { mainViewModel.onFetchUsersBtnClicked() }
    }

    private fun setupUI() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                binding.recyclerView.context,
                (binding.recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding.recyclerView.adapter = mainAdapter
    }

    private fun setupFailureObserver() {
        mainViewModel.getFailure().observe(this, Observer {
            hideProgress()
            hideRecyclerView()
            showFailureMsg()
            showGetUserBtn()
        })
    }

    private fun setupEmptyObserver() {
        mainViewModel.getEmpty().observe(this, Observer {
            hideProgress()
            hideRecyclerView()
            showFailureMsg()
            showGetUserBtn()
        })
    }

    private fun setupUsersObserver() {
        mainViewModel.getDataSuccess().observe(this, Observer {
            hideProgress()
            hideFailureMsg()
            showRecyclerView()
            hideGetUserBtn()
            mainAdapter.addData(it)
        })
    }

    private fun setupLoadingObserver() {
        mainViewModel.getLoading().observe(this, Observer{
            showProgress()
            hideRecyclerView()
            hideFailureMsg()
            hideGetUserBtn()
        })
    }

    fun hideProgress() {
        binding.progressBar.visibility = View.GONE
    }

    fun showProgress() {
        binding.progressBar.visibility = View.VISIBLE
    }

    fun showGetUserBtn() {
        binding.btnGetUsers.visibility = View.VISIBLE
    }

    fun hideGetUserBtn() {
        binding.btnGetUsers.visibility = View.GONE
    }

    fun showRecyclerView() {
        binding.recyclerView.visibility = View.VISIBLE
    }

    fun hideRecyclerView() {
        binding.recyclerView.visibility = View.GONE
    }

    fun showFailureMsg() {
        binding.tvFailure.visibility = View.VISIBLE
    }

    fun hideFailureMsg() {
        binding.tvFailure.visibility = View.GONE
    }

}