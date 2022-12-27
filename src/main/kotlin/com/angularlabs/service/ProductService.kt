package com.angularlabs.service

import com.angularlabs.dto.ProductReq
import com.angularlabs.dto.ProductRes

interface ProductService {
    fun create(req: ProductReq): ProductRes
}