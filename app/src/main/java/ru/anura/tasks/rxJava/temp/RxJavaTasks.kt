package ru.anura.tasks.rxJava.temp

import android.content.Context
import android.util.Log
import io.reactivex.rxjava3.subjects.ReplaySubject

class RxJavaTasks {
    companion object{
        fun firstRxTask(context: Context) {
//            Observable.timer(10, TimeUnit.MILLISECONDS, Schedulers.newThread())
//                .subscribeOn(Schedulers.io()) // Устанавливаем поток для подписки
//                .map {
//                    Log.d("HAHAHA", "mapThread = ${Thread.currentThread().name}")
//                }
//                .doOnSubscribe {
//                    Log.d("HAHAHA", "onSubscribeThread = ${Thread.currentThread().name}")
//                }
//                .subscribeOn(Schedulers.computation()) // Этот вызов не имеет эффекта, так как subscribeOn действует только при первой установке
//                .observeOn(Schedulers.single()) // Переключаем поток для последующих операций
//                .flatMap {
//                    Log.d("HAHAHA", "flatMapThread = ${Thread.currentThread().name}")
//                    Observable.just(it)
//                        .subscribeOn(Schedulers.io()) // Подписка на вложенный Observable будет выполняться в этом потоке
//                }
//                .subscribe {
//                    Log.d("HAHAHA", "subscribeThread = ${Thread.currentThread().name}")
//                }
            val subject = ReplaySubject.create<String>()
            subject.onNext("1")
            subject.onNext("2")
            subject.onNext("3")
            subject.subscribe { Log.d("HAHAHA", it) }

        }
    }



}