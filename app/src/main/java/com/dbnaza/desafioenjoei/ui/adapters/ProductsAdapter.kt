package com.dbnaza.desafioenjoei.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dbnaza.desafioenjoei.R
import com.dbnaza.desafioenjoei.api.Product
import com.dbnaza.desafioenjoei.extensions.formatCurrency
import com.dbnaza.desafioenjoei.extensions.setVisibility
import com.dbnaza.desafioenjoei.ui.DetailsActivity
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation
import kotlinx.android.synthetic.main.item_product.view.*


class ProductsAdapter(private val list: List<Product>) : RecyclerView.Adapter<ProductsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        holder.bindView(list[position])
    }
}

val roundedCornersTransformation: RoundedCornersTransformation by lazy {
    RoundedCornersTransformation(20, 0, RoundedCornersTransformation.CornerType.TOP)
}

val circularTransformation: CropCircleTransformation by lazy {
    CropCircleTransformation()
}

val picasso: Picasso by lazy {
    Picasso.get()
}

class ProductsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bindView(product: Product?) {
        val context = itemView.context
        product?.let { prod ->
            if (prod.images.isEmpty().not()) {
                picasso.load(prod.images[0])
                    .fit()
                    .centerCrop()
                    .placeholder(R.drawable.rounded_placeholder)
                    .transform(roundedCornersTransformation)
                    .into(itemView.imgProduct)
            }
            picasso
                .load(prod.owner?.picture)
                .fit()
                .centerCrop()
                .placeholder(R.drawable.circular_placeholder)
                .transform(circularTransformation)
                .into(itemView.imgProfile)

            itemView.txtProductName.text = prod.name
            itemView.txtProductPrice.text = prod.finalPrice.formatCurrency()
            itemView.txtDiscount.setVisibility(prod.discount > 0)
            itemView.txtDiscount.text = String.format(context.getString(R.string.item_discount), prod.discount)
            itemView.txtLikes.text = prod.likes.toString()

            itemView.setOnClickListener {
                context.startActivity(DetailsActivity.getStartIntent(context, prod))
            }
        }
    }
}