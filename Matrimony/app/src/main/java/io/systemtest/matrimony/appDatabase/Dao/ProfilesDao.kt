package io.systemtest.matrimony.appDatabase.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.systemtest.matrimony.appDatabase.tables.ProfileTables

@Dao
interface ProfilesDao {

    @Insert
    fun AddProfiles(profileTables: ProfileTables)

    @Query("select * from ProfileTables")
    fun getAllProfiles(): List<ProfileTables>

}