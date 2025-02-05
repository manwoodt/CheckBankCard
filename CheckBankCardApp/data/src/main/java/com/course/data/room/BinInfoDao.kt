package com.course.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BinInfoDao {
    @Insert
    // TODO обработать конфликтные операции
    suspend fun insertBinInfo(binInfo: BinInfoEntity)

    @Query("SELECT * FROM bin_info ORDER BY uid DESC")
    suspend fun getAllBinInfo():List<BinInfoEntity>

    @Delete
   suspend fun deleteBinInfo(binInfo:BinInfoEntity)

   @Query("DELETE FROM bin_info")
   suspend fun deleteAll()

 }