package com.dev.smkpgri3.mycrud.Data

import android.content.Context
import android.service.autofill.FillContext
import android.service.autofill.UserData
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dev.smkpgri3.mycrud.Data.Dao.UserDao
import com.dev.smkpgri3.mycrud.Data.Entity.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun userDao() : UserDao
    companion object{
        private var instance:AppDatabase? = null
        fun getInstance(context: Context) : AppDatabase{
            if (instance == null ){
                instance=Room.databaseBuilder(context,AppDatabase::class.java, "app-database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return instance!!
        }
    }

}