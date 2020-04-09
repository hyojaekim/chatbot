package com.kdu.life.domain

import kotlin.streams.toList

data class MaskStockInfo(
        val count: Int,
        var stores: List<MaskStock>
) {
    init {
        this.stores = this.stores.stream()
                .filter { maskStock -> !maskStock.isEmpty() }
                .limit(MAX_SIZE)
                .toList()
    }

    companion object {
        private const val MAX_SIZE = 20L
    }
}