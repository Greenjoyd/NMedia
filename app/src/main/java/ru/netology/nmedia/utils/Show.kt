package ru.netology.nmedia.utils

import java.lang.Math.floor
import java.math.RoundingMode
import java.text.DecimalFormat

object Show {
    fun showCount(int: Int): String {
        if (int < 1000) {
            return int.toString()
        } else if (int >= 1000 && int <= 10000) {
            return (floor((int.toDouble() / 1_000) * 10) / 10.0).toString()
        } else if (int > 10000 && int < 100000) {
            var st = int.toString()
            var ch = st.substring(0, 2)
            return ch + "K"
        } else if (int >= 100000 && int < 1000000) {
            var st = int.toString()
            var ch = st.substring(0, 3)
            return ch + "K"
        } else if (int >= 1000000 && int < 10000000) {
            return (floor((int.toDouble() / 1_000_000) * 10) / 10.0).toString()
        } else {
            return "10M+"
        }
    }

    }
