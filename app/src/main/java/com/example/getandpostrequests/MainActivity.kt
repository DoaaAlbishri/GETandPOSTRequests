package com.example.getandpostrequests

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val name = findViewById<View>(R.id.editText) as EditText
        val savebtn = findViewById<View>(R.id.btsave) as Button
        val showbtn = findViewById<View>(R.id.btshow) as Button


        savebtn.setOnClickListener {

            var user = PeopleDetails.Datum(Random.nextInt(0,100), name.text.toString())

            addUserdata(user, onResult = { name.setText("") })
        }
        showbtn.setOnClickListener {
            startActivity(Intent(this,MainActivity2::class.java))
        }

    }

    private fun addUserdata(user: PeopleDetails.Datum, onResult: () -> Unit) {

        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Please wait")
        progressDialog.show()

        if (apiInterface != null) {
            apiInterface.addUser(user).enqueue(object : Callback<PeopleDetails.Datum> {
                override fun onResponse(
                        call: Call<PeopleDetails.Datum>,
                        response: Response<PeopleDetails.Datum>
                ) {
                    onResult()
                    Toast.makeText(applicationContext, "Save Success!", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss()
                }

                override fun onFailure(call: Call<PeopleDetails.Datum>, t: Throwable) {
                    onResult()
                    Toast.makeText(applicationContext, "Error!", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss()

                }
            })
        }
    }
}