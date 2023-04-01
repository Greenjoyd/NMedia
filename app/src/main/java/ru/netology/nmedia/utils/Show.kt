package ru.netology.nmedia.utils

object Show {
    fun showCount(int: Int): String = when {
        int < 1000 -> int.toString()
        int in 1000..10000 -> "${kotlin.math.floor((int.toDouble() / 1_000) * 10) / 10.0}K"
        int in 10000..99999 -> "${int.toString().substring(0, 2)}K"
        int in 100000..999999 -> "${int.toString().substring(0, 3)}K"
        int in 1000000..9999999 -> "${kotlin.math.floor((int.toDouble() / 1_000_000) * 10) / 10.0}M"
        else -> "10M+"
    }
}
