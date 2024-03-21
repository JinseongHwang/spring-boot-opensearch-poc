package me.study.opensearch

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchRestClientAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [
    ElasticsearchDataAutoConfiguration::class,
    ElasticsearchRestClientAutoConfiguration::class,
])
class SpringBootOpensearchPocApplication

fun main(args: Array<String>) {
    runApplication<SpringBootOpensearchPocApplication>(*args)
}
