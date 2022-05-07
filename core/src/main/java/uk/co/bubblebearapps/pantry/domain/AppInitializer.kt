package uk.co.bubblebearapps.pantry.domain

import android.app.Application

interface AppInitializer {

    fun init(app: Application)
}
