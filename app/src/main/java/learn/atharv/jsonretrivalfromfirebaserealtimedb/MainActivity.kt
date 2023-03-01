package learn.atharv.jsonretrivalfromfirebaserealtimedb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import learn.atharv.jsonretrivalfromfirebaserealtimedb.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var db = Firebase.firestore
    lateinit var datalist: ArrayList<studentDetail>
    lateinit var adapterRc :RcAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


//        val myData = DataList.allDataList()
        val recyclerView = binding.rv
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        datalist = arrayListOf()
        adapterRc = RcAdapter(datalist)
        recyclerView.adapter = adapterRc

        Log.d("data ","${intent.getStringExtra("Email")}")
        EventChangeListener()

//        val db = Firebase.firestore
//        val docRef = db.collection("new")
//        docRef.get()
//            .addOnSuccessListener { document ->
//                // here we can change the whatever required output formatting
//
//                if (document != null) {
////                    Log.d(TAG, "DocumentSnapshot data: ${document.data}")
//                    //to convert the data into data class
////                    val Student_detail = document.toObject<studentDetail>()
////                    Log.d(TAG,"data in form of data class ${Student_detail?.name} ")
//                    for (all in document){
//                        Log.d(TAG,"${all.id} => ${all.data}")
//                        val allDetails = all.toObject<studentDetail>()
//                        Log.d(TAG,"name data  ${allDetails.leadboards} ")
//                    }
//                } else {
//                    Log.d(TAG, "No such document")
//                }
//            }
//            .addOnFailureListener { exception ->
//                Log.d(TAG, "get failed with ", exception)
//            }
    }

    private fun EventChangeListener() {
        db = FirebaseFirestore.getInstance()
        db.collection("new")
            .addSnapshotListener(object :EventListener<QuerySnapshot>{
                override fun onEvent(
                    value : QuerySnapshot?,
                    error : FirebaseFirestoreException?
                ){
                    if (error != null){
                        Log.e("Firestore Error",error.message.toString())
                        return
                    }
                    for(dc : DocumentChange in value?.documentChanges!!){
                        if(dc.type == DocumentChange.Type.ADDED){
                            datalist.add(dc.document.toObject(studentDetail::class.java))
                        }
                    }
                    adapterRc.notifyDataSetChanged()
                }
            })
    }
//    companion object {
//        private val TAG: String = "testing"
//    }
}