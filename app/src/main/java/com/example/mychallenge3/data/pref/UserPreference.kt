package com.example.mychallenge3.data.pref

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.mychallenge3.data.model.UserModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class UserPreference private constructor(context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        "session", Context.MODE_PRIVATE
    )

    fun saveSession(user: UserModel) {
        sharedPreferences.edit {
            putString(EMAIL_KEY, user.email)
            putString(TOKEN_KEY, user.token)
            putBoolean(IS_LOGIN_KEY, true)
        }
    }

    fun getSession(): Flow<UserModel> {
        return flow {
            val email = sharedPreferences.getString(EMAIL_KEY, "") ?: ""
            val token = sharedPreferences.getString(TOKEN_KEY, "") ?: ""
            val isLogin = sharedPreferences.getBoolean(IS_LOGIN_KEY, false)
            emit(UserModel(email, token, isLogin))
        }
    }

    fun logout() {
        sharedPreferences.edit {
            clear()
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: UserPreference? = null

        private const val EMAIL_KEY = "email"
        private const val TOKEN_KEY = "token"
        private const val IS_LOGIN_KEY = "isLogin"

        fun getInstance(context: Context): UserPreference {
            return INSTANCE ?: synchronized(this) {
                val instance = UserPreference(context)
                INSTANCE = instance
                instance
            }
        }
    }
}
