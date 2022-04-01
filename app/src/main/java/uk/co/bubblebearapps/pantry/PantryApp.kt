package uk.co.bubblebearapps.pantry

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import uk.co.bubblebearapps.pantry.domain.model.AppInitializer
import javax.inject.Inject

@HiltAndroidApp
class PantryApp : Application() {

    @Inject
    lateinit var appInitializers: Set<@JvmSuppressWildcards AppInitializer>

    override fun onCreate() {
        super.onCreate()

        appInitializers.forEach { initializer -> initializer.init(this) }
    }
}
