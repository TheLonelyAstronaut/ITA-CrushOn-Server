package com.itechart.crushon.controller

import com.itechart.crushon.service.UploadService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.reactive.asFlow
import org.springframework.http.codec.multipart.FilePart
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux

@RestController
@RequestMapping("api/v1/upload")
class UploadController(
    private val uploadService: UploadService
) {
    @PostMapping
    fun uploadFile(@RequestPart("file") filePart: FilePart): Long =
        uploadService.upload(filePart)
}
