package io.systemtest.matrimony.Utility

import io.systemtest.matrimony.gestureScreen.GuestureViewModel
import io.systemtest.matrimony.homeScreen.HomeScreenViewModel
import io.systemtest.matrimony.ProfileDetailsScreen.ProfileDetailViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

var viewModelModule =  module{


    viewModel { HomeScreenViewModel() }
    viewModel { ProfileDetailViewModel() }
    viewModel { GuestureViewModel() }
}