package com.example.mobiledevelopersummary.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mobiledevelopersummary.models.Content

@Database(entities = [MyContent::class], version = 3,
    exportSchema = false)
abstract class ContentDatabase : RoomDatabase() {
    abstract val contentDatabaseDao: ContentDatabaseDao
    companion object {
        @Volatile
        private var INSTANCE: ContentDatabase? = null
        fun getInstance(context: Context): ContentDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context,
                        ContentDatabase::class.java,
                        "my_content_folder_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}