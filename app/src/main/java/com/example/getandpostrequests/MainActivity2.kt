package com.example.getandpostrequests

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val responseText = findViewById<TextView>(R.id.textView)

        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Please wait")
        progressDialog.show()
        val call: Call<List<PeopleDetails.Datum>> = apiInterface!!.doGetListResources()

        call?.enqueue(object : Callback<List<PeopleDetails.Datum>> {
            override fun onResponse(
                call: Call<List<PeopleDetails.Datum>>,
                response: Response<List<PeopleDetails.Datum>>
            ) {
                Log.d("TAG", response.code().toString() + "")
                var displayResponse = ""
                val resource: List<PeopleDetails.Datum>? = response.body()
                val datumList = resource
                progressDialog.dismiss()

                for (datum in datumList!!) {
                    displayResponse += """ ${datum.pk}  ${datum.name}
"""
                }
                responseText.text = displayResponse
            }

            override fun onFailure(call: Call<List<PeopleDetails.Datum>>, t: Throwable?) {
                call.cancel()
                progressDialog.dismiss()
            }
        })

    }
}