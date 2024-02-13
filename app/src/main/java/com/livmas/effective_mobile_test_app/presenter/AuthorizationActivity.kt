package com.livmas.effective_mobile_test_app.presenter

import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.livmas.effective_mobile_test_app.R
import com.livmas.effective_mobile_test_app.databinding.ActivityAuthorizationBinding
import java.util.regex.Pattern

class AuthorizationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthorizationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAuthorizationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        addNameOnTextChanged()
        addLastNameOnTextChanged()
        addPhoneOnTextChanged()
    }

    private fun addNameOnTextChanged() {
        binding.TIETName.doOnTextChanged { text, _, _, _ ->
            binding.TILName.error = textValidationErrorMessage(text)
        }
    }

    private fun addLastNameOnTextChanged() {
        binding.TIETLastname.doOnTextChanged { text, _, _, _ ->
            binding.TILLastname.error = textValidationErrorMessage(text)
        }
    }

    private fun addPhoneOnTextChanged() {
        binding.TIETPhone.addTextChangedListener(PhoneNumberFormattingTextWatcher())
    }


    private fun textValidationErrorMessage(text: CharSequence?): String? {
        if (text == null)
            return null

        val matcher = Pattern.compile("[а-яё]+").matcher(text.toString().lowercase())
        return if (matcher.matches())
            null
        else
            resources.getString(R.string.only_cyrillic_error)
    }
}