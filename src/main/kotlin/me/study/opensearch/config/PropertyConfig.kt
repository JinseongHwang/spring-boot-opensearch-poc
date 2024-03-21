package me.study.opensearch.config

import me.study.opensearch.config.properties.OpensearchProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(OpensearchProperties::class)
class PropertyConfig