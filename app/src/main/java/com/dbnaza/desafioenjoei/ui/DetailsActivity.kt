package com.dbnaza.desafioenjoei.ui

import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.dbnaza.desafioenjoei.api.Product
import com.dbnaza.desafioenjoei.extensions.configureToolbar
import com.dbnaza.desafioenjoei.extensions.extra
import com.dbnaza.desafioenjoei.extensions.formatCurrency
import com.dbnaza.desafioenjoei.extensions.setVisibility
import com.dbnaza.desafioenjoei.ui.adapters.ProductImagesPageAdapter
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.comments_button.*
import kotlinx.android.synthetic.main.content_details.*
import android.text.Spannable
import android.graphics.Typeface
import android.text.SpannableStringBuilder
import com.dbnaza.desafioenjoei.utils.CustomTypefaceSpan
import android.text.style.ForegroundColorSpan
import android.graphics.Color
import androidx.core.content.res.ResourcesCompat
import com.dbnaza.desafioenjoei.R


class DetailsActivity : AppCompatActivity() {

    private val product by extra<Product?>(PRODUCT) { null }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        configureToolbar(toolbar)

        configureViews()
    }

    private fun configureViews() {
        product?.let {
            txtFinalPrice.text = makeTextBold(it.finalPrice.formatCurrency())
            txtOriginalPrice.text = it.price.formatCurrency()
            txtProductName.text = it.name
            txtProductDescription.text = it.description

            if (it.discount > 0) {
                txtInstallments.text = getString(
                    R.string.installments_with_discount,
                    it.discount,
                    it.maxInstallments
                )
            } else {
                txtInstallments.text = getString(R.string.installments, it.maxInstallments)
            }

            pager.adapter = ProductImagesPageAdapter(it.images)
            dotsIndicator.setViewPager(pager)

            txtOriginalPrice.paintFlags = txtOriginalPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

            val hasComments = it.comments > 0
            txtCommentsCount.setVisibility(hasComments, false)
            txtCommentsCount.text = it.comments.toString()
            if (hasComments) {
                btnCommentsWithBadge.setColorFilter(
                    ContextCompat.getColor(this, R.color.pink),
                    PorterDuff.Mode.SRC_ATOP
                )
            }
        }
    }

    private fun makeTextBold(
        string: String
    ): SpannableStringBuilder {
        val sb = SpannableStringBuilder(string)

        val regular = ResourcesCompat.getFont(
            this,
            R.font.proxima_nova_regular
        )
        val bold = ResourcesCompat.getFont(
            this,
            R.font.proxima_nova_bold
        )

        val regularSpan = CustomTypefaceSpan(newType = regular!!)
        val boldSpan = CustomTypefaceSpan(newType = bold!!)

        sb.setSpan(regularSpan, 0, 2, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        sb.setSpan(boldSpan, 2, string.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        return sb
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_details, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        private const val PRODUCT = "product"

        @JvmStatic
        fun getStartIntent(context: Context, product: Product): Intent {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra(PRODUCT, product)
            return intent
        }
    }
}
