package io.systemtest.matrimony.appDatabase.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ProfileTables")
data class ProfileTables(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int?,
    @ColumnInfo(name = "profile_id")
    var profile_id: String?,
    @ColumnInfo(name = "profile_name")
    var profile_name: String?,
    @ColumnInfo(name = "profile_detail")
    var profile_detail: String?,
    @ColumnInfo(name = "profile_images")
    var profile_images: String?,
    @ColumnInfo(name = "profile_imagesList")
    var profile_imagesList: String?
)



