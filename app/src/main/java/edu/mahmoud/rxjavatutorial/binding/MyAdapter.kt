package edu.mahmoud.rxjavatutorial.binding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxbinding2.view.RxView
import edu.mahmoud.rxjavatutorial.R
import edu.mahmoud.rxjavatutorial.utils.log
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.BehaviorSubject


class MyAdapter(private val myDataset: ArrayList<String>) :

    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    private val clickObservable = BehaviorSubject.create<String>()

    val bg = CompositeDisposable()

    fun getViewObs(): BehaviorSubject<String> {
        return clickObservable
    }

    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        var data: String? = null
            set(value) {
                field = value
                tvView.text = field
            }


        val tvView: TextView = view.findViewById(R.id.tvItem)

    }


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyAdapter.MyViewHolder {
        // create a new view
        val root = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_item, parent, false)

        val vh = MyViewHolder(root)

        RxView.clicks(vh.tvView)
            .takeUntil(RxView.detaches(parent))
            .map { vh.data }
            .subscribe(clickObservable)

        return vh
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.data = myDataset[position]


    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = myDataset.size

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        bg.clear()
    }

    fun setData(data: List<String>) {
        "mydataset before $myDataset".log()
        myDataset.clear()
        myDataset.addAll(data)
        "mydataset after $myDataset".log()
        notifyDataSetChanged()

    }
}