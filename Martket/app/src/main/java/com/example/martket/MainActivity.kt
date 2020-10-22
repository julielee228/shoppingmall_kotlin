package com.example.martket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = Firebase.database
        var myRef = database.getReference("productList")

        title = "PPI_market"

        var totalAmount: Int = 0

        goBasket.setOnClickListener() {

            var intent2 = Intent(this, BasketActivity::class.java)

            if (romandCheck.isChecked) {
                myRef.child("romand").setValue(9700)
            }
            if (clioCheck.isChecked) {
                myRef.child("clio").setValue(32000)
            }
            startActivity(intent2)
        }


        goBuy.setOnClickListener() {

            var intent3 = Intent(this, PaymentActivity::class.java)

            if (romandCheck.isChecked) {
                intent3.putExtra("romand", "romand")
                intent3.putExtra("romandPrice", 9700.toString()+"원")
                totalAmount += 9700
            }
            if (clioCheck.isChecked) {
                intent3.putExtra("clio", "clio")
                intent3.putExtra("clioPrice", 32000.toString()+"원")
                totalAmount += 32000
            }
            Log.d("얼마냐 ", totalAmount.toString())
            intent3.putExtra("totalAmount", totalAmount.toString())
            startActivity(intent3)
        }
    }
}
