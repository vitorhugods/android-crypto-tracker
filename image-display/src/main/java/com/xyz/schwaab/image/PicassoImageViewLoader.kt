package xyz.schwaab.image

import android.widget.ImageView
import com.squareup.picasso.Picasso

internal class PicassoImageViewLoader(private val picasso: Picasso) : ImageViewLoader() {

    override fun loadUrlIntoImageView(url: String, target: ImageView) {
        picasso.load(url).into(target)
    }
}

fun ImageViewLoader.Companion.usingPicasso(picasso: Picasso): ImageViewLoader {
    return PicassoImageViewLoader(picasso)
}
