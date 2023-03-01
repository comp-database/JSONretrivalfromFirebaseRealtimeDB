package learn.atharv.jsonretrivalfromfirebaserealtimedb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import learn.atharv.jsonretrivalfromfirebaserealtimedb.databinding.ActivityLoginscreenBinding
import org.checkerframework.checker.units.qual.A

class loginscreen : AppCompatActivity() {

    lateinit var binding: ActivityLoginscreenBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_loginscreen)

        binding = ActivityLoginscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)


        auth = FirebaseAuth.getInstance()
        binding.loginBtn.setOnClickListener {
            val email = binding.email.text.toString()
            val pass = binding.password.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty()) {

                auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val intent = Intent(this, UserInfo::class.java)
                        intent.putExtra("Email",binding.email.text.toString())
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()

                    }
                }
            } else {
                Toast.makeText(this, "Empty Fields Are not Allowed !!", Toast.LENGTH_SHORT).show()

            }
        }
    }
    override fun onStart() {
        super.onStart()
        if(auth.currentUser != null){
            val intent = Intent(this, UserInfo::class.java)
            intent.putExtra("Email",binding.email.text.toString())
            startActivity(intent)
        }
    }
}