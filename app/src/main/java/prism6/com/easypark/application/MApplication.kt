package prism6.com.easypark.application

import android.app.Application
import com.facebook.stetho.Stetho

class mApplication: Application() {
    companion object {
        lateinit var instance : mApplication
    }

    override fun onCreate() {
        super.onCreate()

        instance = this

        Stetho.initializeWithDefaults(this);
    }
}