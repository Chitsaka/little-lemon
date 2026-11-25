package com.example.littlelemon

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RoomDatabase


@Dao
interface MenuDao {
    @Query("SELECT * FROM MenuItemNetwork")
    fun getAllMenuItems(): LiveData<List<MenuItemNetwork>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMenuItems(items: List<MenuItemNetwork>)

    @Query("SELECT (SELECT COUNT(*) FROM MenuItemNetwork) == 0")
    suspend fun isEmpty(): Boolean
}

@Database(entities = [MenuItemNetwork::class], version=2, exportSchema = false)
abstract class MenuDatabase: RoomDatabase() {
    abstract fun menuDao(): MenuDao
}


