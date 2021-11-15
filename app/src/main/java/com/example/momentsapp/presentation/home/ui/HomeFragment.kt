package com.example.momentsapp.presentation.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.momentsapp.data.model.Moment
import com.example.momentsapp.databinding.FragmentHomeBinding
import com.example.momentsapp.presentation.home.adapter.MomentsAdapter
import com.example.momentsapp.presentation.home.viewModel.HomeViewModel
import com.example.momentsapp.utils.Loading
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    lateinit var momentsAdapter: MomentsAdapter
    lateinit var binding: FragmentHomeBinding
    val viewModel: HomeViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        initAdapter()
        viewModel.getMoments()
        viewModel.moment.observe(viewLifecycleOwner, {
            updateUI(it)
        })
        viewModel.progressBar.observe(viewLifecycleOwner, {
            progressShowOrHidden(it)
        })
        viewModel.errorMessage.observe(viewLifecycleOwner, {
            showErrorMessage(it)
        })

        return binding.root
    }

    private fun initAdapter() {
        momentsAdapter = MomentsAdapter()
        binding.momentsRv.apply {
            adapter = momentsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
        momentsAdapter.setOnItemClickListener {
            viewModel.likeMoment(it.id)
        }
    }

    private fun showErrorMessage(error: Throwable?) {
        Toast.makeText(activity, error?.message, Toast.LENGTH_LONG).show()
    }

    private fun progressShowOrHidden(value: Boolean?) {
        if (value == true) {
            context?.let { Loading.show(it) }
        } else {
            context?.let { Loading.dismiss() }
        }
    }

    private fun updateUI(result: MutableList<Moment>?) {
        momentsAdapter.differ.submitList(result?.toList())
    }


}