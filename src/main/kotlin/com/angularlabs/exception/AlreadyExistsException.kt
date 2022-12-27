package com.angularlabs.exception

import io.grpc.Status

class AlreadyExistsException(private val productName: String?): BaseBusinessException() {
    override fun errorMessage(): String {
        return "The product $productName already registered in the database."
    }

    override fun statusCode(): Status.Code {
        return Status.Code.ALREADY_EXISTS
    }
}