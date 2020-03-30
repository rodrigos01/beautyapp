package org.rodrigo.beauty

import androidx.multidex.MultiDexApplication
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.rodrigo.beauty.servicelocator.appModule

class BeautyApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@BeautyApplication)
            modules(appModule)
        }
    }
}