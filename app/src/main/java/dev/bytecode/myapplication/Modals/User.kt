package dev.bytecode.myapplication.Modals

data class User(
    val uid: String,
    val nameSurname: String,
    val email: String,
    val supportingTeam: HashMap<String, Any?>,
    val followingAuthors: List<HashMap<String, Any?>>,
    val imageUrl: String = "https://firebasestorage.googleapis.com/v0/b/tribun-app.appspot.com/o/ic_avatar.jpg?alt=media&token=6d65002d-be50-4198-bee8-3f74eca1802c"
)