package com.example.applerecipe.di

import android.content.Context
import com.example.applerecipe.presentation.BaseApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


//The Singleton component state that the dependency injection will be alive as long as
//the application is alive. SingletonComponent was named before as ApplicationComponent
//There is a documentation for this. Example, there are ViewModelComponent, ActivityComponent, etc.
@Module
@InstallIn(SingletonComponent::class)
object AppModule{
    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): BaseApplication {
        return app as BaseApplication
    }

    @Singleton
    @Provides
    fun provideRandomString(): String{
        return "Hey look, Random EREAEWRIAERARW"
    }
}