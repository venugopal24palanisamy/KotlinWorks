package io.systemtest.matrimony.appDatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import io.systemtest.matrimony.appDatabase.Dao.ProfilesDao
import io.systemtest.matrimony.appDatabase.tables.ProfileTables

@Database(entities = [ProfileTables::class], version = 1)
abstract class AppDatabase() : RoomDatabase() {

   abstract fun profile_dao(): ProfilesDao

}