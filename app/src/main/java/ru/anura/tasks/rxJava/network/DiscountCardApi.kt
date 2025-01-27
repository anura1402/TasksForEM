package ru.anura.tasks.rxJava.network

import io.reactivex.rxjava3.core.Observable
import ru.anura.tasks.rxJava.network.entity.DiscountCard
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class DiscountCardApi {


    private fun generateDiscountCards(count: Int, startId: Int): List<DiscountCard> {
        return List(count) { i ->
            val id = startId + i
            val discount = Random.nextInt(5, 50)
            DiscountCard(id, "Card $id", discount)
        }
    }

        fun getDiscountCardsFromServer1(): Observable<List<DiscountCard>> {
            val mockData = generateDiscountCards(10, 1)
            //return Observable.error<List<DiscountCard>>(RuntimeException("Ошибка в основном источнике"))
            return Observable.just(mockData).delay(1, TimeUnit.SECONDS)
        }

        fun getDiscountCardsFromServer2(): Observable<List<DiscountCard>> {
            val mockData = generateDiscountCards(10, 101)
            return Observable.error<List<DiscountCard>>(RuntimeException("Ошибка в основном источнике"))
            //return Observable.just(mockData).delay(2, TimeUnit.SECONDS)
        }
    }
