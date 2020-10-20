package com.example.timer

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.timer.retrofit.ApiClient
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.error_page.*
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    private var flag: Int = 0
    lateinit var relativeLayout: RelativeLayout
    var dataList = ArrayList<MyItem>()

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val obj_adapter = CustomAdapter(dataList)
        recycler_view.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        recycler_view.adapter = obj_adapter
        var toolbar:androidx.appcompat.widget.Toolbar
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        getSupportActionBar()?.setDisplayShowTitleEnabled(false);

        getData()


        retryButton.setOnClickListener {
            SwipeToRefresh.visibility=View.VISIBLE
            shimmer_view_container.visibility=View.VISIBLE
            getData()
            error_page.visibility=View.GONE
        }

        SwipeToRefresh.setOnRefreshListener {
            shimmer_view_container.visibility=View.VISIBLE
            getData()
        }


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.item1 -> {
                Toast.makeText(applicationContext, "Sorted by stars", Toast.LENGTH_LONG).show();
                return true
//                class CustomComparator : Comparator<MyItem?> {
//                    override fun compare(o1: MyItem?, o2: MyItem?) {
//                        return o1.stars.compareTo(o2.stars)
//
//                    }
//            }
        }

            R.id.item2 -> {
                Toast.makeText(applicationContext, " Sorted by name", Toast.LENGTH_LONG).show();
                return true
//                sort1(dataList, Comparator<MyItem> { lhs, rhs ->
//                    if (lhs.name > rhs.name) {
//                        -1
//                    } else {
//                        1
//                    }
//                })

            }
            else -> super.onOptionsItemSelected(item)

        }
    }



    private fun getData(){
        SwipeToRefresh.isRefreshing=false
        dataList.clear()
        val call:retrofit2.Call<List<MyItem>> = ApiClient.getClient.getTrendingList()
        call.enqueue(object : Callback<List<MyItem>> {
            override fun onResponse(
                call: retrofit2.Call<List<MyItem>>?,
                response: Response<List<MyItem>>?
            ) {
                SwipeToRefresh.isRefreshing = false
                println("abcd")
                // shimmer_view_container.visibility=View.GONE
                dataList.addAll(response!!.body()!!)
                shimmer_view_container.visibility = View.VISIBLE
                SwipeToRefresh.visibility = View.VISIBLE
                val adapter = CustomAdapter(dataList)
                recycler_view.adapter = adapter
            }

            override fun onFailure(call: retrofit2.Call<List<MyItem>>?, t: Throwable?) {
                println("efgh")
                Toast.makeText(applicationContext, "error while fetching data", Toast.LENGTH_LONG)
                    .show()
                SwipeToRefresh.visibility = View.GONE
                shimmer_view_container.visibility = View.GONE
                error_page.visibility = View.VISIBLE
            }

        })
    }
}













