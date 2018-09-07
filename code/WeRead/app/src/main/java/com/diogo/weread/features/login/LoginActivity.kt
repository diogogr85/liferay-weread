package com.diogo.weread.features.login

import android.content.Intent
import com.diogo.weread.R
import com.diogo.weread.features.MainActivity
import com.diogo.weread.features.base.BaseActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.kodein.di.generic.instance


class LoginActivity: BaseActivity<LoginView>(), LoginView {

    override val layoutId: Int = R.layout.activity_login

    override val presenter: LoginPresenter by instance()

    override fun setPresenter() {
        presenter.bindView(this)
    }

    override fun onCreate() {
        loginButton.setOnClickListener {
            if (isFormValid()) {
                startActivity(Intent(applicationContext, MainActivity::class.java))
            }
        }

        loginCreateAccountButton.setOnClickListener {
            //TODO - create account screen
        }

        loginEmailEditText.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                loginEmailEditText.error = null
            }
        }

        loginPasswordEditText.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                loginPasswordEditText.error = null
            }
        }
    }

    /**************/
    /** Callbacks */
    /**************/
    override fun onLoginSuccess() {
        //TODO
    }

    override fun onError() {
        //TODO
    }

    /**********/
    /** Utils */
    /**********/
    private fun isFormValid(): Boolean {
        var isValid = true

        if (loginEmailEditText.text.isEmpty()) {
            isValid = false
            loginEmailEditText.error = getString(R.string.error_required_field_text)
        }

        if (loginPasswordEditText.text.isEmpty()) {
            isValid = false
            loginPasswordEditText.error = getString(R.string.error_required_field_text)
        }

        return isValid
    }

}