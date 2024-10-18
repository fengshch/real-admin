package cc.realtec.real.auth.server.service;

import java.io.InputStream;

public interface MinioService {

    String uploadFile(String objectName, InputStream inputStream, String contentType) throws Exception;

    InputStream getFile(String objectName) throws Exception;
}
