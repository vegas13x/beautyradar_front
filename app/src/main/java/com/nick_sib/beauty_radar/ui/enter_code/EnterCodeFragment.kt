package com.nick_sib.beauty_radar.ui.enter_code


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import com.nick_sib.beauty_radar.R
import com.nick_sib.beauty_radar.data.state.AppState
import com.nick_sib.beauty_radar.databinding.FragmentEnterCodeBinding
import com.nick_sib.beauty_radar.ui.logout.LogoutFragment
import com.nick_sib.beauty_radar.ui.utils.AUTH_SECCES_OPEN_NEXT_SCREEN
import org.koin.androidx.viewmodel.ext.android.viewModel

class EnterCodeFragment : Fragment(R.layout.fragment_enter_code) {

    companion object {
        fun newInstance() = EnterCodeFragment()
    }

    private val viewModel: EnterCodeViewModel by viewModel()
    private lateinit var binding: FragmentEnterCodeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentEnterCodeBinding.bind(view)

        viewModel.subscribe(viewLifecycleOwner).observe(viewLifecycleOwner, { renderData(it) })

        binding.enterCodeFragmentBtnGo.setOnClickListener {
            viewModel.codeEntered(binding.enterCodeFragmentEtEntryFieldCode.text.toString())
        }

    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success<*> -> {

            }
            is AppState.Loading -> {
                when (appState.progress) {
                    AUTH_SECCES_OPEN_NEXT_SCREEN -> {
                        activity?.supportFragmentManager?.beginTransaction()
                            ?.replace(
                                R.id.main_activity_container,
                                LogoutFragment.newInstance()
                            )
                            ?.addToBackStack("Logout")?.commit()
                    }
                }
            }
            is AppState.Error -> {
                toast(appState.error)
            }
            is AppState.SystemMessage -> {

            }
        }
    }

    private fun toast(text: String) {
        Toast.makeText(activity, text, Toast.LENGTH_SHORT).show()
    }

}