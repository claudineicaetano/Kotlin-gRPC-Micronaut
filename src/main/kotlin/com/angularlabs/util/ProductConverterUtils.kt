package com.angularlabs.util

import com.angularlabs.domain.entity.Product
import com.angularlabs.dto.ProductReq
import com.angularlabs.dto.ProductRes

fun Product.toProductRes(): ProductRes {
    return ProductRes(
        id = id,
        name = name,
        price = price,
        quantityInStock = quantityInStock
    )
}

fun ProductReq.toDomain(): Product {
    return Product(
        id = null,
        name = name,
        price = price,
        quantityInStock = quantityInStock
    )
}