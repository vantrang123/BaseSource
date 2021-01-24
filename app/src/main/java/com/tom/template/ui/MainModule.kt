package com.tom.template.ui

import com.tom.template.di.scope.FragmentScoped
import com.tom.template.fragment.PhotoListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainModule {
    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributePhotoListFragment(): PhotoListFragment
}