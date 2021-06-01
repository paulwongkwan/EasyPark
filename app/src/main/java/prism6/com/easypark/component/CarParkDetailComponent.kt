package prism6.com.easypark.component

import dagger.Component
import prism6.com.easypark.module.RepositoryModule
import prism6.com.easypark.ui.activity.CarparkDetailViewModel
import javax.inject.Singleton

@Component(modules = [RepositoryModule::class])
@Singleton
interface CarParkDetailComponent {
    fun inject(vm : CarparkDetailViewModel)
}