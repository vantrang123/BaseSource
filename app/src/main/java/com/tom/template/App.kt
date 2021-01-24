package com.tom.template

import com.tom.base.BaseApp
import com.tom.template.di.component.DaggerAppComponent
import com.tom.template.di.networkModules
import com.tom.template.di.repositoryModules
import com.tom.template.di.useCaseModules
import com.tom.template.di.viewModels
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : BaseApp() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                viewModels +
                        repositoryModules +
                        useCaseModules +
                        networkModules
            )
        }
        instance = this
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(this).also { appComponent ->
            appComponent.inject(this@App)
        }
    }

    companion object {
        lateinit var instance: App
            private set
    }
}