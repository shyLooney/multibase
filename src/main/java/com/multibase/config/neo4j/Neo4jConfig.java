package com.multibase.config.neo4j;

import com.multibase.entity.neo4j.Institute;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.AbstractNeo4jConfig;
import org.springframework.data.neo4j.core.DatabaseSelectionProvider;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.LinkedList;


@Configuration
@EnableNeo4jRepositories(
        basePackages = "com.multibase.repository.neo4j"
)
@EnableTransactionManagement
public class Neo4jConfig extends AbstractNeo4jConfig {
    @Override
    @Bean
    public Driver driver() {
        return GraphDatabase.driver("bolt://192.168.99.1:7687", AuthTokens.basic("neo4j", "password"));
    }

    @Override @Bean
    protected DatabaseSelectionProvider databaseSelectionProvider() {

        return DatabaseSelectionProvider.createStaticDatabaseSelectionProvider("neo4j");
    }
}
