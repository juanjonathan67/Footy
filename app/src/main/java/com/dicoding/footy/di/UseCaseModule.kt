package com.dicoding.footy.di

import com.dicoding.footy.domain.interactor.FootyInteractor
import com.dicoding.footy.domain.useCase.FootyUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    @Singleton
    abstract fun bindFootyUseCase(
        footyInteractor: FootyInteractor,
    ): FootyUseCase
}