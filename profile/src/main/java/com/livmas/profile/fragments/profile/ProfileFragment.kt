package com.livmas.profile.fragments.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.livmas.domain.AuthedUserHolder
import com.livmas.domain.usecases.LogoutUseCase
import com.livmas.profile.R
import com.livmas.profile.databinding.FragmentProfileBinding
import com.livmas.ui.SendingFragment
import com.livmas.ui.databinding.LinearButtonLayoutBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ProfileFragment : SendingFragment() {

    private val viewModel: ProfileViewModel by viewModels()
    private lateinit var binding: FragmentProfileBinding
    private val logoutUseCase = LogoutUseCase(AuthedUserHolder.holdingAuthedUser)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        title = resources.getString(com.livmas.ui.R.string.title_profile_page)

        setupButtonsStates()
        setupListeners()
    }

    private fun setupButtonsStates() {
        binding.apply {
            setTitleAndSubtitle(binding.lblAccount, com.livmas.ui.R.drawable.ic_profile_page,"Имя Фамилия", "+7 000 000 00 00")
            setTitleAndSubtitle(binding.lblLiked,
                R.drawable.ic_outline_like,
                resources.getString(R.string.liked_button),
                resources.getString(R.string.liked_button_count, 1))
            setTitleWithoutSubtitle(binding.lblShops, R.drawable.ic_shops, resources.getString(R.string.shops_button))
            setTitleWithoutSubtitle(binding.lblFeedback, R.drawable.ic_feedback, resources.getString(R.string.feedback_button))
            setTitleWithoutSubtitle(binding.lblOffer, R.drawable.ic_offer, resources.getString(R.string.offer_button))
            setTitleWithoutSubtitle(binding.lblReturn, R.drawable.ic_return, resources.getString(R.string.return_button))
        }
    }

    private fun setTitle(lbl: LinearButtonLayoutBinding, iconId: Int, text: String) {
        lbl.tvLblTitle.text = text
        lbl.ivLblIcon.setImageResource(iconId)
    }
    private fun setTitleAndSubtitle(lbl: LinearButtonLayoutBinding, iconId: Int, title: String, sTitle: String) {
        setTitle(lbl, iconId, title)
        lbl.tvLblSubtitle.text = sTitle
    }
    private fun setTitleWithoutSubtitle(lbl: LinearButtonLayoutBinding, iconId: Int, title: String) {
        setTitle(lbl, iconId, title)
        lbl.tvLblSubtitle.visibility = View.GONE
    }
    private fun setupListeners() {
        setupLikedListener()
        setupAccountListener()
    }
    private fun setupLikedListener() {
        binding.lblLiked.root.setOnClickListener {
            navController?.navigate(R.id.action_navigation_profile_main_to_navigation_profile_liked2)
        }
    }
    private fun setupAccountListener() {
        binding.lblAccount.root.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                logoutUseCase.execute()
            }
        }
    }

//    private fun openAuthActivity() {
//        val intent = Intent(
//            requireContext(), AuthActivity
//        )
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
//    }
}