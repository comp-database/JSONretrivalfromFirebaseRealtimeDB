package learn.atharv.jsonretrivalfromfirebaserealtimedb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import learn.atharv.jsonretrivalfromfirebaserealtimedb.databinding.ActivityUserInfoBinding

class UserInfo : AppCompatActivity() {
    lateinit var binding: ActivityUserInfoBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_user_info)
        binding = ActivityUserInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()

        binding.emailText.text =auth.currentUser?.email

        binding.DataBtn.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
        binding.LogOutBtn.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this,loginscreen::class.java))
            finish()
        }
        val db = Firebase.firestore
        val docRef = db.collection("new")
        docRef.get()
            .addOnSuccessListener { document ->
                // here we can change the whatever required output formatting

                if (document != null) {
//                    Log.d(TAG, "DocumentSnapshot data: ${document.data}")
                    //to convert the data into data class
//                    val Student_detail = document.toObject<studentDetail>()
//                    Log.d(TAG,"data in form of data class ${Student_detail?.name} ")
                    for (all in document){
                        val allDetails = all.toObject<studentDetail>()
                        if (allDetails.leadboards.toString() == auth.currentUser?.email.toString()){
                            binding.pointText.text = allDetails.points.toString()
                        }
                        Log.d("TAG","name data  ${allDetails.leadboards} ")
                    }
                } else {
                    Log.d("TAG", "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("TAG", "get failed with ", exception)
            }
    }
}