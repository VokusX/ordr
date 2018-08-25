package net.ordrapp.ramen.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import net.ordrapp.ramen.R
import java.util.*
import java.util.Arrays.asList
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_onboarding.*
import net.ordrapp.ramen.data.AppDatabase
import net.ordrapp.ramen.data.User
import net.ordrapp.ramen.utils.runOnIoThread
import java.util.Arrays.asList
import java.util.Arrays.asList
import javax.inject.Inject


class OnboardingActivity : AppCompatActivity() {

    private val RC_SIGN_IN = 420

    val auth = FirebaseAuth.getInstance()!!

    @Inject lateinit var database : AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        signInButton.setOnClickListener { startActivityForResult(
                AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(listOf(AuthUI.IdpConfig.GoogleBuilder().build()))
                        .build(), RC_SIGN_IN) }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == RC_SIGN_IN){

            val response = IdpResponse.fromResultIntent(data)
            if(resultCode == Activity.RESULT_OK){

                runOnIoThread {
                    //TODO Get user data from response and send to database
                    database.userDao().insertUser() }

                finish()
                return
            }
        }
    }
}
