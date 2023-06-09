package com.frapp.weatherforecast.di.components

import dagger.Module


@Module(
    subcomponents = [
        ActivitySubComponent::class,
        FragmentSubComponent::class
    ]
)
class AppSubComponents