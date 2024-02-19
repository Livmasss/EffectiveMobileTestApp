package com.livmas.profile.adapters

import android.content.res.Resources
import androidx.navigation.NavController
import com.livmas.profile.R
import com.livmas.ui.adapters.ACatalogRecyclerAdapter
import com.livmas.ui.models.PreviewItemModel

class LikedRecyclerAdapter(resources: Resources,
                           data: List<PreviewItemModel>, navController: NavController
) : ACatalogRecyclerAdapter(resources, data, navController) {
    override fun navigate() {
        navController.navigate(R.id.action_navigation_profile_liked_to_navigation_item)
    }
}