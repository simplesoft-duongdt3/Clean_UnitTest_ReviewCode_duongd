package duongd.example

import android.app.Application
import duongd.example.di.KoinModules
import org.koin.standalone.StandAloneContext

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        StandAloneContext.startKoin(KoinModules.modules())
    }
}