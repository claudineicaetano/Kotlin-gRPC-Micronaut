package com.angularlabs.util

import com.angularlabs.ProductServiceRequest

class ValidationUtil {
    companion object{
        fun validationPayload(payload: ProductServiceRequest?): ProductServiceRequest {
            payload?.let {
                if (it.name.isNullOrBlank())
                    throw IllegalArgumentException("The name field cannot be null or empty")

                if (it.price.isNaN() || it.price < 0)
                    throw IllegalArgumentException("The price filed should be number greater than zero")

                return it
            }
            throw IllegalArgumentException()
        }
    }
}