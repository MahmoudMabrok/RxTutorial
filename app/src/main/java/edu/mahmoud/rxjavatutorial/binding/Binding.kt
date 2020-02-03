package edu.mahmoud.rxjavatutorial.binding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxbinding2.widget.RxTextView
import edu.mahmoud.rxjavatutorial.R
import edu.mahmoud.rxjavatutorial.utils.log
import edu.mahmoud.rxjavatutorial.utils.show
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_binding.*

class Binding : AppCompatActivity() {

    private lateinit var viewAdapter: MyAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    val bg = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_binding)

        viewManager = LinearLayoutManager(this)

        val ds = arrayListOf<String>(
            "AA",
            "B",
            "C",
            "AAQ",
            "BQE",
            "CDE",
            "BA",
            "CW",
            "AAQE",
            "BQEQ",
            "CADE"
        )

        // as it was send reference so when clear is called, this one is cleared also
        viewAdapter = MyAdapter(ds.clone() as ArrayList<String>)

        rvBinding.apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter

        }

        bg.add(viewAdapter.getViewObs()
            .subscribe {
                this.show(it)
            })

        bg.add(
            RxTextView.textChanges(edText)
                .subscribe { text ->
                    "text changed ,$text,".log()
                    viewAdapter.setData(ds.filter { item -> item.contains(text) })
                }
        )
    }

    override fun onStop() {
        super.onStop()
        bg.clear()
    }
}
