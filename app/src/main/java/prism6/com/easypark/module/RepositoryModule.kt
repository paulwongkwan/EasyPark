package prism6.com.easypark.module

import dagger.Module
import dagger.Provides
import prism6.com.infiniteimgur.repository.GalleryLocalRepository
import prism6.com.infiniteimgur.repository.GalleryRemoteRepository
import prism6.com.infiniteimgur.repository.GalleryRepository
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Singleton
    @Provides
    fun provideRemoteRepository() = GalleryRemoteRepository()

    @Singleton
    @Provides
    fun provideLocalepository() = GalleryLocalRepository()

    @Singleton
    @Provides
    fun provideRepository(galleryRemoteRepository: GalleryRemoteRepository,
                          galleryLocalRepository: GalleryLocalRepository
    ) =
        GalleryRepository(galleryRemoteRepository, galleryLocalRepository)
}