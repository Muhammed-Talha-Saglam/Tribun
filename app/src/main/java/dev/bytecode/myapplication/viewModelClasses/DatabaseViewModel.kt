package dev.bytecode.myapplication.viewModelClasses

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import dev.bytecode.myapplication.Modals.Author
import dev.bytecode.myapplication.Modals.Team
import dev.bytecode.myapplication.utils.newUserAuthors
import dev.bytecode.myapplication.utils.newUserName
import dev.bytecode.myapplication.utils.newUserTeam

class DatabaseViewModel : ViewModel() {


    private var _teams = MutableLiveData<List<Team>>()
    private var _authors = MutableLiveData<List<Author>>()
    private var _nameSurname = MutableLiveData<String>()
    private var _supportingTeam = MutableLiveData<Team>()
    private var _followingAuthors = MutableLiveData<MutableList<Author>>()

    val teams: LiveData<List<Team>> = _teams
    val authors: LiveData<List<Author>> = _authors
    val nameSurname: LiveData<String> = _nameSurname
    val supportingTeam: LiveData<Team> = _supportingTeam
    val followingAuthor: LiveData<MutableList<Author>> = _followingAuthors


    private lateinit var firestore: FirebaseFirestore
    var auth: FirebaseAuth = FirebaseAuth.getInstance()
    private var currentUser: FirebaseUser? = auth.currentUser


    init {
        firestore = FirebaseFirestore.getInstance()
        getTeams()
        getAuthors()
    }

    fun getTeams() {

        firestore.collection("teams").addSnapshotListener { snapshot, e ->

            if (e != null) {
                return@addSnapshotListener
            }

            val list = mutableListOf<Team>()

            snapshot?.documents?.forEach {

                val team = Team(
                    id = it["id"] as String?,
                    name = it["name"] as String?,
                    imgUrl = it["imgUrl"] as String?,
                    infoUrl = it["infoUrl"] as String?
                )

                list.add(team)
            }
            _teams.value = list

        }

    }

    fun getAuthors() {

        firestore.collection("suggestedAuthors").addSnapshotListener { snapshot, e ->

            if (e != null) {
                return@addSnapshotListener
            }

            val list = mutableListOf<Author>()

            snapshot?.documents?.forEach {


                val author = Author(
                    id = it["id"] as String?,
                    name = it["name"] as String?,
                    twitterUserName = it["twitterUserName"] as String?,
                    imageUrl = it["imageUrl"] as String?,
                    following = it["following"] as Boolean?
                )

                list.add(author)
            }
            _authors.value = list

        }

    }
    fun getCurrentUser() {
        firestore.collection("users").document(currentUser!!.uid)
            .addSnapshotListener { snapshot, e ->

                if (e != null) {
                    return@addSnapshotListener
                }

                _nameSurname.value = snapshot?.get("nameSurname") as String?

                val teamMap = snapshot?.get("supportingTeam") as Map<*, *>?
                val team = Team(
                    id = teamMap?.get("id") as String?,
                    name = teamMap?.get("name") as String?,
                    infoUrl = teamMap?.get("infoUrl") as String?,
                    imgUrl = teamMap?.get("imgUrl") as String?
                )
                _supportingTeam.value = team

                val authorMap = snapshot?.get("followingAuthors") as List<Map<*, *>>?
                var authorList = mutableListOf<Author>()

                authorMap?.forEach {
                    var author = Author(
                        id = it?.get("id") as String?,
                        name = it?.get("name") as String?,
                        twitterUserName = it?.get("twitterUserName") as String?,
                        imageUrl = it?.get("imgUrl") as String?,
                        following = it?.get("following") as Boolean?,
                        )
                    authorList.add(author)
                }
                _followingAuthors.value = authorList



            }
    }

    fun addNewUserToDatabase() {
        firestore.collection("users").document(currentUser!!.uid).set(
            hashMapOf(
                "id" to currentUser!!.uid,
                "email" to currentUser!!.email,
                "nameSurname" to newUserName,
                "imageUrl" to "https://firebasestorage.googleapis.com/v0/b/tribun---deniseict.appspot.com/o/ic_avatar.jpg?alt=media&token=e7ddfc4c-3f83-4bbf-a8ed-008badea8844",
                "supportingTeam" to newUserTeam,
                "followingAuthors" to newUserAuthors

            )
        ).addOnCompleteListener {
            getCurrentUser()
        }

    }

    fun updateSupportingTeam(team: Team) {
        firestore.collection("users").document(currentUser!!.uid).update("supportingTeam", team)

    }

    fun updateFollowingAuthors(authors: List<Author>) {
        firestore.collection("users").document(currentUser!!.uid).update("followingAuthors", authors)

    }



    fun signOut() {
        auth.signOut()
    }


}