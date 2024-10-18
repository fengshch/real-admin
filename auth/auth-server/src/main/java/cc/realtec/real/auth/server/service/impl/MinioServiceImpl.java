package cc.realtec.real.auth.server.service.impl;

import cc.realtec.real.auth.server.service.MinioService;
import io.minio.GetObjectArgs;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.http.Method;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class MinioServiceImpl implements MinioService {

    private final MinioClient minioClient;

    @Value("${minio.default-bucket}")
    private String defaultBucket;

    @Override
    public String uploadFile(String objectName, InputStream inputStream, String contentType) throws Exception {
        minioClient.putObject(
                PutObjectArgs.builder().bucket(defaultBucket).object(objectName).stream(
                                inputStream, -1, 10485760)
                        .contentType(contentType)
                        .build());

        return minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                        .method(Method.GET)
                        .bucket(defaultBucket)
                        .object(objectName)
                        .expiry(24 * 60 * 60) // URL valid for 24 hours
                        .build());
    }

    @Override
    public InputStream getFile(String objectName) throws Exception {
        return minioClient.getObject(GetObjectArgs.builder()
                .bucket(defaultBucket)
                .object(objectName).build());
    }
}
