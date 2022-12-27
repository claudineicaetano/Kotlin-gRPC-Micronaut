package com.angularlabs.service.impl

import com.angularlabs.domain.entity.Product
import com.angularlabs.domain.repository.ProductRepository
import com.angularlabs.dto.ProductReq
import com.angularlabs.exception.AlreadyExistsException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`

internal class ProductServiceImplTest {
    private val productRepository = Mockito.mock(ProductRepository::class.java)
    private val productService = ProductServiceImpl(productRepository)

    @Test
    fun `when create method is call with valid data a ProductRes is returned`(){
        val productInput = Product(
            id = null,
            name = "product name",
            price = 10.0,
            quantityInStock = 5
        )
        val productOutput = Product(
            id = 1,
            name = "product name",
            price = 10.0,
            quantityInStock = 5
        )

        `when`(productRepository.save(productInput)).thenReturn(productOutput)

        val productReq = ProductReq(name = "product name", price = 10.0, quantityInStock = 5)

        val productRes = productService.create(productReq)

        assertEquals(productRes.name, productOutput.name)
        assertEquals(productRes.price, productOutput.price)
        assertEquals(productRes.quantityInStock, productOutput.quantityInStock)
    }

    @Test
    fun `when the create method is called with the duplicate product name, the exception must be thrown`(){
        val productInput = Product(
            id = null,
            name = "product name",
            price = 10.0,
            quantityInStock = 5
        )
        val productOutput = Product(
            id = 1,
            name = "product name",
            price = 10.0,
            quantityInStock = 5
        )

        `when`(productRepository.findByNameIgnoreCase(productInput.name))
            .thenReturn(productOutput)

        val productReq = ProductReq(name = "product name", price = 10.0, quantityInStock = 5)

        Assertions.assertThrowsExactly(AlreadyExistsException::class.java){
            productService.create(productReq)
        }
    }

}