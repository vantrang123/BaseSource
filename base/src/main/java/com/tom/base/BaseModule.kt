package com.tom.base

import com.tom.base.util.DefaultErrorFactory
import com.tom.base.util.ErrorFactory
import dagger.Binds
import dagger.Module

@Module
abstract class BaseModule {
    @Binds
    internal abstract fun provideErrorFactory(defaultErrorFactory: DefaultErrorFactory): ErrorFactory
}