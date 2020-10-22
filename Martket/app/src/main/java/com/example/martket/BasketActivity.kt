package com.example.martket

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_basket.*
import kotlinx.android.synthetic.main.product.view.*


class BasketActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basket)

        title = "PPI_market"

        val database = Firebase.database
        val myRef = database.getReference("productList")
        var ProductList = arrayListOf<Product>()
        val Adapter = ProductAdapter(this, ProductList)

        var totalAmount = 0

        // 디비에 저장된 상품들을 리스트뷰에 나타냄
        myRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }
            override fun onDataChange(p0: DataSnapshot) {

                productList.adapter = Adapter

                for (snapshot in p0.children) {
                    //Log.d("key", snapshot.key.toString())
                    //Log.d("값", snapshot.value.toString())
                    //Log.d("type 확인하기 ", R.drawable.romand::class.simpleName!!)

                    ProductList.add(Product(snapshot.key.toString(), Integer.parseInt(snapshot.value.toString()), resources.getIdentifier(snapshot.key.toString(), "drawable", packageName)))
                }
            }
        })

        // 홈 버튼을 클릭 했을 때 장바구니 값을 초기화 시키기
        goHome.setOnClickListener() {
            myRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                }
                override fun onDataChange(p0: DataSnapshot) {

                    for (snapshot in p0.children) {
                        snapshot.ref.removeValue();
                    }
                }
            })

            var intent1 = Intent(this, MainActivity::class.java)
            startActivity(intent1)
        }

        // 구매하기 버튼을 클릭 했을 때
        goBuy2.setOnClickListener() {

            var intent3 = Intent(this, PaymentActivity::class.java)
            var cnt: Int = productList.count

            // 체크박스에 체크되어있는 상품을 구매하기 페이지로 넘겨주기
            for (i in 0..(cnt-1)) {
                val item = productList[i]
                val check = item.productCheck

                if (check.isChecked){
                    intent3.putExtra(item.productCheck.text.toString(),  item.productCheck.text.toString())
                    intent3.putExtra(item.productCheck.text.toString()+"Price", item.productPrice.text.toString()+"원")

                    totalAmount += Integer.parseInt(item.productPrice.text.toString())
                }
            }

            intent3.putExtra("totalAmount", totalAmount.toString())
            startActivity(intent3)
        }
    }
}

