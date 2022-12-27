package com.angularlabs.util

import com.angularlabs.domain.entity.Product
import com.angularlabs.dto.ProductReq
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ProductConverterUtilsTest {

    @Test
    fun `When toProductRes method in ProductConverter is called, should return a ProductRes with all data`(){
        val product = Product(
            1,
            "Some product",
            10.00,
            15
        )
        val productRes = product.toProductRes()
        Assertions.assertEquals(productRes.id, product.id)
        Assertions.assertEquals(productRes.name, product.name)
        Assertions.assertEquals(productRes.price, product.price)
        Assertions.assertEquals(productRes.quantityInStock, product.quantityInStock)
    }

    @Test
    fun `When toDomain method in ProductConverter is called, should return a Product with all data`(){
        val productReq = ProductReq(
            "Some product",
            10.00,
            15
        )
        val product = productReq.toDomain()
        Assertions.assertEquals(null, product.id)
        Assertions.assertEquals(productReq.name, product.name)
        Assertions.assertEquals(productReq.price, product.price)
        Assertions.assertEquals(productReq.quantityInStock, product.quantityInStock)
    }
}