package com.example.martket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_basket.*
import kotlinx.android.synthetic.main.activity_basket.goHome
import kotlinx.android.synthetic.main.activity_payment.*

class PaymentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        title = "PPI_market"

        val database = Firebase.database
        val myRef = database.getReference("productList")

        if (intent.hasExtra("romand"))
        {
            firstItemName.setText(intent.getStringExtra("romand"))
            firstItemPrice.setText(intent.getStringExtra("romandPrice"))
        }

        if (intent.hasExtra("clio"))
        {
            secondItemName.setText(intent.getStringExtra("clio"))
            secondItemPrice.setText(intent.getStringExtra("clioPrice"))
        }

        totalPrice.setText(intent.getStringExtra("totalAmount")+"원")

        // 구매하기 버튼을 클릭 했을 때 장바구니 값을 초기화 시키기
        goHome.setOnClickListener {
            myRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                }
                override fun onDataChange(p0: DataSnapshot) {

                    for (snapshot in p0.children) {
                        snapshot.ref.removeValue();
                    }
                }
            })
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}