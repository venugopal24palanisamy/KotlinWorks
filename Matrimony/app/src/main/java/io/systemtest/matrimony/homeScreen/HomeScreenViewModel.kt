package io.systemtest.matrimony.homeScreen

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import io.systemtest.matrimony.homeScreen.Modal.ImagesList
import io.systemtest.matrimony.R
import io.systemtest.matrimony.appDatabase.Dao.ProfilesDao
import io.systemtest.matrimony.appDatabase.tables.ProfileTables
import io.systemtest.matrimony.Utility.subscribeOnBackground

class HomeScreenViewModel() : ViewModel() {


    var liveDataProfileTables: MutableLiveData<List<ProfileTables>> =
        MutableLiveData<List<ProfileTables>>()

    var success: MutableLiveData<String> = MutableLiveData()

    fun getAddedSuccess(): MutableLiveData<String> {

        return success;
    }

    fun AddProfileDetails(
        ctx: Context,
        database: ProfilesDao,
        profileImagesList: ArrayList<ImagesList>
    ) {

        subscribeOnBackground {
            viewModelScope.also {
                database.AddProfiles(
                    ProfileTables(
                        null, "MT001", "ProfileName1",
                        "One " + ctx.resources.getString(R.string.details), "https://img.freepik.com/premium-vector/african-american-woman-avatar-with-glasses-portrait-young-girl-vector-illustration-face_217290-363.jpg?w=740",Gson().toJson(profileImagesList)
                    )
                )

                database.AddProfiles(
                    ProfileTables(
                        null, "MT002", "ProfileName2",
                        "Two " +ctx.resources.getString(R.string.details)+ctx.resources.getString(R.string.details)+ctx.resources.getString(R.string.details)+ctx.resources.getString(R.string.details)+ctx.resources.getString(R.string.details)+ctx.resources.getString(R.string.details), "https://img.freepik.com/premium-vector/beautiful-women-excited-expressions_96037-110.jpg?w=1380",Gson().toJson(profileImagesList)
                    )
                )

                database.AddProfiles(
                    ProfileTables(
                        null, "MT003", "ProfileName3",
                        "Three " +ctx.resources.getString(R.string.details),"https://img.freepik.com/premium-vector/portrait-young-beautiful-woman-profile-with-long-flowing-hair_478440-102.jpg?size=338&ext=jpg&ga=GA1.2.1002440564.1588571494", Gson().toJson(profileImagesList)
                    )
                )

                database.AddProfiles(
                    ProfileTables(
                        null,
                        "MT004",
                        "ProfileName4",
                        "Four " + ctx.resources.getString(R.string.details),"https://img.freepik.com/free-vector/flat-hand-drawn-female-team-leader_52683-55365.jpg?size=338&ext=jpg&ga=GA1.2.1002440564.1588571494",
                        Gson().toJson(profileImagesList)
                    )
                )

                database.AddProfiles(
                    ProfileTables(
                        null,
                        "MT005",
                        "ProfileName5",
                        "Five " + ctx.resources.getString(R.string.details),"https://img.freepik.com/premium-vector/beautiful-young-lady-doctor-vector-illustration_96037-31.jpg?size=626&ext=jpg&ga=GA1.2.1002440564.1588571494",
                        Gson().toJson(profileImagesList)
                    )
                )

                success.postValue("added")
            }
        }

    }


    fun GetProfileDetails(): MutableLiveData<List<ProfileTables>> {

        return liveDataProfileTables
    }


    fun getAllProfiles(database: ProfilesDao) {
        subscribeOnBackground {
            liveDataProfileTables.postValue(database.getAllProfiles())
        }


    }

}