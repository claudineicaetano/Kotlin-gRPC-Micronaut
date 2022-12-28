package com.angularlabs.exception

import io.grpc.Status

class ProductNotExistsException(private val productId: Long?): BaseBusinessException() {
    override fun errorMessage(): String {
        return "The product with ID: $productId wasn't found in the database."
    }

    override fun statusCode(): Status.Code {
        return Status.Code.NOT_FOUND
    }
}