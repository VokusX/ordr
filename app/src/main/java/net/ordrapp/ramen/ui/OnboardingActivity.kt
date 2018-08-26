package net.ordrapp.ramen.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_onboarding.*
import net.ordrapp.ramen.R
import net.ordrapp.ramen.data.AppDatabase
import net.ordrapp.ramen.data.User
import net.ordrapp.ramen.utils.runOnIoThread
import javax.inject.Inject


class OnboardingActivity : AppCompatActivity() {


    val auth = FirebaseAuth.getInstance()!!

    @Inject
    lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

        val client = GoogleSignIn.getClient(this, gso)

        signInButton.setOnClickListener {
            val signInIntent = client.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {

            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)

                val user = User(
                        account.displayName!!,
                        account.familyName,
                        account.familyName!!,
                        account.email!!,
                        account.id!!,
                        account.photoUrl!!.toString()
                )

                runOnIoThread {
                    database.userDao().insertUser(user)
                }

                val resultIntent = Intent()
                resultIntent.putExtra(USER_ID_KEY, user.id)
                setResult(Activity.RESULT_OK, resultIntent)

                finish()
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w("OnboardingActivity", "Google sign in failed", e)
                // ...

                setResult(Activity.RESULT_CANCELED)
                finish()
            }
        }
    }

    companion object {
        private const val RC_SIGN_IN = 420
        const val USER_ID_KEY = "user_id"
    }
}
