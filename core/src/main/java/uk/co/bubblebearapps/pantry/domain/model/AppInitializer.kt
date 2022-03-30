package uk.co.bubblebearapps.pantry.domain.model

import android.app.Application

interface AppInitializer {

    fun init(app: Application)
}
