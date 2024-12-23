package ru.anura.tasks

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * Написать свой делегат, который будет кешировать время запуска приложения.
 * Раз в 3 секунды выводить закешированное значение в логи Не в UI потоке.
 */
class TimeCacher<T>(private val valueProvider:()-> T) : ReadOnlyProperty<Any?, T> {
    private var cachedValue: T? = null
    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        if (cachedValue == null) {
            cachedValue = valueProvider()
            logging()
        }
        return cachedValue!!
    }

    private fun logging() {
        CoroutineScope(Dispatchers.Default).launch {
            while (isActive) {
                Log.d("TimeCacher", cachedValue.toString())
                delay(3000)
            }
        }
    }
}