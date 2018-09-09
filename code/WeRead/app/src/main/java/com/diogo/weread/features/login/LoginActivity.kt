package com.diogo.weread.features.login

import android.content.Intent
import com.diogo.weread.BuildConfig
import com.diogo.weread.R
import com.diogo.weread.features.MainActivity
import com.diogo.weread.features.base.BaseActivity
import com.diogo.weread.features.createAccount.CreateAccountActivity
import com.diogo.weread.utils.validateField
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
                presenter.authenticateUser(loginEmailEditText.text.toString(),
                        loginPasswordEditText.text.toString())
            }
        }

        loginCreateAccountButton.setOnClickListener {
            startActivity(Intent(applicationContext, CreateAccountActivity::class.java))
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

        if (BuildConfig.DEBUG) {
            loginEmailEditText.setText("email@email.com")
            loginPasswordEditText.setText("qwerty2134")
        }
    }

    /**************/
    /** Callbacks */
    /**************/
    override fun onLoginSuccess() {
        startActivity(Intent(applicationContext, MainActivity::class.java))
    }

    /**********/
    /** Utils */
    /**********/
    private fun isFormValid(): Boolean {
        val errorMsg = getString(R.string.error_required_field_text)

        return loginEmailEditText.validateField(errorMsg) and
                loginPasswordEditText.validateField(errorMsg)
    }

}