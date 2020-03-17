package com.kdu.food.domain

class CampusFoods(private val campusFoods: ArrayList<CampusFood>) {

    fun get(): ArrayList<CampusFood> {
        return campusFoods
    }
}