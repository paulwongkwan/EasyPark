package prism6.com.easypark.module

import dagger.Module
import dagger.Provides
import prism6.com.easypark.repository.CarparkLocalRepository
import prism6.com.easypark.repository.CarparkRemoteRepository
import prism6.com.easypark.repository.CarparkRepository
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Singleton
    @Provides
    fun provideRemoteRepository() = CarparkRemoteRepository()

    @Singleton
    @Provides
    fun provideLocalepository() = CarparkLocalRepository()

    @Singleton
    @Provides
    fun provideRepository(galleryRemoteRepository: CarparkRemoteRepository,
                          galleryLocalRepository: CarparkLocalRepository
    ) =
        CarparkRepository(galleryRemoteRepository, galleryLocalRepository)
}