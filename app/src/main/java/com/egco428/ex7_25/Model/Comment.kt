package com.egco428.ex7_25.Model

class Comment {
    var id:Long = 0
    var commentData: String? = null

    override fun toString(): String {
        return commentData.toString() //if dont make this function ,it will return address ,not String
    }
}