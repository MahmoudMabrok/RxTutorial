package edu.mahmoud.rxjavatutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Observable.just("A", 1)
            .repeat(3)
            .subscribe {
                Log.d("Test", "$it")
            }

        /*  Observable.interval(1, TimeUnit.SECONDS)
              .subscribe{
                  Log.d("Test1" , "$it")
              }
          */

        Observable.range(1, 100)
            .filter { it % 2 == 0 }
            .skip(20)
            .subscribe {
                Log.d("Test3", "$it")
            }


    }
}
