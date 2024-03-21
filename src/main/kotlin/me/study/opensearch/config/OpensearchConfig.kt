package me.study.opensearch.config

import me.study.opensearch.config.properties.OpensearchProperties
import org.apache.http.HttpHost
import org.apache.http.auth.AuthScope
import org.apache.http.auth.UsernamePasswordCredentials
import org.apache.http.impl.client.BasicCredentialsProvider
import org.opensearch.client.RestClient
import org.opensearch.client.RestHighLevelClient
import org.opensearch.data.client.orhlc.AbstractOpenSearchConfiguration
import org.springframework.context.annotation.Configuration
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories

@Configuration
@EnableElasticsearchRepositories
class OpensearchConfig(
    private val property: OpensearchProperties,
) : AbstractOpenSearchConfiguration() {

    override fun opensearchClient(): RestHighLevelClient {
        val credentials = UsernamePasswordCredentials(property.username, property.password)
        val credentialsProvider = BasicCredentialsProvider()
        credentialsProvider.setCredentials(AuthScope.ANY, credentials)

        val builder = RestClient.builder(
            HttpHost.create(property.host)
        ).setHttpClientConfigCallback {
            it.setDefaultCredentialsProvider(credentialsProvider)
        }

        return RestHighLevelClient(builder)
    }
}
