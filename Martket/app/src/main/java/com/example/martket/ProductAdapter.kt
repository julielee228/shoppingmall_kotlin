package com.example.martket

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView


// 데이터와 리스트뷰 연결을 위한 어댑터 사용하기
class ProductAdapter(val context: BasketActivity, val ProductList: ArrayList<Product>) : BaseAdapter()
{
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = LayoutInflater.from(context).inflate(R.layout.product, null)

        val name = view.findViewById<CheckBox>(R.id.productCheck)
        val price = view.findViewById<TextView>(R.id.productPrice)
        val image = view.findViewById<ImageView>(R.id.productImage)

        val product = ProductList[position]

        name.text = product.name
        name.isChecked = false
        price.text = product.price.toString()
        image.setImageResource(product.image)

        return view
    }

    override fun getItem(position: Int): Any {
        return ProductList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return ProductList.size
    }
}
