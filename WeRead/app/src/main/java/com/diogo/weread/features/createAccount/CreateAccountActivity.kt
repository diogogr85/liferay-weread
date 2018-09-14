package com.diogo.weread.features.createAccount

import com.diogo.weread.BuildConfig
import com.diogo.weread.R
import com.diogo.weread.features.base.BaseActivity
import com.diogo.weread.utils.validateField
import kotlinx.android.synthetic.main.activity_create_account.*
import org.kodein.di.generic.instance

class CreateAccountActivity: BaseActivity<CreateAccountView>(), CreateAccountView {

    override val layoutId = R.layout.activity_create_account

    override val presenter: CreateAccountPresenter by instance()

    override fun setPresenter() {
        presenter.bindView(this)
    }

    override fun onCreate() {
        title = "Create Account"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        createAccountButton.setOnClickListener {
            if (isFormValid()) {
                presenter.createAccount(createAccountNameEditText.text.toString(),
                        createAccountEmailEditText.text.toString(),
                        createAccountPasswordEditText.text.toString())
            }
        }

        clearFocusListeners()
    }

    /**************/
    /** Callbacks */
    /**************/
    override fun onCreateAccountSuccess() {
        finish()
    }

    /**********/
    /** Utils */
    /**********/
    private fun isFormValid(): Boolean {
        val errorMessage = getString(R.string.error_required_field_text)

        return createAccountNameEditText.validateField(errorMessage) and
                createAccountEmailEditText.validateField(errorMessage) and
                createAccountPasswordEditText.validateField(errorMessage)
    }

    private fun clearFocusListeners() {
        createAccountNameEditText.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                createAccountNameEditText.error = null
            }
        }

        createAccountEmailEditText.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                createAccountEmailEditText.error = null
            }
        }

        createAccountPasswordEditText.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                createAccountPasswordEditText.error = null
            }
        }
    }

}