package xyz.schwaab.image

import android.widget.ImageView

abstract class ImageViewLoader {
    fun loadImage(url: String?): URLSource {
        return URLSource(url)
    }

    protected abstract fun loadUrlIntoImageView(url: String, target: ImageView)

    inner class URLSource internal constructor(private val url: String?) {
        /**
         * Clears the imageView and tries to load the specified URL into the imageView target.
         * @return true if attempting to load image. false if aborted (due to empty or null URL, for example).
         */
        fun into(imageView: ImageView): Boolean {
            imageView.setImageDrawable(null)

            if (url.isNullOrBlank()) {
                return false
            }

            loadUrlIntoImageView(url, imageView)
            return true
        }
    }

    companion object
}
