package com.example.getandpostrequests

import com.google.gson.annotations.SerializedName

class PeopleDetails {

    var data: List<Datum>? = null

    class Datum {
        @SerializedName("pk")
        var pk: Int? = null

        @SerializedName("name")
        var name: String? = null

        constructor(pk:Int? , name: String?) {
            this.name = name
            this.pk = pk
        }
    }
}