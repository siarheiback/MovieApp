package happigin.inc.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import happigin.inc.di.retrofit.RetrofitModule
import happigin.inc.presentation.screens.main.MainActivity
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class, RetrofitModule::class])
interface AppComponent {

    fun inject(homeActivity: MainActivity)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun create(): AppComponent
    }
}
