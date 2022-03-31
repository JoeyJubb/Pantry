package uk.co.bubblebearapps.pantry.app

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import uk.co.bubblebearapps.pantry.domain.model.AppInitializer
import javax.inject.Inject

internal class ThreeTenInitializer @Inject constructor(

): AppInitializer {

    override fun init(app: Application) {
        AndroidThreeTen.init(app)
    }
}
