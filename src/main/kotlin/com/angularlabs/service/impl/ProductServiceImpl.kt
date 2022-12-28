package com.angularlabs.service.impl

import com.angularlabs.domain.repository.ProductRepository
import com.angularlabs.dto.ProductReq
import com.angularlabs.dto.ProductRes
import com.angularlabs.exception.AlreadyExistsException
import com.angularlabs.exception.ProductNotExistsException
import com.angularlabs.service.ProductService
import com.angularlabs.util.toDomain
import com.angularlabs.util.toProductRes
import jakarta.inject.Singleton

@Singleton
class ProductServiceImpl(private val productRepository: ProductRepository) : ProductService {
    override fun create(req: ProductReq): ProductRes {
        verifyName(req.name)
        val productSaved = productRepository.save(req.toDomain())
        return productSaved.toProductRes()
    }

    override fun findById(id: Long): ProductRes {
        val productFind = productRepository.findById(id)

        productFind.orElseThrow { ProductNotExistsException(id) }

        return productFind.get().toProductRes()
    }

    private fun verifyName( name:String ){
        productRepository.findByNameIgnoreCase(name)?.let {
            throw AlreadyExistsException(name)
        }
    }
}