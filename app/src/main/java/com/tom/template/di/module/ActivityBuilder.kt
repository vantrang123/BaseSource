package com.tom.template.di.module

import com.tom.template.activity.MainActivity
import com.tom.template.di.scope.ActivityScope
import com.tom.template.ui.MainModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {
    @ActivityScope
    @ContributesAndroidInjector(modules = [MainModule::class])
    internal abstract fun contributeMainActivity(): MainActivity
}