package com.sizran.railvaycompany.db
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.sizran.railvaycompany.entity.Train

@Dao
interface TrainDao {
    @Query("SELECT * FROM trains")
    fun getAllTrains(): List<Train>

    @Insert
    fun insertTrain(train: Train)

    @Delete
    fun deleteTrain(train: Train)
}
