package com.angularlabs.util

import com.angularlabs.ProductServiceRequest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ValidationUtilTest {
    @Test
    fun `When validation method is call with valid data in payload, should not throw any exception`() {
        val request = ProductServiceRequest.newBuilder()
            .setName("Product Name")
            .setPrice(20.0)
            .setQuantityInStock(3)
            .build()
        Assertions.assertDoesNotThrow{
            ValidationUtil.validationPayload(request)
        }
    }

    @Test
    fun `When validation method is call without prop name, should throw exception`() {
        val request = ProductServiceRequest.newBuilder()
            .setName("")
            .setPrice(20.0)
            .setQuantityInStock(3)
            .build()
        Assertions.assertThrowsExactly(IllegalArgumentException::class.java){
            ValidationUtil.validationPayload(request)
        }
    }


    @Test
    fun `When validation method is call with invalid price, should throw exception`() {
        val request = ProductServiceRequest.newBuilder()
            .setName("Some product name")
            .setPrice(-1.0)
            .setQuantityInStock(3)
            .build()
        Assertions.assertThrowsExactly(IllegalArgumentException::class.java){
            ValidationUtil.validationPayload(request)
        }
    }
}