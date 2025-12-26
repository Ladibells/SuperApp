package dev.ladibells.datasource.local.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseDao <in T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(t :T) : Long

    @Delete
    fun delete(t: T) : Int

    @Update
    fun update(t: T) : Int
}