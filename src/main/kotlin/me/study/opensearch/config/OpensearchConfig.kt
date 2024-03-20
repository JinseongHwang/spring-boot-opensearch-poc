package me.study.opensearch.config

import org.apache.http.HttpHost
import org.apache.http.auth.AuthScope
import org.apache.http.auth.UsernamePasswordCredentials
import org.apache.http.impl.client.BasicCredentialsProvider
import org.opensearch.client.RestClient
import org.opensearch.client.RestHighLevelClient
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

    @Bean
    fun opensearchClient(): RestHighLevelClient {
        val basicCredentialsProvider = BasicCredentialsProvider()
        basicCredentialsProvider.setCredentials(
            AuthScope.ANY,
            UsernamePasswordCredentials(property.username, property.password)
        )

        return RestHighLevelClient(
            RestClient.builder(
                HttpHost.create(property.host)
            ).setHttpClientConfigCallback {
                it.setDefaultCredentialsProvider(basicCredentialsProvider)
            }
        )
    }
}