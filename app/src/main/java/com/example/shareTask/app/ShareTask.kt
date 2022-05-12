package com.example.shareTask.app

import android.app.Application
import com.example.shareTask.di.AppComponent
import com.example.shareTask.di.DaggerAppComponent
import com.example.shareTask.di.ViewModelModule

class ShareTask : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .viewModelModule(ViewModelModule(this))
            .build()
    }
}