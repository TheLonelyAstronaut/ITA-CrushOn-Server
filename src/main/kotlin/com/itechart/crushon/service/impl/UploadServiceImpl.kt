package com.itechart.crushon.service.impl

import com.itechart.crushon.integrations.s3.S3Provider
import com.itechart.crushon.model.File
import com.itechart.crushon.repository.FileRepository
import com.itechart.crushon.service.UploadService
import org.springframework.http.codec.multipart.FilePart
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class UploadServiceImpl(
    private val fileRepository: FileRepository,
    private val s3Provider: S3Provider
): UploadService {
    override fun upload(data: FilePart): Long {
        val url = s3Provider.upload(data)
        val file = File(link = url)

        fileRepository.save(file)

        return file.id!!
    }
}
