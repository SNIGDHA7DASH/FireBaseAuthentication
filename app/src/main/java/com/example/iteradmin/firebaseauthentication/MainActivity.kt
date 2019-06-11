package com.example.iteradmin.firebaseauthentication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mAuth = FirebaseAuth.getInstance()
        val e = findViewById<EditText>(R.id.email)
        val p =findViewById<EditText>(R.id.password)

        val signUp = findViewById<Button>(R.id.sign_up)
        val login = findViewById<Button>(R.id.login)
        signUp.setOnClickListener {
            val email: String = e.text.toString()
            val password: String = p.text.toString()
            signInFirebase(email, password)
        }
        login.setOnClickListener {
            val email:String =e.text.toString()
            val password:String = p.text.toString()
            loginFirebase(email,password)
        }
    }



        private fun signInFirebase(email:String,password:String) {
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    startActivity(Intent(this,ProfileActivity::class.java))
                }else{
                    Toast.makeText(this,"email not  wrong",Toast.LENGTH_LONG).show()
                }
            }
        }




        private fun loginFirebase(email:String,password:String){
        mAuth.createUserWithEmailAndPassword(email,password).
                addOnCompleteListener(this){task ->
                    if (task.isSuccessful){
                        val user:FirebaseUser? = mAuth.currentUser
                        Toast.makeText(this,user?.displayName,Toast.LENGTH_LONG).show()
                        startActivity(Intent(this,ProfileActivity::class.java))
                      //  sign_up.visibility = View.INVISIBLE
                    }else{
                        Toast.makeText(this,"something got wrong",Toast.LENGTH_LONG).show()
                    }

                }

    }

    override fun onStart() {
        super.onStart()
        val user:FirebaseUser?=mAuth.currentUser
        if (user != null){
            startActivity(Intent(this,ProfileActivity::class.java))
    }

}
}