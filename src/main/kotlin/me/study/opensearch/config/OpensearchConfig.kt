package me.study.opensearch.config

import org.apache.http.HttpHost
import org.apache.http.auth.AuthScope
import org.apache.http.auth.UsernamePasswordCredentials
import org.apache.http.impl.client.BasicCredentialsProvider
import org.opensearch.client.RestClient
import org.opensearch.client.json.jackson.JacksonJsonpMapper
import org.opensearch.client.opensearch.OpenSearchClient
import org.opensearch.client.transport.rest_client.RestClientTransport
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories


@Configuration
@EnableConfigurationProperties(OpensearchProperty::class)
@EnableElasticsearchRepositories
class OpensearchConfig(
    private val property: OpensearchProperty
) {

    // ref: https://opensearch.org/docs/latest/clients/java/
    @Bean
    fun opensearchClient(): OpenSearchClient {
        val credentialsProvider = BasicCredentialsProvider()
        credentialsProvider.setCredentials(
            AuthScope.ANY,
            UsernamePasswordCredentials(property.username, property.password)
        )

        val restClient = RestClient.builder(
            HttpHost("localhost", 9200, "https")
        ).setHttpClientConfigCallback {
            it.setDefaultCredentialsProvider(credentialsProvider)
        }.build()

        val transport = RestClientTransport(restClient, JacksonJsonpMapper())
        return OpenSearchClient(transport)
    }
}