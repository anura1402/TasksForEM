package ru.anura.tasks.kotlin

/**
 * Написать extention-функцию для List,
 * которая в рантайме будет искать Int в списке типа Any и возвращать его.
 * Заранее подготовить список, наполненный разными классами(5-10 шт будет достаточно).
 * По нажатию на кнопку выводить результат в логи (не использовать рефлексию).
 */
fun <E> List<E>.findInt(action: (Int) -> Unit): Boolean {
    var result = false
    this.forEach {
        if (it is Int) {
            action(it)
            result = true
        }
    }
    return result
}