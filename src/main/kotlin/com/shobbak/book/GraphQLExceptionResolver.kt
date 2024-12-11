package com.shobbak.book

import graphql.GraphQLError
import graphql.GraphqlErrorBuilder
import graphql.schema.DataFetchingEnvironment
import jakarta.validation.ConstraintViolationException
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter
import org.springframework.stereotype.Component
import org.springframework.web.bind.MethodArgumentNotValidException


@Component
class GraphQLExceptionResolver : DataFetcherExceptionResolverAdapter() {
    override fun resolveToSingleError(ex: Throwable, env: DataFetchingEnvironment): GraphQLError? {
        return if (ex is ConstraintViolationException) {
            val errorMessages = ex.constraintViolations.joinToString(", ") { violation ->
                "${violation.propertyPath}: ${violation.message}"
            }

            GraphqlErrorBuilder.newError()
                .message("Validation error: $errorMessages")
                .build()
        } else {
            null
        }
    }
}