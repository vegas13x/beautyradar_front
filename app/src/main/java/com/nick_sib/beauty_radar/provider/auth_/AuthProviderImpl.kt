package com.nick_sib.beauty_radar.provider.auth_

import android.app.Activity
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.nick_sib.beauty_radar.data.entites.UserMaster
import com.nick_sib.beauty_radar.data.state.AppState
import com.nick_sib.beauty_radar.ui.utils.AUTH_SECCES_OPEN_NEXT_SCREEN
import com.nick_sib.beauty_radar.ui.utils.CODE_ERROR_GONE_CODE_LAYOUT
import com.nick_sib.beauty_radar.ui.utils.CODE_RECEIVED_VISIBLE_CODE_LAYOUT
import java.util.concurrent.TimeUnit


class AuthProviderImpl(private val authUser: FirebaseAuth) : IAuthProvider {

    private val TAG_SUCCESS_VERIFICATION: String = "AuthPI SUCCESS"
    private val TAG_CODE_SEND: String = "AuthPI CODE SEND"

    private val livedataAuthProvider: MutableLiveData<AppState> = MutableLiveData()
    private lateinit var localVerificationId: String
    private var resendingToken: PhoneAuthProvider.ForceResendingToken? = null

    private val callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks =
        object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
                singnInWithPhoneAuthCredential(phoneAuthCredential)
                Log.d(TAG_SUCCESS_VERIFICATION, "onVerificationCompleted: succes")
            }

            override fun onVerificationFailed(firebaseException: FirebaseException) {
                livedataAuthProvider.value = AppState.Loading(CODE_ERROR_GONE_CODE_LAYOUT)
                livedataAuthProvider.value = AppState.Error(firebaseException.message.toString())
            }

            override fun onCodeSent(
                verifyID: String,
                forceResendingToken: PhoneAuthProvider.ForceResendingToken
            ) {
                super.onCodeSent(verifyID, forceResendingToken)
                Log.d(TAG_CODE_SEND, "onCodeSent: ${verifyID}")
                localVerificationId = verifyID
                resendingToken = forceResendingToken
                livedataAuthProvider.value = AppState.Loading(CODE_RECEIVED_VISIBLE_CODE_LAYOUT)
                livedataAuthProvider.value = AppState.Success(localVerificationId)
            }
        }
    private val currentUser
        get() = authUser.currentUser

    override fun getLiveDataAuthProvider(): LiveData<AppState> {
        return livedataAuthProvider
    }

    override fun singUpEmailAndPasswordUser(email: String, password: String) {
        authUser.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Log.w("ADD-FIREBASE", "Success add user in firebase")
                } else {
                    Log.w("ADD-FIREBASE", "ERROR add user in firebase")
                }
            }
    }

    override fun startPhoneNumberVerification(activity: Activity, phone: String) {
        val options = PhoneAuthOptions.newBuilder(authUser)
            .setPhoneNumber(phone)       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(activity)                 // Activity (for callback binding)
            .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    override fun resentVerificationCode(activity: Activity, phone: String) {
        val options = PhoneAuthOptions.newBuilder(authUser)
            .setPhoneNumber(phone)       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(activity) // Activity (for callback binding)
            .setForceResendingToken(resendingToken)
            .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
            .build()

        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    override fun verifyPhoneNumber(code: String) {
        val credential: PhoneAuthCredential =
            PhoneAuthProvider.getCredential(localVerificationId, code)
        singnInWithPhoneAuthCredential(credential)
    }

    override fun addEmailAndPasswordInCurrentUser(email: String, password: String) {
        val credential = EmailAuthProvider.getCredential(email, password)

        authUser.currentUser.linkWithCredential(credential)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    livedataAuthProvider.value = AppState.Success("Данные пользователя обновлены")
                } else {
                    livedataAuthProvider.value = AppState.Error("Данные пользователя не обновлены!")
                }
            }
    }

    private fun singnInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        authUser.signInWithCredential(credential)
            .addOnCompleteListener {
                livedataAuthProvider.value = AppState.Loading(AUTH_SECCES_OPEN_NEXT_SCREEN)
            }
            .addOnFailureListener {
                livedataAuthProvider.value = AppState.Error(it.message.toString())
            }
    }


}