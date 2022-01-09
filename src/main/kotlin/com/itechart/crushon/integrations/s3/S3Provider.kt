package com.itechart.crushon.integrations.s3

import org.springframework.http.codec.multipart.FilePart
import org.springframework.web.multipart.MultipartFile

interface S3Provider {
    fun upload(data: FilePart): String
}
