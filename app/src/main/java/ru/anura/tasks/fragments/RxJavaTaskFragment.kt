package ru.anura.tasks.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import ru.anura.tasks.databinding.FragmentRxJavaTaskBinding
import ru.anura.tasks.rxJava.adapter.ProductAdapter
import ru.anura.tasks.rxJava.network.ApiClient
import ru.anura.tasks.rxJava.network.DiscountCardApi
import java.util.concurrent.TimeUnit
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.zip

class RxJavaTaskFragment : Fragment() {
    private var _binding: FragmentRxJavaTaskBinding? = null
    private val binding: FragmentRxJavaTaskBinding
        get() = _binding ?: throw RuntimeException("FragmentRxJavaTaskBinding == null")

    private lateinit var adapter: ProductAdapter
    private val api = ApiClient.apiClient
    private val discountCardApi = DiscountCardApi()
    private lateinit var disposable: Disposable

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRxJavaTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun initRcView() = with(binding) {
        adapter = ProductAdapter()
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        initRcView()
//        firstTask()
//        secondTask()
//        thirdTask()
//        fourthTask()
//        fifthTaskA()
//        fifthTaskB()
//        val t = Observable.create{
//            for (i in 1..10) {
//                it.onNext(i)
//                Thread.sleep(1000)
//            }
//        }
//            .throttleLatest(5, TimeUnit.SECONDS)
//            .subscribe{
//                Log.d("Coroutine", "Observable: $it")
//            }
        viewLifecycleOwner.lifecycleScope.launch {
//            val flow1 = testCoroutineTasks().throttleFirst(5000).collect{
//                Log.d("Coroutine", "Flow: $it")
//            }
            val flow2 = testCoroutineTasks().throttleLatest(5000)
                .collect {
                Log.d("Coroutine", "Flow2: $it")
            }
        }

    }

    private fun <T> Flow<T>.throttleFirst(duration: Long): Flow<T> = flow {
        var time = 0L
        Log.d("Coroutine", "FlowTempTime: $time")
        collect {
            Log.d("Coroutine", "FlowTempTime: $time")
            Log.d("Coroutine", "FlowTempSystem: ${System.currentTimeMillis()}")
            Log.d("Coroutine", "FlowTemp: ${System.currentTimeMillis() - time}, $duration")
            if (System.currentTimeMillis() - time >= duration) {
                emit(it)
                time = System.currentTimeMillis()

            }
        }

    }

    private fun <T> Flow<T>.throttleLatest(duration: Long): Flow<T> = flow {
//        var time = 0L
//        var temp: T? = null
//        var duration2 = 0L

        var currTime = System.currentTimeMillis() - duration
        var l: T? = null;
        var i = 0
        collect {
            var currTime2 = System.currentTimeMillis()
            if (currTime2 - currTime >= duration) {
                if (i == 0) {
                    emit(it)
                } else{
                    emit(l!!)
                }
                currTime = currTime2
            }
            i++
            l = it

//            if (System.currentTimeMillis() - time < duration && duration2 == 0L) {
//                //emit(it)
//                duration2 = System.currentTimeMillis() - time
//            }
//            if (System.currentTimeMillis() - time + duration2 >= duration ) {
//                emit(it)
//                time = System.currentTimeMillis()+duration2
//            }

        }

    }

    private suspend fun testCoroutineTasks(): Flow<Int> {
        return flow {
            for (i in 1..10) {
                emit(i)
                delay(1000)
            }
        }

    }

    private suspend fun testCoroutineTasks2(): Flow<Char> {
        return flow {
            for (i in 'A'..'E') {
                emit(i)
                delay(300)
            }
        }

    }

    private suspend fun testCoroutineTasksAsync() {
        coroutineScope {
            launch {
                try {
                    delay(500)
                    throw IllegalStateException()
                } catch (e: Exception) {
                    Log.d("Coroutine", "Exception: $e")
                }

            }
        }
    }

    private fun testTasks() {
        val test = Observable.fromIterable(listOf(1, 2, 3))
            .subscribeOn(Schedulers.io()) // Источник работает на io
            .observeOn(Schedulers.computation()) // После него всё на computation
            .flatMap {
                Log.d("RX", "flatMapThread = ${Thread.currentThread().name}")
                Observable.just(it * 10)
                    .subscribeOn(Schedulers.newThread()) // Здесь каждый `just` на `newThread`
            }
            .observeOn(Schedulers.single()) // Меняет поток перед подпиской
            .subscribe {
                Log.d("RX", "subscribeThread = ${Thread.currentThread().name}")
            }
    }

    private fun fifthTaskA() {
        val obs1 = discountCardApi.getDiscountCardsFromServer1()
            .onErrorReturn { emptyList() }
        val obs2 = discountCardApi.getDiscountCardsFromServer2()
            .onErrorReturn { emptyList() }
        val common = Observable.zip(obs1, obs2) { list1, list2 ->
            list1 + list2
        }
            .subscribe({
                Log.d("HAHAHA", "Fifth Task A: count = ${it.size}, DiscountCard = $it")
            })
    }

    private fun fifthTaskB() {
        val obs1 = discountCardApi.getDiscountCardsFromServer1()
        val obs2 = discountCardApi.getDiscountCardsFromServer2()
        val common = Observable.zip(obs1, obs2) { list1, list2 ->
            list1 + list2
        }
            .onErrorReturn { emptyList() }
            .subscribe({
                Log.d("HAHAHA", "Fifth Task B: count = ${it.size}, DiscountCard = $it")
            })
    }

    private fun fourthTask() {
        val editing = Observable.create {
            binding.edtText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    it.onNext(s.toString())
                }

                override fun afterTextChanged(s: Editable?) {
                }

            })
        }.debounce(3, TimeUnit.SECONDS)
            .subscribe({
                Log.d("HAHAHA", "FourthTask: EditText = $it")
            }, {
                Log.d("HAHAHA", "FourthTask: Error ${it.message}")
            })
    }

    private fun thirdTask() {
        val position = BehaviorSubject.create { it ->
            adapter.onProductItemClickListener = { product ->
                it.onNext(product.id)
            }
        }.observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                binding.tvPosition.text =
                    String.format("Выбранная позиция в RecyclerView: %s", it.toString())
            })
    }

    private fun secondTask() {
        val timer = Observable.interval(1, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                binding.tvTime.text = it.toString()
            },
                {
                    Log.d("HAHAHA", "SecondTask: Error ${it.message}")
                    Toast.makeText(context, "Error ${it.message}", Toast.LENGTH_SHORT).show()
                }, {

                })
    }

    private fun firstTask() {
        disposable = api.getProducts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                adapter.submitList(it.products)
                Log.d("HAHAHA", "First Task: ${it.products}")
                Toast.makeText(context, "Успешно", Toast.LENGTH_SHORT).show()
            }, {
                Log.d("HAHAHA", "First Task: Error ${it.message}")
                Toast.makeText(context, "Проверьте подключение к интернету", Toast.LENGTH_SHORT)
                    .show()
            }, {
            })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        disposable.dispose()
    }

    companion object {
        const val NAME = "RxJavaTaskFragment"
        fun newInstance(): RxJavaTaskFragment {
            return RxJavaTaskFragment()
        }
    }
}