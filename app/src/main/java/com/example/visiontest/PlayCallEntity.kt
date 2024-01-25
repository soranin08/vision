//package com.example.visiontest
//
//import androidx.room.ColumnInfo
//import androidx.room.Dao
//import androidx.room.Entity
//import androidx.room.Insert
//import androidx.room.OnConflictStrategy
//import androidx.room.PrimaryKey
//import androidx.room.Query
//
//class PlayCallEntity {
//    @Entity(tableName = "play_calls")
//    data class PlayCallEntity(
//        @PrimaryKey
//        @ColumnInfo(name = "description")
//        val description: String
//    )
//    @Dao
//    interface PlayCallDao{
//        //データの取得メソッド
//        @Query("SELECT * FROM play_calls")
//        fun loadAllPlayCall(): List<PlayCallEntity>
//
//        //挿入メソッド
//        @Insert(onConflict = OnConflictStrategy.REPLACE)
//        fun savePlayCall(playcallentity: PlayCallEntity)
//    }
//}