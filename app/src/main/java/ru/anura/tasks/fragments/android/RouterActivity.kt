package ru.anura.tasks.fragments.android

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ru.anura.tasks.R

class RouterActivity: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_router)
        Log.d("AndTest","onCreate Router")
    }
    fun navigateTo(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.router_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}