package com.example.shift.di

import android.content.Context
import android.content.SharedPreferences
import com.example.shift.storage.SharedPrefStorage
import com.example.shift.usecases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SingletonModule {

    @Provides
    @Singleton
    fun provideValidationName(): ValidationName{
        return ValidationName()
    }

    @Provides
    @Singleton
    fun provideValidationDate(): ValidationDate {
        return ValidationDate()
    }

    @Provides
    @Singleton
    fun provideValidationPassword(): ValidationPassword {
        return ValidationPassword()
    }

    @Provides
    @Singleton
    fun provideMatchingPassword(): MatchingPassword {
        return MatchingPassword()
    }

    @Provides
    @Singleton
    fun provideCheckEmpty(): CheckEmpty {
        return CheckEmpty()
    }

    @Provides
    @Singleton
    fun provideSharedPrefStorage(@ApplicationContext context: Context): SharedPreferences {
        return SharedPrefStorage(context).prefStorage
    }



}