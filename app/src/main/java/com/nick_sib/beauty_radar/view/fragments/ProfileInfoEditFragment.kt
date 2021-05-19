package com.nick_sib.beauty_radar.view.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.nick_sib.beauty_radar.R
import com.nick_sib.beauty_radar.databinding.FragmentProfileInfoEditBinding
import com.nick_sib.beauty_radar.extension.findNavController
import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.model.provider.repository.master.UserMasterProfile
import com.nick_sib.beauty_radar.view_model.ProfileInfoEditViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileInfoEditFragment : Fragment(R.layout.fragment_profile_info_edit) {

    private val viewModel: ProfileInfoEditViewModel by viewModel()
    private lateinit var binding: FragmentProfileInfoEditBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileInfoEditBinding.bind(view)

        viewModel.subscribe().observe(viewLifecycleOwner, {
            renderData(it)
        })

        saveBtn()
        btnBarInit()
        viewModel.getInfoAboutUser()

    }

    private fun saveBtn() {
        binding.finish.setOnClickListener {
            if (binding.nameInput.text.toString() != "" && binding.surnameInput.text.toString() != "" &&
                binding.addressInput.text.toString() != "" && binding.phoneInput.text.toString() != ""
                && binding.dateBirthInput.text.toString() != "" && binding.aboutUrSelfInput.text.toString() != ""
            ) {
                viewModel.setInfoAboutUser(
                    binding.nameInput.text.toString(), binding.surnameInput.text.toString(),
                    binding.addressInput.text.toString(), binding.phoneInput.text.toString(),
                    binding.dateBirthInput.text.toString(), binding.aboutUrSelfInput.text.toString()
                )
                findNavController().navigate(ProfileInfoEditFragmentDirections.actionProfileInfoEditFragmentToProfileInfoFragment())
            } else {
                Toast.makeText(requireContext(), "Заполните все поля!", Toast.LENGTH_LONG).show()
            }
        }

    }

    private fun btnBarInit() {
        binding.fragmentMcBtnNavBar.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_btm_nav_btn_clients -> {
                    findNavController().navigate(ProfileInfoEditFragmentDirections.actionProfileInfoEditFragmentToClientsFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menu_btm_nav_btn_profile -> {
                    findNavController().navigate(ProfileInfoEditFragmentDirections.actionProfileInfoEditFragmentToProfileInfoFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menu_btm_nav_btn_main -> {
                    findNavController().navigate(ProfileInfoEditFragmentDirections.actionProfileInfoEditFragmentToMasterClientsFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                else -> false
            }
        }
    }

    private fun renderData(appState: AppState?) {
        when (appState) {
            is AppState.Success<*> ->
                when (appState.data) {
                    is UserMasterProfile -> {
                        if (appState.data.name != null) {
                            var userMasterProfile = appState.data
                            binding.addressInput.setText(userMasterProfile.address.toString())
                            binding.phoneInput.setText(userMasterProfile.phone.toString())
                            binding.dateBirthInput.setText(userMasterProfile.dateBirthday.toString())
                            binding.aboutUrSelfInput.setText(userMasterProfile.aboutUrself.toString())
                            binding.nameInput.setText(userMasterProfile.name.toString())
                            binding.surnameInput.setText(userMasterProfile.surname.toString())
                        }else{
                            Toast.makeText(requireContext(), "Заполните все поля!", Toast.LENGTH_LONG).show()
                        }
                    }
                }
        }
    }
}