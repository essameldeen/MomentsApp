package com.example.momentsapp.presentation.home.ui
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.momentsapp.R
import com.example.momentsapp.data.model.Moment
import com.example.momentsapp.presentation.home.viewModel.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val viewModel: HomeViewModel by viewModel()
        viewModel.getMoments()
        viewModel.moment.observe(this, {
            updateUI(it)
        })
        viewModel.progressBar.observe(this, {
            progressShowOrHidden(it)
        })
        viewModel.errorMessage.observe(this, {
            showErrorMessage(it)
        })

        return view
    }

    private fun showErrorMessage(error: Throwable?) {
        Toast.makeText(activity, error?.message, Toast.LENGTH_LONG).show()
    }

    private fun progressShowOrHidden(value: Boolean?) {
        progressBar.visibility = if (value!!) View.VISIBLE else View.GONE
    }

    private fun updateUI(result: MutableList<Moment>?) {
        // update Adapter
    }


}