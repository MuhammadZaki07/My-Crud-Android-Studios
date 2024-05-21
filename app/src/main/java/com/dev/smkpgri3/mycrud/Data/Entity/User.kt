package com.dev.smkpgri3.mycrud.Data.Entity

import android.provider.ContactsContract.CommonDataKinds.Phone
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity

data class User (
    @PrimaryKey(autoGenerate = true) var uid : Int? = null,
    @ColumnInfo(name = "full_name") var fullName:String?,
    @ColumnInfo(name= "email") var email: String?,
    @ColumnInfo(name= "phone") var phone: String?
)