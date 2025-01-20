package com.example.m5lesson4_retrofitmvvm_rickandmortyapi

enum class CharacterStatus(val colorResId: Int) {
    ALIVE(android.R.color.holo_green_light),
    DEAD(android.R.color.holo_red_light),
    UNKNOWN(android.R.color.darker_gray)
}