package com.multibase.config.elastic;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@DependsOn("generator")
@EnableElasticsearchRepositories(basePackages = "com.multibase.repository.elastic")
@ComponentScan(basePackages = { "com.multibase.entity.elastic" })
public class ElasticConfig {
    @Bean
    public ElasticsearchClient elasticsearchClient() {
        RestClient httpClient = RestClient.builder(new HttpHost("localhost", 9200)).build();

        ElasticsearchTransport transport = new RestClientTransport(httpClient, new JacksonJsonpMapper());

        ElasticsearchClient esClient = new ElasticsearchClient(transport);

        return esClient;
    }
}
