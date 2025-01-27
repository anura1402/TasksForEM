package ru.anura.tasks.rxJava.network

import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import ru.anura.tasks.rxJava.network.entity.Product
import ru.anura.tasks.rxJava.network.entity.Products

interface Api {
    @GET("products")
     fun getProducts(): Observable<Products>
    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: Int): Observable<Product>


}