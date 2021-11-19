package com.sample.pokedex.modules

import com.sample.pokedex.data.repository.CloudRepositoryImpl
import com.sample.pokedex.domain.repository.CloudRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModules {

    @Binds
    abstract fun bindsCloudRepositoryRepository(repository: CloudRepositoryImpl): CloudRepository

}
