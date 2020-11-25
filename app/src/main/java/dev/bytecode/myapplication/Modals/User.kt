package dev.bytecode.myapplication.Modals

data class User(
    val uid: String,
    val nameSurname: String,
    val email: String,
    val supportingTeam: HashMap<String, Any?>,
    val followingAuthors: List<HashMap<String, Any?>>,
    val imageUrl: String = "https://firebasestorage.googleapis.com/v0/b/tribun---deniseict.appspot.com/o/ic_avatar.jpg?alt=media&token=e7ddfc4c-3f83-4bbf-a8ed-008badea8844"
)