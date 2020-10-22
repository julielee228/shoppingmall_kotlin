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

        title = "PPI_market"

        val database = Firebase.database
        var myRef = database.getReference("productList")
        var totalAmount = 0

        // 장바구니 버튼을 클릭했을 때
        goBasket.setOnClickListener() {

            var intent2 = Intent(this, BasketActivity::class.java)

            // 체크박스에 체크되어있는 상품은 파이어베이스 디비에 저장
            if (romandCheck.isChecked) {
                myRef.child("romand").setValue(9700)
            }
            if (clioCheck.isChecked) {
                myRef.child("clio").setValue(32000)
            }
            startActivity(intent2)
        }

        // 구매하기 버튼을 클릭했을 때
        goBuy.setOnClickListener() {

            var intent3 = Intent(this, PaymentActivity::class.java)

            // 체크박스에 체크되어있는 상품을 구매하기 페이지로 넘겨주기
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
//            Log.d("총가격 : ", totalAmount.toString())
            intent3.putExtra("totalAmount", totalAmount.toString())
            startActivity(intent3)
        }
    }
}
