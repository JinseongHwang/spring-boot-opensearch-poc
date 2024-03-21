package me.study.opensearch.controller


import org.opensearch.client.RequestOptions
import org.opensearch.client.RestHighLevelClient
import org.opensearch.client.indices.CreateIndexRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/os")
class OpensearchController(
    val opensearchClient: RestHighLevelClient
) {

    @PostMapping("/index")
    fun createIndex(
        @RequestParam name: String
    ): ResponseEntity<String> {
        if (name.isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Index name must not be blank.")
        }

        val createIndexRequest = CreateIndexRequest(name)
        val indexResponse = opensearchClient.indices().create(createIndexRequest, RequestOptions.DEFAULT)
        println("Index creation response: $indexResponse")

        return ResponseEntity.status(HttpStatus.CREATED).body("Index=`${name}` created successfully.")
    }
}