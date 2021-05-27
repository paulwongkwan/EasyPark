package prism6.com.easypark.component

import dagger.Component
import prism6.com.easypark.module.NetworkModule
import prism6.com.easypark.module.RepositoryModule
import prism6.com.easypark.repository.RemoteRepository
import prism6.com.easypark.ui.home.HomeViewModel
import javax.inject.Singleton

@Component(modules = [NetworkModule::class, RepositoryModule::class])
@Singleton
interface RepositotyComponent {
    fun inject(remoteRepository: RemoteRepository)
    fun inject(homeViewModel: HomeViewModel)
}