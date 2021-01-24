package com.tom.template.di.component

import androidx.databinding.DataBindingComponent
import com.tom.template.di.module.BindingModule
import com.tom.template.di.scope.BindingScope
import dagger.Component

@BindingScope
@Component(
    dependencies = [AppComponent::class], modules = [BindingModule::class]
)
interface BindingComponent : DataBindingComponent {
}