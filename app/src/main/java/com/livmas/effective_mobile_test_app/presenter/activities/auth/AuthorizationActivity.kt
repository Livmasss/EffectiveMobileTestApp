package com.livmas.effective_mobile_test_app.presenter.activities.auth

import android.content.Intent
import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.util.TypedValue
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.livmas.effective_mobile_test_app.R
import com.livmas.effective_mobile_test_app.databinding.ActivityAuthorizationBinding
import com.livmas.effective_mobile_test_app.presenter.activities.MainActivity
import java.util.regex.Pattern

class AuthorizationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthorizationBinding
    private val viewModel: AuthorizationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAuthorizationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNameInput()
        setupLastnameInput()
        setupPhoneInput()
        setupObservers()

        setupLoginButton()
    }

    private fun setupLoginButton() {
        binding.apply {
            bLogin.setOnClickListener {
                if (!checkIsAllInputValid())
                    return@setOnClickListener

                viewModel.login(
                    TIETName.text.toString(),
                    TIETLastname.text.toString(),
                    TIETPhone.text.toString()
                )

                nextActivity()
            }
        }
    }

    private fun nextActivity() = startActivity(
            Intent(this@AuthorizationActivity, MainActivity::class.java))

    private fun setupNameInput() {
        binding.apply {
            TIETName.doOnTextChanged { text, _, _, _ ->
                TILName.error = getTextValidationErrorMessage(text)
                checkAvailableAndChangeButton(binding.TILPhone.error)
            }
        }
    }

    private fun setupLastnameInput() {
        binding.TIETLastname.doOnTextChanged { text, _, _, _ ->
            binding.TILLastname.error = getTextValidationErrorMessage(text)
            checkAvailableAndChangeButton(binding.TILPhone.error)
        }
    }

    private fun setupPhoneInput() {
        binding.TIETPhone.doOnTextChanged { text, _, _, _ ->
            binding.TILPhone.error = getPhoneValidationErrorMessage(text)
            checkAvailableAndChangeButton(binding.TILPhone.error)
        }
        binding.TIETPhone.addTextChangedListener(PhoneNumberFormattingTextWatcher())
    }

    private fun setupObservers() {
        setupAuthorizationObserver()
        binding.apply {
            viewModel.apply {
                name.observe(this@AuthorizationActivity) {
                    TIETName.setText(it)
                }
                lastname.observe(this@AuthorizationActivity) {
                    TIETLastname.setText(it)
                }
                phone.observe(this@AuthorizationActivity) {
                    TIETPhone.setText(it)
                }
                isButtonActive.observe(this@AuthorizationActivity) {
                    changeButtonColor(if (it)
                        androidx.appcompat.R.attr.colorPrimary
                    else
                        com.google.android.material.R.attr.colorPrimaryVariant
                    )
                }
            }
        }
    }

    private fun setupAuthorizationObserver() {
//        viewModel.isAuthed.observe(this) {
//            if (it)
//                nextActivity()
//        }
    }

    private fun checkAvailableAndChangeButton(error: CharSequence?) =
        viewModel.isButtonActive.postValue(error == null && checkIsAllInputValid())

    private fun changeButtonColor(attrId: Int) {
        val tvColor = TypedValue()
        theme.resolveAttribute(attrId, tvColor, true)
        binding.bLogin.setBackgroundColor(tvColor.data)
    }

    private fun getTextValidationErrorMessage(text: CharSequence?): String? {
        getInputEmptyOrNullErrorMessage(text).let {
            if (it != null)
                return it
        }

        val matcher = Pattern.compile("[а-яё]+").matcher(text.toString().lowercase())
        return if (matcher.matches())
            null
        else
            resources.getString(R.string.only_cyrillic_error)
    }

    private fun getPhoneValidationErrorMessage(text: CharSequence?): String? {
        getInputEmptyOrNullErrorMessage(text).let {
            if (it != null)
                return it
        }

        return if (text!!.count { c -> c.isDigit() } != 10)
            resources.getString(R.string.phone_length_error)
        else
            null
    }

    private fun getInputEmptyOrNullErrorMessage(text: CharSequence?): String? =
        text.let {
            if (it == null)
                return@let resources.getString(R.string.input_empty_error)

            if (it.isEmpty())
                resources.getString(R.string.input_empty_error)
            else
                null
        }


    private fun checkIsAllInputValid(): Boolean =
        with(binding) {
            TILName.error == null && TILLastname.error == null && TILPhone.error == null
        }
}