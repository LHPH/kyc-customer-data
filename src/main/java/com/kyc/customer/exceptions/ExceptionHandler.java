package com.kyc.customer.exceptions;

import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.execution.DataFetcherExceptionHandler;
import graphql.execution.DataFetcherExceptionHandlerParameters;
import graphql.execution.DataFetcherExceptionHandlerResult;

public class ExceptionHandler implements DataFetcherExceptionHandler {

    @Override
    public DataFetcherExceptionHandlerResult onException(DataFetcherExceptionHandlerParameters params) {

        Throwable exception = params.getException();

        GraphQLError error = null;

        if( exception instanceof  CustomerException){

            CustomerException custom = (CustomerException)exception;
            error = GraphqlErrorBuilder
                    .newError()
                    .extensions(custom.getMetaData())
                    .message(custom.getMessage())
                    .build();
        }
        else{
            error = GraphqlErrorBuilder.newError().message(exception.getMessage()).build();
        }

        return DataFetcherExceptionHandlerResult
                .newResult()
                .error(error)
                .build();

    }
}
