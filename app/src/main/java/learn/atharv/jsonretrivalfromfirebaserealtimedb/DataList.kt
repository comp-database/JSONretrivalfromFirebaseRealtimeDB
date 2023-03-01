package learn.atharv.jsonretrivalfromfirebaserealtimedb

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class DataList {
    companion object {
        private const val TAG: String = "testing new"
        fun allDataList(): ArrayList<studentDetail> {
            val datalist: ArrayList<studentDetail> = ArrayList<studentDetail>()

            var leadboards: String
            var name: String
            var points: String

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
                        for (all in document) {

//                        Log.d(TAG,"${all.id} => ${all.data}")
                            val allDetails = all.toObject<studentDetail>()

//                            leadBoards = allDetails.leadBoards.toString()
                            leadboards = allDetails.leadboards.toString()
                            name = allDetails.name.toString()
                            points = allDetails.points.toString()

                            val newStudent = studentDetail(leadboards, name, points)
                            datalist.add(newStudent)

//                            Log.d(TAG, "data  ${datalist} ")
                        }
                        Log.d(TAG, "data  ${datalist} ")
                    } else {
                        Log.d(TAG, "No such document")
                    }
                }
                .addOnFailureListener { exception ->
                    Log.d(TAG, "get failed with ", exception)
                }
            return datalist
        }
    }
}