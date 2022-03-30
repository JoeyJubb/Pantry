package uk.co.bubblebearapps.pantry.ext

import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> Fragment.observe(liveData: LiveData<T>, observer: Observer<T>) = viewLifecycleOwner.observe(liveData, observer)

fun <T> LifecycleOwner.observe(liveData: LiveData<T>, observer: Observer<T>) = liveData.observe(this, observer)