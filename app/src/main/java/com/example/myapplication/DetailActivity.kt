package com.example.myapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import android.view.View
import com.example.myapplication.Model.Place
import kotlinx.android.synthetic.main.activity_detail.*



class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val place: Place? = intent.getSerializableExtra(MainActivity.EXTRA_PLACE) as? Place
        Log.d(packageName,"_____${place?.name}_____")

        //オプショナルパインディング
        place?.let {
            initUI(place)
        } ?: run { // ?: ← ネルビス演算子
            Log.e(packageName,"place not found!")
        }
    }

    private fun initUI(place: Place)
    {
        placeNameTextView.text = place.name
        cityNameTextView.text = place.city
        detailTextView.text = place.description ?: resources.getString(R.string.description)

        backButton.visibility = if (place.latitude != null && place.longitude != null ) View.VISIBLE else View.GONE
        backButton.setOnClickListener {
            //前の画面に値を渡すための設定
            //            setResult(Activity.RESULT_OK, Intent().apply {
            //                putExtra("string",place.name)
            //            })
            //            finish()

            //Uriを生成してIntentでマップ表示
            val uri = Uri.parse("geo:${place.latitude},${place.longitude}?z=16")
            startActivity(Intent(Intent.ACTION_VIEW, uri))
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean
    {
        when (item?.itemId) {
            android.R.id.home -> finish()
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }
}
