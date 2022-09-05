package io.systemtest.matrimony.gestureScreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.systemtest.matrimony.Utility.subscribeOnBackground
import io.systemtest.matrimony.appDatabase.Dao.ProfilesDao
import io.systemtest.matrimony.appDatabase.tables.ProfileTables

class GuestureViewModel() : ViewModel() {


    var liveDataProfileTables: MutableLiveData<List<ProfileTables>> =
        MutableLiveData<List<ProfileTables>>()

    fun GetProfileDetails(): MutableLiveData<List<ProfileTables>> {

        return liveDataProfileTables
    }


    fun getAllProfiles(database: ProfilesDao) {
        subscribeOnBackground {
            liveDataProfileTables.postValue(database.getAllProfiles())
        }


    }
}