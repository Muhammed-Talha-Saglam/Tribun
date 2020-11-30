package dev.bytecode.myapplication.Modals

data class Author(
    var id: String?,
    val name: String?,
    val twitterUserName: String?,
    val imageUrl: String?,
    var following: Boolean?

) {

    override fun equals(other: Any?): Boolean {
        val another = other as Author
        return this.twitterUserName == another.twitterUserName
    }

    override fun hashCode(): Int {
        return twitterUserName.hashCode()
    }
}