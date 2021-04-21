package com.nick_sib.beauty_radar.ui.enter_code

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.nick_sib.beauty_radar.R
import com.nick_sib.beauty_radar.SingletonUID
import com.nick_sib.beauty_radar.data.entites.UserMaster
import com.nick_sib.beauty_radar.data.state.AppState
import com.nick_sib.beauty_radar.databinding.FragmentEnterCodeBinding
import com.nick_sib.beauty_radar.extension.findNavController
import com.nick_sib.beauty_radar.ui.sign_up.SignUpFragment
import com.nick_sib.beauty_radar.ui.utils.USER_IS_DISABLE_IN_DB
import com.nick_sib.beauty_radar.ui.utils.USER_IS_ENABLE_IN_DB
import org.koin.androidx.viewmodel.ext.android.viewModel

class EnterCodeFragment : Fragment(R.layout.fragment_enter_code) {

    private val viewModel: EnterCodeViewModel by viewModel()
    private var binding: FragmentEnterCodeBinding? = null
    private val args: EnterCodeFragmentArgs by navArgs()

    private lateinit var uid: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentEnterCodeBinding.bind(view)

        viewModel.subscribe().observe(viewLifecycleOwner, { renderData(it) })
        binding?.viewModel = viewModel
        binding?.enterCodeFragmentTvInfo?.text =
            getString(R.string.text_help_info_phone, "+7 ${args.phone}")
        initListener()
        uid = SingletonUID.getInstance()!!.getUID().toString()
    }

    private fun initListener(){
        binding?.enterCodeFragmentIvBackTo?.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Empty -> {}
            is AppState.Success<*> -> {
                binding?.fragmentAuthLoadingDialog?.root?.isGone = true
                val data: UserMaster? = appState.data as? UserMaster
                data?.run { viewModel.checkUserInDB(uid) }
                when (appState.data as? String) {
                    USER_IS_ENABLE_IN_DB -> {
                        findNavController().navigate(EnterCodeFragmentDirections.actionEnterCodeFragmentToLogoutFragment())
                    }
                    USER_IS_DISABLE_IN_DB -> {
                        findNavController().navigate(EnterCodeFragmentDirections.actionEnterCodeFragmentToSignUpFragment(uid))
                    }
                    else -> {}
                }
            }

            is AppState.Loading -> {
                binding?.fragmentAuthLoadingDialog?.root?.isGone = false
            }
            is AppState.Error -> {
                binding?.fragmentAuthLoadingDialog?.root?.isGone = true
                viewModel.codeError()
                when (appState.error) {

                    else -> toast(appState.error.message ?: "")
                }
            }
            is AppState.SystemMessage -> {

            }
        }
    }

    private fun toast(text: String) {
        Toast.makeText(activity, text, Toast.LENGTH_SHORT).show()
    }

}