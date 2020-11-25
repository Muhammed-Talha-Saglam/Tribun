package dev.bytecode.myapplication.Modals

data class Author(
    val id: String?,
    val name: String?,
    val twitterUserName: String?,
    val imageUrl: String?,
    var following: Boolean?
)