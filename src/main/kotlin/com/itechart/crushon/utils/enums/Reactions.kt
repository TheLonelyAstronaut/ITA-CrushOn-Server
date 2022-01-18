package com.itechart.crushon.utils.enums

enum class Reactions(
    private val fromString: String
) {
    LIKE("like"),
    DISLIKE("dislike");

    override fun toString(): String {
        return fromString
    }

    companion object {
        fun fromString(string: String): Reactions =
            if(string == "like") {
                LIKE
            } else
                DISLIKE
    }
}
