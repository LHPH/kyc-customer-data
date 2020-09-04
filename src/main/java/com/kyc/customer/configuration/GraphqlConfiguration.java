package com.kyc.customer.configuration;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.kyc.customer.exceptions.ExceptionHandler;
import com.kyc.customer.graphql.fetchers.AddCustomerFetcher;
import com.kyc.customer.graphql.fetchers.DeleteCustomerFetcher;
import com.kyc.customer.graphql.fetchers.RetrieveAllCustomerFetcher;
import com.kyc.customer.graphql.fetchers.RetrieveCustomerByParameterFetcher;
import com.kyc.customer.graphql.fetchers.UpdateCustomerFetcher;
import graphql.GraphQL;
import graphql.execution.AsyncExecutionStrategy;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.URL;

@Configuration
public class GraphqlConfiguration {

    @Autowired
    private RetrieveAllCustomerFetcher retrieveAllCustomerFetcher;

    @Autowired
    private RetrieveCustomerByParameterFetcher retrieveCustomerByParameterFetcher;

    @Autowired
    private AddCustomerFetcher addCustomerFetcher;

    @Autowired
    private UpdateCustomerFetcher updateCustomerFetcher;

    @Autowired
    private DeleteCustomerFetcher deleteCustomerFetcher;

    @Bean
    public GraphQL graphql() throws IOException {

        URL url = Resources.getResource("graphql/schema.graphqls");
        String sdl = Resources.toString(url, Charsets.UTF_8);
        GraphQLSchema graphQLSchema = buildSchema(sdl);
        GraphQL graphQL = GraphQL.newGraphQL(graphQLSchema)
                .queryExecutionStrategy(new AsyncExecutionStrategy(new ExceptionHandler()))
                .mutationExecutionStrategy(new AsyncExecutionStrategy(new ExceptionHandler())).build();
        return graphQL;
    }

    private GraphQLSchema buildSchema(String sdl){

        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(sdl);
        RuntimeWiring runtimeWiring = buildWiring();
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);

    }

    private RuntimeWiring buildWiring() {

        return RuntimeWiring.newRuntimeWiring()
                .type("Query",
                        typeWiring -> typeWiring
                                .dataFetcher("customers",retrieveAllCustomerFetcher)
                                .dataFetcher("customer",retrieveCustomerByParameterFetcher)
                                )
                .type("Mutation",
                        typeWiring -> typeWiring
                                .dataFetcher("addCustomer",addCustomerFetcher)
                                .dataFetcher("updateCustomer",updateCustomerFetcher)
                                .dataFetcher("deleteCustomer",deleteCustomerFetcher)
                                )
                .build();
    }
}
