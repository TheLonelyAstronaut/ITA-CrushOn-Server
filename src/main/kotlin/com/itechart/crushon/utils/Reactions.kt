package com.itechart.crushon.utils

enum class Reactions(
    private val fromString: String
) {
    LIKE("like"),
    DISLIKE("dislike");

    override fun toString(): String {
        return fromString
    }
}
