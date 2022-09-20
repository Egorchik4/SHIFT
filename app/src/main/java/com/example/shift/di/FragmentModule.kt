package com.example.shift.di

import android.content.SharedPreferences
import com.example.shift.usecases.GetInSharedPreference
import com.example.shift.usecases.PutInSharedPreference
import com.example.shift.usecases.ShowAlertDialog
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import javax.inject.Singleton

@Module
@InstallIn(FragmentComponent::class)
class FragmentModule {

    @Provides
    fun provideShowAlertDialog(): ShowAlertDialog {
        return ShowAlertDialog()
    }

    @Provides
    fun provideGetInSharedPreference(sharedPreferences: SharedPreferences): GetInSharedPreference {
        return GetInSharedPreference(sharedPreferences)
    }

    @Provides
    fun providePutInSharedPreference(sharedPreferences: SharedPreferences): PutInSharedPreference {
        return PutInSharedPreference(sharedPreferences)
    }
}