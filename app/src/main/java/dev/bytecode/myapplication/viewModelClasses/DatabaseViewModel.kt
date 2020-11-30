package dev.bytecode.myapplication.viewModelClasses

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import dev.bytecode.myapplication.Modals.Author
import dev.bytecode.myapplication.Modals.Team
import dev.bytecode.myapplication.utils.newUserName

class DatabaseViewModel : ViewModel() {


    private var _teams = MutableLiveData<List<Team>>()
    private var _authors = MutableLiveData<MutableList<Author>>()
    private var _nameSurname = MutableLiveData<String>()
    private var _userImg = MutableLiveData<String>()
    private var _supportingTeam = MutableLiveData<Team>()
    private var _followingAuthors = MutableLiveData<MutableList<Author>>()
    private var _twitterUserName = MutableLiveData<String>()


    val teams: LiveData<List<Team>> = _teams
    val authors: LiveData<MutableList<Author>> = _authors
    val nameSurname: LiveData<String> = _nameSurname
    val userImg: LiveData<String> = _userImg
    val supportingTeam: LiveData<Team> = _supportingTeam
    val followingAuthors: LiveData<MutableList<Author>> = _followingAuthors
    val twitterUserName: LiveData<String> = _twitterUserName

    fun setTwitterUserName(name: String) {
        _twitterUserName.value = name
    }


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
                    imageUrl = it["imageUrl"] as String?,
                    infoUrl = it["infoUrl"] as String?
                )


                list.add(team)
            }
            _teams.value = list

        }

    }

    fun getAuthors() {

        firestore.collection("suggestedAuthors").get().addOnCompleteListener {


            var suggestedAuthors = mutableListOf<Author>()

            it.result?.documents?.forEach {


                var suggestedAuthor = Author(
                    id = it["id"] as String?,
                    name = it["name"] as String?,
                    twitterUserName = it["twitterUserName"] as String?,
                    imageUrl = it["imageUrl"] as String?,
                    following = it["following"] as Boolean?
                )

                suggestedAuthors.add(suggestedAuthor)

            }

            var followingAuthors = mutableListOf<Author>()

            firestore.collection("users").document(currentUser!!.uid).get()
                .addOnCompleteListener { f ->

                    val snapShot = f.result

                    (snapShot?.get("followingAuthors") as List<Map<*, *>>?)?.forEach { following ->

                        Log.d("**************", following.toString())

                        var followingAuthor = Author(
                            id = following["id"] as String?,
                            name = following["name"] as String?,
                            twitterUserName = following["twitterUserName"] as String?,
                            following = following["following"] as Boolean?,
                            imageUrl = following["imageUrl"] as String?
                        )
                        followingAuthors.add(followingAuthor)

                    }


                    // Merge following and suggested authors lists
                    val mergedList = followingAuthors.union(suggestedAuthors)



                    Log.d("********* Merged List ", mergedList.toString())

                    _authors.value = mergedList.toMutableList()

                }

        }

    }


    fun updateAuthors(author: Author) {
        _authors.value?.forEach {
            if (author.twitterUserName == it.twitterUserName) {
                it.following = author.following
            }
        }

    }

    fun addNewTwitterAuthor() {
        val author = Author(
            twitterUserName = twitterUserName.value,
            following = true,
            imageUrl = "https://twitter.com/${twitterUserName.value}/profile_image?size=original",
            name = null,
            id = null
        )
        _authors.value?.add(author)
        updateFollowingAuthors()

    }

    fun getCurrentUser() {
        firestore.collection("users").document(currentUser!!.uid)
            .addSnapshotListener { snapshot, e ->

                if (e != null) {
                    return@addSnapshotListener
                }

                _nameSurname.value = snapshot?.get("nameSurname") as String?
                _userImg.value = snapshot?.get("imageUrl") as String?

                val teamMap = snapshot?.get("supportingTeam") as Map<*, *>?
                val team = Team(
                    id = teamMap?.get("id") as String?,
                    name = teamMap?.get("name") as String?,
                    infoUrl = teamMap?.get("infoUrl") as String?,
                    imageUrl = teamMap?.get("imageUrl") as String?
                )
                _supportingTeam.value = team

                val authorMap = snapshot?.get("followingAuthors") as List<Map<*, *>>?
                var authorList = mutableListOf<Author>()

                authorMap?.forEach {
                    var author = Author(
                        id = it.get("id") as String?,
                        name = it.get("name") as String?,
                        twitterUserName = it.get("twitterUserName") as String?,
                        imageUrl = it.get("imgUrl") as String?,
                        following = it.get("following") as Boolean?,
                    )
                    authorList.add(author)
                }
                _followingAuthors.value = authorList


            }
    }

    fun addNewUserToDatabase(team: Team) {
        firestore.collection("users").document(currentUser!!.uid).set(
            hashMapOf(
                "uid" to currentUser!!.uid,
                "email" to currentUser!!.email,
                "nameSurname" to newUserName,
                "imageUrl" to "https://firebasestorage.googleapis.com/v0/b/tribun---deniseict.appspot.com/o/ic_avatar.jpg?alt=media&token=e7ddfc4c-3f83-4bbf-a8ed-008badea8844",
                "supportingTeam" to team,
                "followingAuthors" to null

            )
        )

    }

    fun updateSupportingTeam(team: Team) {
        firestore.collection("users").document(currentUser!!.uid).update("supportingTeam", team)

    }

    fun updateFollowingAuthors() {
        val followedAuthors = authors.value?.filter {
            it.following == true
        }

        firestore.collection("users").document(currentUser!!.uid)
            .update("followingAuthors", followedAuthors).addOnCompleteListener {
                getAuthors()
            }

    }


    fun signOut() {
        auth.signOut()
    }


}