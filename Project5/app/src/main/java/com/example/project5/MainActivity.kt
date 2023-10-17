package com.example.project5

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers

class MainActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var titleTextView: TextView
    private lateinit var explanationTextView: TextView
    private lateinit var fetchDataButton: Button
    private lateinit var dateUser:EditText
    var photoURL = ""
    var title = ""
    var explanation = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView = findViewById(R.id.image_view)
        titleTextView = findViewById(R.id.title_text_view)
        explanationTextView = findViewById(R.id.explanation_text_view)
        fetchDataButton = findViewById(R.id.fetch_data_button)
        dateUser = findViewById(R.id.search_date)


        fetchDataButton.setOnClickListener {
            getPhotoAPI()
        }
        getPhotoURl(fetchDataButton,imageView)

    }

    private fun getPhotoURl(button: Button, imageView: ImageView, vararg textView: TextView ){
        button.setOnClickListener{
            getPhotoAPI()
            titleTextView.text = title
            explanationTextView.text = explanation
            Glide.with(this).load(photoURL).
                    fitCenter().into(imageView)
        }
    }
    private fun getPhotoAPI(){
        val client = AsyncHttpClient()
        val api = "Please use the API key"
        var userDate = dateUser.text
        val nasaUrl = "https://api.nasa.gov/planetary/apod?api_key=$api&date=$userDate"
        client[nasaUrl, object :
            JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON) {
                Log.d("Planetary", json.jsonObject.toString())

                photoURL = json.jsonObject.getString("url")
                title = json.jsonObject.getString("copyright")
                explanation = json.jsonObject.getString("explanation")
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

