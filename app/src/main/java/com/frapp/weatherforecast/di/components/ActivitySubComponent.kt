package com.frapp.weatherforecast.di.components

import com.frapp.weatherforecast.di.ActivityScope
import com.frapp.weatherforecast.presentation.MainActivity
import dagger.Subcomponent


@ActivityScope
@Subcomponent()
interface ActivitySubComponent {

    // Factory that is used to create instances of this subcomponent
    @Subcomponent.Factory
    interface Factory {
        fun create(): ActivitySubComponent
    }

    fun inject(mainActivity: MainActivity)

}