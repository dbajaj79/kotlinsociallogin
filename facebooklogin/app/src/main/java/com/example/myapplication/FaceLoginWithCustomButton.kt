package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.myapplication.databinding.ActivityFaceLoginWithCustomButtonBinding
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import java.util.*

class FaceLoginWithCustomButton : AppCompatActivity() {


    lateinit var activityFaceLoginWithCustomButtonBinding: ActivityFaceLoginWithCustomButtonBinding
    lateinit var callbackManager: CallbackManager
    val key_public_profile = "public_profile"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityFaceLoginWithCustomButtonBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_face_login_with_custom_button)
        callbackManager = CallbackManager.Factory.create()
        activityFaceLoginWithCustomButtonBinding.btnLogin.setOnClickListener(MyClickListener())
        setListenerWithFaceCallBackListener()

    }

    fun makeFaceLoginCall()
    {
        LoginManager.getInstance().logInWithReadPermissions(this,Arrays.asList(key_public_profile))

    }
    fun setListenerWithFaceCallBackListener()
    {
            LoginManager.getInstance().registerCallback(callbackManager,MyFaceLoginCallBack())
    }

    class MyFaceLoginCallBack:FacebookCallback<LoginResult>
    {
        override fun onSuccess(result: LoginResult?) {
            val profile = Profile.getCurrentProfile()
            println(profile.firstName)
            println(profile.lastName)

        }

        override fun onCancel() {
        }

        override fun onError(error: FacebookException?) {
        }

    }

   inner class MyClickListener:View.OnClickListener
    {
        override fun onClick(p0: View?) {
            makeFaceLoginCall()

        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode,resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)

    }
}
