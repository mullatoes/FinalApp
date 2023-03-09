package com.android.finalapp.data

import com.android.finalapp.model.Item

object SampleData {
    //create a list of items
    var items = mutableListOf(
        Item("Iphone 14 Pro", 1299.0, "Iphone 14 Pro with multiple cameras"),
        Item("MacBook Air M1", 1199.0, "It is powered by Apple M1 chip"),
        Item("Airpods Pro", 299.0, "Noise cancellation earphones for you"),
    )
}