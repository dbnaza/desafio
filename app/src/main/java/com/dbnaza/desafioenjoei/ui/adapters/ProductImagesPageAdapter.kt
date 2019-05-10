package com.dbnaza.desafioenjoei.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.dbnaza.desafioenjoei.App
import com.dbnaza.desafioenjoei.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_product_pictures.view.*


class ProductImagesPageAdapter(private val images: List<String>) : PagerAdapter() {

    private val picasso: Picasso by lazy {
        Picasso.get()
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean = obj == view

    override fun getCount(): Int = images.size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(App.instance)
        val layout = inflater.inflate(
            R.layout.item_product_pictures,
            container, false
        ) as ImageView

        picasso.load(images[position])
            .fit()
            .centerCrop()
            .placeholder(R.drawable.rounded_placeholder)
            .transform(roundedCornersTransformation)
            .into(layout)

        container.addView(layout);
        return layout
    }

}