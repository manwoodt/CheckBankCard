package com.course.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.course.domain.model.BinInfo

@Dao
interface BinDao {
    @Insert
    // TODO обработать конфликтные операции
    suspend fun insertBinInfo(binInfo: BinInfoEntity)

    @Query("SELECT * FROM bin_info ORDER BY uid")
    suspend fun getAllBinInfo():List<BinInfoEntity>

    @Delete
   suspend fun deleteBinInfo(binInfo:BinInfoEntity)

   @Query("DELETE FROM bin_info")
   suspend fun deleteAll()

 }