package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.myapplication.Utility.App
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()
{
    companion object
    {
        const val EXTRA_PLACE = "EXTRA_PLACE"
        const val REQUEST_CODE = 1000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //リストに表示するためのAdapterを生成
        val adapter: PlaceAdapter = PlaceAdapter(this, App.places, object :PlaceAdapter.Listener{
                override fun onItemClicked(position: Int) {
                    super.onItemClicked(position)
//                Log.d(packageName,"_____${position}_____")

                    val intent = Intent(this@MainActivity, DetailActivity::class.java).apply {
                        putExtra(EXTRA_PLACE,App.places[position])
                    }
                    //画面遷移
//                startActivity(intent)
                    //画面遷移(戻りあり)
                    startActivityForResult(intent, REQUEST_CODE)
                }
            })

        //import kotlinx.android.synthetic.main.activity_main.* で、View.findByIdをしなくてもすむ
        placeRecyclerView
            //run: 前半が省略可能
            .apply {
                //リストのコンテンツの大きさがデータによって変わらないならtrueを渡す(パフォーマンス向上のため)
                setHasFixedSize(true)
                //RecyclerViewのレイアウトをカスタムできる(今回はList表示、Grid表示ならGridLayoutManager)
                layoutManager = LinearLayoutManager(this@MainActivity)
                this.adapter = adapter
            }
    }


}
