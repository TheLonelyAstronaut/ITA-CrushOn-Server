package com.itechart.crushon.utils.enums

enum class Gender(
    private val fromString: String
)  {
    MALE("male"),
    FEMALE("female");

    override fun toString(): String {
        return fromString
    }

    companion object {
        fun fromString(string: String): Gender =
            if(string == "female") {
                FEMALE
            } else
                MALE
    }
}
