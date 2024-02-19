package com.livmas.effective_mobile_test_app

import com.livmas.data.dataModule
import org.koin.dsl.module

val appModule = module {
    includes(
        dataModule
    )
}