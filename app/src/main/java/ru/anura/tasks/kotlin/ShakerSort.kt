package ru.anura.tasks.kotlin


/**
 * Написать шейкерную сортировку для List<Int?>. Учесть кейсы, когда переданный массив = null,
 * некоторые эл-ты массива = null - пушить такие эл-ты в конец сортированного списка.
 */
fun shakerSort(list: List<Int?>?): List<Int?>? {
    list?.let {
        val mutableList = list.toMutableList()
        fun swap(i: Int, j: Int) {
            val temp = mutableList[i]
            mutableList[i] = mutableList[j]
            mutableList[j] = temp
        }
        do {
            var swapped = false
            for (i in 0 until mutableList.size - 1) {
                if (mutableList[i] == null && mutableList[i + 1] != null) {
                    swap(i, i + 1)
                    swapped = true
                } else if (mutableList[i] != null && mutableList[i + 1] != null) {
                    if (mutableList[i]!! > mutableList[i + 1]!!) {
                        swap(i, i + 1)
                        swapped = true
                    }
                }
            }
            if (!swapped) break
            swapped = false
            for (i in mutableList.size - 2 downTo 0) {
                if (mutableList[i] == null && mutableList[i + 1] != null) {
                    swap(i, i + 1)
                    swapped = true
                } else if (mutableList[i] != null && mutableList[i + 1] != null) {
                    if (mutableList[i]!! > mutableList[i + 1]!!) {
                        swap(i, i + 1)
                        swapped = true
                    }
                }
            }
        } while (swapped)
        val sortedList: List<Int?> = mutableList
        return sortedList
    } ?: return null
}