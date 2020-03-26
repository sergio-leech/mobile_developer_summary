package com.example.mobiledevelopersummary.database

import androidx.lifecycle.MutableLiveData
import androidx.room.*


@Dao
interface ContentDatabaseDao {
    @Insert
    suspend fun insert(myContent: MyContent)
    @Update
    suspend fun update(myContent: MyContent)
    @Delete
    suspend fun delete(myContent: MyContent)
    @Query("SELECT * from my_content_table WHERE contentId = :key")
    suspend fun get(key: String): MyContent
    @Query("DELETE FROM my_content_table")
    suspend fun clear()
    @Query("SELECT * FROM my_content_table ORDER BY data DESC")
  suspend fun getAllContents(): List<MyContent>
}