package me.study.opensearch.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding

@ConfigurationProperties(prefix = "opensearch")
data class OpensearchProperties @ConstructorBinding constructor(
    val region: String,
    val signingServiceName: String,
    val host: String,
    val username: String,
    val password: String
)