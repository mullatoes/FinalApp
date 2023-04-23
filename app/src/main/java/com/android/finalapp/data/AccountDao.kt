package com.android.finalapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.android.finalapp.account.entities.Admin
import com.android.finalapp.account.entities.User

@Dao
interface AccountDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAdmin(admin: Admin)

    @Query("SELECT * FROM users WHERE email = :email AND password = :password")
    suspend fun getUserByEmailAndPassword(email: String, password: String): User?

    @Query("SELECT * FROM admins WHERE email = :email AND password = :password")
    suspend fun getAdminByEmailAndPassword(email: String, password: String): Admin?

}