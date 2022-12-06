package br.com.apiservicos.apiservicos.services;

import java.io.IOException;

public interface S3AwsService {

    String uploadFile(String base64) throws IOException;
}
