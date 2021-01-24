package com.tom.template.di.component

import android.app.Application
import com.tom.base.BaseModule
import com.tom.template.App
import com.tom.template.di.module.ActivityBuilder
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AndroidInjectionModule::class,
        BaseModule::class,
        ActivityBuilder::class
    ]
)
interface AppComponent : AndroidInjector<App> {
    override fun inject(instance: App?)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }
}