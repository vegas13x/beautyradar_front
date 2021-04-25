package com.nick_sib.beauty_radar.model.room.roomView

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.nick_sib.beauty_radar.R
import com.nick_sib.beauty_radar.model.data.state.AppState
import com.nick_sib.beauty_radar.databinding.FormPageFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class Room: Fragment(R.layout.form_page_fragment) {

    companion object {
        fun newInstance() = Room()
    }

    private val viewModel: RoomViewModel by viewModel()
    private var binding: FormPageFragmentBinding? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FormPageFragmentBinding.bind(view)

        viewModel.getData()

        viewModel.subscribe(viewLifecycleOwner).observe(viewLifecycleOwner, {
            renderData(it)
        })

    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success<*> -> {

            }
            is AppState.Loading -> {


            }
            is AppState.Error -> {
                when (appState.error) {

                    else -> toast(appState.error.message ?: "")
                }
            }
            is AppState.SystemMessage -> {

            }
        }
    }

    private fun toast(text: String) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}