package com.kdu.food.domain

import org.springframework.data.jpa.repository.JpaRepository

interface CampusFoodRepository : JpaRepository<CampusFood, Long>