package com.nick_sib.beauty_radar.model.data.entites

enum class FragmentType {
    SIGNUP {
        override fun next(): FragmentType = SIGNIN
    },
    SIGNIN {
        override fun next(): FragmentType = SIGNUP
    };

    abstract fun next(): FragmentType
}