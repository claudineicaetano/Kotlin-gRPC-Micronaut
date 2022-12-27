package com.angularlabs.resource

import com.angularlabs.FindByIdServiceRequest
import com.angularlabs.ProductServiceRequest
import com.angularlabs.ProductServiceResponse
import com.angularlabs.ProductsServiceGrpc.ProductsServiceImplBase
import com.angularlabs.dto.ProductReq
import com.angularlabs.exception.BaseBusinessException
import com.angularlabs.service.ProductService
import com.angularlabs.util.ValidationUtil
import io.grpc.stub.StreamObserver
import io.micronaut.grpc.annotation.GrpcService

@GrpcService
class ProductResources(private val productService: ProductService): ProductsServiceImplBase() {
    override fun create(request: ProductServiceRequest?, responseObserver: StreamObserver<ProductServiceResponse>?) {
        try {
            val payload = ValidationUtil.validationPayload(request)
            val productReq = ProductReq(
                name = payload.name,
                price = payload.price,
                quantityInStock = payload.quantityInStock
            )

            val productRes = productService.create(productReq)

            val productResponse = ProductServiceResponse.newBuilder()
                .setId(productRes.id!!)
                .setName(productRes.name)
                .setPrice(productRes.price)
                .setQuantityInStock(productRes.quantityInStock)
                .build()

            responseObserver?.onNext(productResponse)
            responseObserver?.onCompleted()
        } catch ( ex: BaseBusinessException ){
            responseObserver?.onError(ex.statusCode().toStatus()
                .withDescription(ex.errorMessage()).asRuntimeException())
        }
    }

    override fun findById(
        request: FindByIdServiceRequest?,
        responseObserver: StreamObserver<ProductServiceResponse>?
    ) {
        try {
            val productRes = productService.findById(request!!.id)

            val productResponse = ProductServiceResponse.newBuilder()
                .setId(productRes.id!!)
                .setName(productRes.name)
                .setPrice(productRes.price)
                .setQuantityInStock(productRes.quantityInStock)
                .build()

            responseObserver?.onNext(productResponse)
            responseObserver?.onCompleted()
        } catch ( ex: BaseBusinessException ){ // life long and prosper to polymorphism -- is same above!
            responseObserver?.onError(ex.statusCode().toStatus()
                .withDescription(ex.errorMessage()).asRuntimeException())
        }
    }
}