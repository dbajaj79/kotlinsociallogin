package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.myapplication.databinding.ActivityMainBinding
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var activitymainbinding: ActivityMainBinding
    lateinit var callbackManager: CallbackManager
    val KEY_EMAIL = "email"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activitymainbinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        callbackManager = CallbackManager.Factory.create()
        activitymainbinding.loginButton.setPermissions(Arrays.asList(KEY_EMAIL))
        activitymainbinding.loginButton.registerCallback(callbackManager, MyFaceBookCallBackListener())

    }

    class MyFaceBookCallBackListener : FacebookCallback<LoginResult> {
        override fun onSuccess(result: LoginResult?) {
        }

        override fun onCancel() {
        }

        override fun onError(error: FacebookException?) {
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }
}
