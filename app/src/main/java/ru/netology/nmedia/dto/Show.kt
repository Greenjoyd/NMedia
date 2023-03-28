package ru.netology.nmedia.dto

import ru.netology.nmedia.dto.Post
import java.math.RoundingMode
import java.text.DecimalFormat

class Show {
    fun showShare(post: Post): String {
        if (post.share < 1000) {
            return post.share.toString()
        } else if (post.share >= 1000 && post.share <= 10000) {
            var decimalShare = roundOffDecimal(post.share.toDouble() / 1000)
            return decimalShare.toString() + "K"
        } else if (post.share > 10000 && post.share < 100000) {
            var st = post.share.toString()
            var ch = st.substring(0, 2)
            return ch + "K"
        } else if (post.share >= 100000 && post.share < 1000000) {
            var st = post.share.toString()
            var ch = st.substring(0, 3)
            return ch + "K"
        } else if (post.share >= 1000000 && post.share < 10000000) {
            var decimalShare = roundOffDecimal(post.share.toDouble() / 1000000)
            return decimalShare.toString() + "M"
        } else {
            return "10M+"
        }
    }

    fun showLike(post: Post): String {
        if (post.likes < 1000) {
            return post.likes.toString()
        } else if (post.likes >= 1000 && post.likes <= 10000) {
            var decimalShare = roundOffDecimal(post.likes.toDouble() / 1000)
            return decimalShare.toString() + "K"
        } else if (post.likes > 10000 && post.likes < 100000) {
            var st = post.likes.toString()
            var ch = st.substring(0, 2)
            return ch + "K"
        } else if (post.likes >= 100000 && post.likes < 1000000) {
            var st = post.likes.toString()
            var ch = st.substring(0, 3)
            return ch + "K"
        } else if (post.likes >= 1000000 && post.likes < 10000000) {
            var decimalShare = roundOffDecimal(post.likes.toDouble() / 1000000)
            return decimalShare.toString() + "M"
        } else {
            return "10M+"
        }
    }
        fun showViews(post: Post): String {
            if (post.views < 1000) {
                return post.views.toString()
            } else if (post.views >= 1000 && post.views <= 10000) {
                var decimalShare = roundOffDecimal(post.views.toDouble() / 1000)
                return decimalShare.toString() + "K"
            } else if (post.views > 10000 && post.views < 100000) {
                var st = post.views.toString()
                var ch = st.substring(0, 2)
                return ch + "K"
            } else if (post.views >= 100000 && post.views < 1000000) {
                var st = post.views.toString()
                var ch = st.substring(0, 3)
                return ch + "K"
            } else if (post.views >= 1000000 && post.views < 10000000) {
                var decimalShare = roundOffDecimal(post.views.toDouble() / 1000000)
                return decimalShare.toString() + "M"
            } else {
                return "10M+"
            }
        }

        fun roundOffDecimal(number: Double): Double? {
            val df = DecimalFormat("#.#")
            df.roundingMode = RoundingMode.FLOOR
            return df.format(number).toDouble()
        }
    }
