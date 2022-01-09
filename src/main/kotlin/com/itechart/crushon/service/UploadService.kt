package com.itechart.crushon.service

import org.springframework.http.codec.multipart.FilePart

interface UploadService {
    fun upload(data: FilePart): Long
}
