package prism6.com.easypark

import android.app.Application
import com.facebook.stetho.Stetho
import com.github.piasy.biv.BigImageViewer
import com.github.piasy.biv.loader.glide.GlideImageLoader

class mApplication: Application() {
    companion object {
        lateinit var instance : mApplication
    }

    override fun onCreate() {
        super.onCreate()

        instance = this

        Stetho.initializeWithDefaults(this);

        BigImageViewer.initialize(GlideImageLoader.with(this));
    }
}