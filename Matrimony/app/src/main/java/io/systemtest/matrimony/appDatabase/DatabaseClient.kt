package io.systemtest.matrimony.appDatabase

import android.content.Context
import androidx.room.Room

class DatabaseClient {

    private var instance: AppDatabase? = null

    lateinit var appDatabase: AppDatabase

    @Synchronized
    fun getDatabaseClient(ctx: Context): AppDatabase {

        if (instance == null)
            instance = Room.databaseBuilder(
                ctx.applicationContext, AppDatabase::class.java, "AppDatabase"
            ).fallbackToDestructiveMigration().build()

        return instance!!
    }

}