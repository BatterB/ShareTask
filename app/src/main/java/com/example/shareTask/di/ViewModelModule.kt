package com.example.shareTask.di

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule(private val context: Context) {
    @Provides
    fun provideContext() : Context{
        return context
    }


}