package com.angularlabs.domain.repository

import com.angularlabs.domain.entity.Product
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository

@Repository
interface ProductRepository: JpaRepository<Product, Long>{
    fun findByNameIgnoreCase(name: String): Product?
}