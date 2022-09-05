package io.systemtest.matrimony.homeScreen.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

class LoadImage {

    companion object {
        @JvmStatic
        @BindingAdapter("profileImage")
        fun loadImage(view: ImageView, profileImage: String) {
            Picasso.get()
                .load(profileImage)
                .into(view)
        }
    }
}