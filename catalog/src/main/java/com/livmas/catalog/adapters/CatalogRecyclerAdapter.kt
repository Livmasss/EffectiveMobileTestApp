package com.livmas.catalog.adapters

import android.content.res.Resources
import androidx.navigation.NavController
import com.livmas.catalog.R
import com.livmas.ui.adapters.ACatalogRecyclerAdapter
import com.livmas.ui.models.CatalogItem

class CatalogRecyclerAdapter(
    resources: Resources,
    data: List<CatalogItem>,
    navController: NavController):
    ACatalogRecyclerAdapter(resources, data, navController)
{
    override fun navigate() {
        navController.navigate(R.id.action_catalog_navigation_main_to_catalog_navigation_item)
    }
}