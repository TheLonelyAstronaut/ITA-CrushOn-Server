package com.itechart.crushon.integrations.s3.impl

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.Grantee
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.PutObjectRequest
import com.itechart.crushon.integrations.s3.S3Provider
import com.itechart.crushon.utils.toInputStream
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.codec.multipart.FilePart
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.util.*
import javax.annotation.PostConstruct

@Component
class S3ProviderImpl(
    @Value("\${amazon.s3.access-key}")
    private var access: String,
    @Value("\${amazon.s3.secret-key}")
    private var secret: String,
    @Value("\${amazon.s3.bucket-name}")
    private var bucketName: String,
    @Value("\${amazon.s3.region}")
    private var region: String,
): S3Provider {
    lateinit var s3client: AmazonS3

    @PostConstruct
    private fun configure() {
        val credentials = BasicAWSCredentials(access, secret)

        s3client = AmazonS3ClientBuilder
            .standard()
            .withCredentials(AWSStaticCredentialsProvider(credentials))
            .withRegion(region)
            .build()
    }

    override fun upload(data: FilePart): String {
        val key = "${Date().time}_${data.filename()}"

        s3client.putObject(
            PutObjectRequest(
                bucketName,
                key,
                data.content().toInputStream(),
                ObjectMetadata()
            ).withCannedAcl(CannedAccessControlList.PublicRead)
        )

        return s3client.getUrl(bucketName, key).toString()
    }
}
