package com.nick_sib.beauty_radar.ui.enter_code


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import com.nick_sib.beauty_radar.R
import com.nick_sib.beauty_radar.data.entites.UserMaster
import com.nick_sib.beauty_radar.data.state.AppState
import com.nick_sib.beauty_radar.databinding.FragmentEnterCodeBinding
import com.nick_sib.beauty_radar.ui.logout.LogoutFragment
import com.nick_sib.beauty_radar.ui.masterclient.MasterClientFragment
import com.nick_sib.beauty_radar.ui.utils.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class EnterCodeFragment : Fragment(R.layout.fragment_enter_code) {

    companion object {
        fun newInstance() = EnterCodeFragment()
    }

    private val viewModel: EnterCodeViewModel by viewModel()
    private var binding: FragmentEnterCodeBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentEnterCodeBinding.bind(view)

        viewModel.subscribe(viewLifecycleOwner).observe(viewLifecycleOwner, {
            Log.d(TAG_DEBAG, "EnterCodeFragment onViewCreated: $it")
            renderData(it) })


        binding?.enterCodeFragmentBtnGo?.setOnClickListener {
            viewModel.codeEntered(binding?.enterCodeFragmentEtEntryFieldCode?.text.toString())
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG_DEBAG, "EnterCodeFragment onDestroyView: биндинг убит")
        binding = null

    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success<*> -> {
                when (appState.data) {
                    is UserMaster -> appState.data.uid?.let {
                        Log.d(TAG_DEBAG, "EnterCodeFragment renderData: ")
                        viewModel.checkUserInDB(it) }
                    USER_IS_ENABLE_IN_DB ->{
                        AppState.Success<String>(CODE_NULL)
                        requireActivity().supportFragmentManager.beginTransaction()
                            .replace(
                                R.id.main_activity_container,
                                MasterClientFragment.newInstance()
//                                LogoutFragment.newInstance()
                            )
                            .addToBackStack("Logout").commit()
                    }
                    USER_IS_DISABLE_IN_DB -> {
                        toast("no ok")
                    }
                }
            }
            is AppState.Loading -> {
                when (appState.progress) {
                    AUTH_SECCES_OPEN_NEXT_SCREEN -> {
//                        requireActivity().supportFragmentManager.beginTransaction()
//                            .replace(
//                                R.id.main_activity_container,
//                                LogoutFragment.newInstance()
//                            )
//                            .addToBackStack("Logout").commit()
                    }
                }
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
        Toast.makeText(activity, text, Toast.LENGTH_SHORT).show()
    }

}