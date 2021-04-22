package com.nick_sib.beauty_radar.ui.profileScreen

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.nick_sib.beauty_radar.R
import com.nick_sib.beauty_radar.data.state.AppState
import com.nick_sib.beauty_radar.databinding.FragmentProfileBinding
import com.nick_sib.beauty_radar.provider.profile.entities.UserProfile
import org.koin.androidx.viewmodel.ext.android.viewModel


class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private val args: ProfileFragmentArgs by navArgs()

    private val PICK_IMAGE = 0

    private val viewModel: ProfileViewModel by viewModel()
    private lateinit var binding: FragmentProfileBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileBinding.bind(view)
        binding?.viewModel = viewModel

        viewModel.subscribe().observe(viewLifecycleOwner, {
            renderData(it)
        })

        binding.button.setOnClickListener {
            openGalleryForImage()
        }

        viewModel.getUserProfileFromDb(args.uid)

    }

    private fun openGalleryForImage() {
        val getIntent = Intent(Intent.ACTION_GET_CONTENT)
        getIntent.type = "image/*"

        val pickIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        pickIntent.type = "image/*"

        val chooserIntent = Intent.createChooser(getIntent, "Select Image")
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(pickIntent))

        startActivityForResult(chooserIntent, PICK_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == PICK_IMAGE){
            binding.imageView3.setImageURI(data?.data) // handle chosen image
        }
    }

    private fun renderData(appState: AppState?) {
        when (appState){
            is AppState.Empty -> {
            }
            is AppState.Success<*> -> {
                val userProfile: UserProfile?
                when (appState.data) {
                    is UserProfile -> {
                        userProfile = appState.data
                        Log.d("TAG123", "renderData:$userProfile ")
                        binding.profileName.text = userProfile.name.toString()
                        binding.profileMaster.text = userProfile.master.toString()
                        binding.profileClient.text = userProfile.client.toString()
                    }
                }

            }
            is AppState.Loading -> {
            }
            is AppState.Error -> {
            }
            is AppState.SystemMessage -> {
            }
        }
    }

}