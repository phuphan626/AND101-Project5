package com.example.project5

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers

class MainActivity : AppCompatActivity() {

    private lateinit var planetList : MutableList<String>
    private lateinit var rvPlanets : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvPlanets = findViewById(R.id.planet_list)
        planetList = mutableListOf()


    }

//    private fun getPhotoURl(button: Button, imageView: ImageView, vararg textView: TextView ){
//        button.setOnClickListener{
//            getPhotoAPI()
//            titleTextView.text = title
//            explanationTextView.text = explanation
//            Glide.with(this).load(photoURL).
//                    fitCenter().into(imageView)
//        }
//    }
    private fun getPhotoAPI(){
        val client = AsyncHttpClient()
        val api = "Please use the API key"
        val nasaUrl = "https://api.nasa.gov/planetary/apod?api_key=$api&count=5"

        client[nasaUrl, object :
            JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON) {
                val planetImageArray = json.jsonObject.getJSONArray("message")
                for (i in 0 until planetImageArray.length()){
                    planetList.add(planetImageArray.getString(i))
                }

                val adapter = PlanetAdapter(planetList)

                rvPlanets.adapter = adapter

                rvPlanets.layoutManager = LinearLayoutManager(this@MainActivity)

                rvPlanets.addItemDecoration(DividerItemDecoration(this@MainActivity, LinearLayoutManager.VERTICAL))



            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                throwable: Throwable?
            ) {
                Log.d("Error Loading Images", errorResponse)
            }
        }]

    }


}

