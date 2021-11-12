package com.example.momentsapp.presentation.new_moment

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.momentsapp.R
import com.example.momentsapp.databinding.FragmentCreateNewMomentBinding
import com.example.momentsapp.utils.ImageUrlParser
import com.example.momentsapp.utils.Loading
import org.koin.androidx.viewmodel.ext.android.viewModel
import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.RuntimePermissions

@RuntimePermissions
class CreateNewMomentFragment : Fragment()
{
    private lateinit var viewBinding: FragmentCreateNewMomentBinding
    private val viewModel: CreateNewMomentViewModel by viewModel()

    private val cameraLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == Activity.RESULT_OK && it.data != null)
        {
            val imagePath = ImageUrlParser.getUriFromExtra(requireActivity().contentResolver, it.data!!.extras!!["data"] as Bitmap)
            Glide.with(requireActivity()).load(imagePath).thumbnail(0.1f).into(viewBinding.photo)
            viewModel.photoPath = imagePath
        }
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        viewModel.state.observe(this@CreateNewMomentFragment, { setViewState(it) })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
    {
        viewBinding = FragmentCreateNewMomentBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        viewBinding.capture.setOnClickListener { openCameraWithPermissionCheck() }

        viewBinding.share.setOnClickListener { createNewMoment() }
    }

    override fun onDestroy()
    {
        super.onDestroy()
        viewModel.clear()
    }

    private fun setViewState(state: CreateNewMomentViewState)
    {
        when (state)
        {
            is CreateNewMomentViewState.SuccessfulCreationState ->
            {
                Loading.dismiss()
                Toast.makeText(requireActivity(), getString(R.string.successful_moment), Toast.LENGTH_SHORT).show()
                findNavController().popBackStack()
            }
            is CreateNewMomentViewState.ErrorCreationState -> {
                Loading.dismiss()
                Toast.makeText(requireActivity(), state.error.message ?: getString(R.string.error), Toast.LENGTH_SHORT).show()
            }
            is CreateNewMomentViewState.LoadingCreationState -> Loading.show(requireActivity())
        }
    }

    private fun createNewMoment()
    {
        if (inputsAreValid()) viewModel.createMoment(viewBinding.title.toString(), viewBinding.description.toString(), viewBinding.country.toString(), viewBinding.city.toString())
    }

    private fun inputsAreValid(): Boolean = viewBinding.title.toString().isNotEmpty() && viewBinding.description.toString().isNotEmpty() && viewBinding.country.toString().isNotEmpty() && viewBinding.city.toString().isNotEmpty()

    @NeedsPermission(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
    fun openCamera() = cameraLauncher.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE))

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        onRequestPermissionsResult(requestCode, grantResults)
    }
}