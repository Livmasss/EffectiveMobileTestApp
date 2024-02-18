package com.livmas.profile.fragments.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.livmas.effective_mobile_test_app.presenter.fragments.profile.ProfileViewModel
import com.livmas.profile.R
import com.livmas.profile.databinding.FragmentProfileBinding
import com.livmas.ui.SendingFragment

class ProfileFragment : SendingFragment() {

    private val viewModel: ProfileViewModel by viewModels()
    private lateinit var binding: FragmentProfileBinding

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

        setupButtons()
    }

    private fun setupButtons() {
        binding.apply {
            setTitleAndSubtitle(binding.lblProfile, com.livmas.ui.R.drawable.ic_profile_page,"Имя Фамилия", "+7 000 000 00 00")
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

    private fun setTitle(lbl: View, iconId: Int, text: String) {
        lbl.findViewById<TextView>(com.livmas.ui.R.id.tvLblTitle).text = text
        lbl.findViewById<ImageView>(com.livmas.ui.R.id.ivLblIcon).setImageResource(iconId)
    }
    private fun setTitleAndSubtitle(lbl: View, iconId: Int, title: String, sTitle: String) {
        setTitle(lbl, iconId, title)
        lbl.findViewById<TextView>(com.livmas.ui.R.id.tvLblSubtitle).text = sTitle
    }
    private fun setTitleWithoutSubtitle(lbl: View, iconId: Int, title: String) {
        setTitle(lbl, iconId, title)
        lbl.findViewById<TextView>(com.livmas.ui.R.id.tvLblSubtitle).visibility = View.GONE
    }
}