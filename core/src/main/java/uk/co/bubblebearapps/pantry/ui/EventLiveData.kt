package uk.co.bubblebearapps.pantry.ui

import androidx.annotation.MainThread
import androidx.collection.ArraySet
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer

/**
 * MutableLiveData that only notifies observers of new values. Returning Observers
 * will not have their .onChanged() triggered unless new data has been set.
 *
 * Set shouldKeepOrphanedValues to true when you want to emit the event in the
 * init of ViewModel, and expect the first observer receive it after onStart.
 *
 * @param shouldKeepOrphanedValues true: When an event is emitted with no observers,
 * the first returning observer will receive the last event.
 */
class EventLiveData<T>(
    private val shouldKeepOrphanedValues: Boolean = true
) : MediatorLiveData<T>() {

    private val observers = ArraySet<ObserverWrapper<in T>>()

    private var orphanedValue: Boolean = false

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        observers.find { it.observer === observer }?.let { _ -> // existing
            return
        }

        val wrapper = wrap(observer)
        observers.add(wrapper)
        super.observe(owner, wrapper)
    }

    @MainThread
    override fun observeForever(observer: Observer<in T>) {
        observers.find { it.observer === observer }?.let { _ -> // existing
            return
        }
        val wrapper = wrap(observer)
        super.observeForever(wrapper)
    }

    @MainThread
    override fun removeObserver(observer: Observer<in T>) {
        if (observer is ObserverWrapper && observers.remove(observer)) {
            super.removeObserver(observer)
            return
        }
        val iterator = observers.iterator()
        while (iterator.hasNext()) {
            val wrapper = iterator.next()
            if (wrapper.observer == observer) {
                iterator.remove()
                super.removeObserver(wrapper)
                break
            }
        }
    }

    @MainThread
    override fun setValue(t: T?) {
        if (observers.isEmpty() && shouldKeepOrphanedValues) {
            orphanedValue = true
        }
        observers.forEach { it.newValue() }
        super.setValue(t)
    }

    private fun wrap(observer: Observer<in T>): ObserverWrapper<in T> {
        val wrapper = ObserverWrapper(observer)
        if (orphanedValue) {
            orphanedValue = false
            wrapper.newValue()
        }
        observers.add(wrapper)
        return wrapper
    }

    private class ObserverWrapper<T>(val observer: Observer<T>) : Observer<T> {

        private var pending = false

        override fun onChanged(t: T?) {
            if (pending) {
                pending = false
                observer.onChanged(t)
            }
        }

        fun newValue() {
            pending = true
        }
    }
}
