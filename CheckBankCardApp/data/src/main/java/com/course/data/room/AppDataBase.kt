package com.course.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [BinInfoEntity::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun binInfoDao(): BinInfoDao

}