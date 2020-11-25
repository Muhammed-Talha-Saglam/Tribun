package dev.bytecode.myapplication.viewModelClasses

import android.app.Activity
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dev.bytecode.myapplication.utils.newUserName


enum class UserType {
    NewUser,
    ExistingUser,
    ForgotPasswordUser
}

class AuthenticationViewModel : ViewModel() {


    // Hold the state of the text input fields.
    private var _userName = MutableLiveData<String>()
    private var _email = MutableLiveData<String>()
    private var _password = MutableLiveData<String>()

    // Text input fields listen to the corresponding LiveData
    // and changes their values as corresponding MutableLiveData changes.
    val userName: LiveData<String> = _userName
    val email: LiveData<String> = _email
    val password: LiveData<String> = _password



    // When the user inputs the text fields,
    // these functions change the corresponding MutableLiveData variables
    fun setUserName(name: String) {
        _userName.value = name
    }

    fun setEmail(name: String) {
        _email.value = name
    }

    fun setPassword(name: String) {
        _password.value = name
    }



    // The login screen shows different fields based on the user type.
    // I.e. if it is not a new user, don't show username field.
    private var _userType = MutableLiveData(UserType.ExistingUser)
    val userType: LiveData<UserType> = _userType
    fun changeUsertype(type: UserType) {
        _userType.value = type
    }



    // Make authentication through Firebase.
    private val auth =  FirebaseAuth.getInstance()
    // Hold the state of the current user.
    var currentUser: FirebaseUser? = auth.currentUser



    // This function signs the new users through Firebase
    fun signUpNewUser(
        activity: Activity,
        navigation: () -> Unit
    ) {

        // Username should not be empty
        if (userName.value == null) {
            makeToastMessage("Lütfen Gerekli Bilgileri Doldurun.", activity)

            return
        }

        // If the text fields are not valid, warn user.
        if (
            !validateUserName(userName.value!!) ||
            email.value.isNullOrEmpty() ||
            password.value.isNullOrEmpty()
        ) {
            makeToastMessage("Lütfen Gerekli Bilgileri Doldurun.", activity)
            return
        }


        // Add a new user to Firebase
        auth.createUserWithEmailAndPassword(email.value!!, password.value!!)
            .addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {

                    newUserName = userName.value!!

                    // Assign the current user
                    auth.currentUser?.let {
                        currentUser = it
                    }

                    // Navigate to team select page
                    navigation()
                    makeToastMessage("Kayıt Başarılı", activity)

                } else {

                    // If sign-in fails, display a message to the user.
                    var msg: String = "HATA"

                    task.exception?.let {
                        msg = it.localizedMessage
                    }
                    makeToastMessage(msg, activity)

                }
            }
    }


    // This function signs existing users through Firebase
    fun signInUser(
        activity: Activity,
        navigation: () -> Unit
    ) {

        // If the text fields are not valid, warn user.
        if (email.value.isNullOrEmpty() || password.value.isNullOrEmpty()) {
            makeToastMessage("Lütfen Gerekli Bilgileri Doldurun.", activity)
            return
        }

        // Authenticate user on Firebase
        auth.signInWithEmailAndPassword(email.value!!, password.value!!)
            .addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {

                    // Assign current user
                    auth.currentUser?.let {
                        currentUser = it
                    }

                    // Navigate to home page
                    navigation()
                    makeToastMessage("Giriş Başarılı", activity)


                } else {

                    var msg: String = "Error"
                    task.exception?.let {
                        msg = it.localizedMessage
                    }

                    // If sign in fails, display a message to the user.
                    makeToastMessage(msg, activity)

                }
            }
    }


    // Sign out the current user
    fun signOut() {
        auth.signOut()
    }



    // Reset the password for the given email
    fun resetPassword(activity: Activity) {

        email.value?.let {
            auth.sendPasswordResetEmail(it).addOnCompleteListener {

                // If reset is successful, tell user to control his e-mail
                if (it.isSuccessful) {
                    makeToastMessage("Lütfen Mail Addresinizi Kontrol Ediniz ",activity)
                } else {
                    // If there is an error, show user the error message
                    it.exception?.let {
                        makeToastMessage(it.localizedMessage, activity)
                    }
                }
            }
        }
    }



    // User name should be longer than three characters
    private fun validateUserName(userName: String): Boolean {
        return userName.length >= 3
    }



    private fun makeToastMessage(message: String, activity: Activity) {
        val toast = Toast.makeText(
            activity.baseContext, message,
            Toast.LENGTH_SHORT,
        ).show()
    }

}

