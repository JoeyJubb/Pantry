package uk.co.bubblebearapps.pantry.app

import android.app.Application
import timber.log.Timber
import uk.co.bubblebearapps.pantry.BuildConfig
import uk.co.bubblebearapps.pantry.domain.AppInitializer
import javax.inject.Inject

internal class TimberInitializer @Inject constructor(

) : AppInitializer {

    override fun init(app: Application) {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
