package io.github.sooakim.github.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.sooakim.github.data.RepoRepositoryImpl
import io.github.sooakim.github.domain.RepoRepository
import javax.inject.Singleton

/**
 * Created by sooakim on 06,June,2022
 */
@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Singleton
    @Binds
    fun bindRepoRepository(
        repository: RepoRepositoryImpl
    ): RepoRepository
}