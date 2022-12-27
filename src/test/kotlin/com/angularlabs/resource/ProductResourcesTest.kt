package com.angularlabs.resource

import com.angularlabs.ProductServiceRequest
import com.angularlabs.ProductsServiceGrpc.ProductsServiceBlockingStub
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class ProductResourcesTest(
    private val productsServiceBlockingStub: ProductsServiceBlockingStub
) {
    @Test
    fun `When productsServiceGrp Create method is called with data valid, a success response is returned`(){
        val req = ProductServiceRequest.newBuilder()
            .setName("Product request")
            .setPrice(8.00)
            .setQuantityInStock(15)
            .build()

        val res = productsServiceBlockingStub.create(req)

        Assertions.assertEquals(1,res.id)
        Assertions.assertEquals("Product request", res.name)
    }

    @Test
    fun `When productsServiceGrp findById method is called with id equal 1, a success response is returned`(){
//        val req = FindByIdServiceRequest.newBuilder()
//            .setId(1)
//            .build()
//
//        val res = productsServiceBlockingStub.findById(req)
//
//        Assertions.assertEquals(1,res.id)
//        Assertions.assertEquals("Product Test", res.name) // because inserted by migration
    }
}