package br.com.apiservicos.apiservicos.services.impl;

import br.com.apiservicos.apiservicos.services.S3AwsService;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
public class S3AwsServiceImpl implements S3AwsService {

    @Value("${aws.s3.bucket.name}")
    private String bucketName;
    @Autowired
    private AmazonS3 s3Client;
    @Override
    public String uploadFile(String base64) {
        File fileObj = converterArquivoParaFile(base64);

        String fileName = generateName(fileObj);
        s3Client.putObject(new PutObjectRequest(bucketName, fileName, fileObj));
        return fileName;
    }

    private String generateName(File file){
        return System.currentTimeMillis() + "." + Objects.requireNonNull(file.getName()).split("\\.")[1];
    }
    private File converterArquivoParaFile(String arquivo) {

        String tempDir = System.getProperty("java.io.tmpdir");

        File file = new File(tempDir + "/" +
                LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() + extractMimeType(arquivo));

        byte[] arquivoPDF;
        String base64File;
        try (FileOutputStream fos = new FileOutputStream(file)) {
            base64File = arquivo.split(",")[1];
            arquivoPDF = Base64.getDecoder().decode(base64File);
            fos.write(arquivoPDF);
        } catch (Exception e) {
            System.out.println(e);
        }
        return file;
    }

    private static String extractMimeType(final String encoded) {
        final Pattern mime = Pattern.compile("^data:([a-zA-Z0-9]+/[a-zA-Z0-9]+).*,.*");
        final Matcher matcher = mime.matcher(encoded);
        if (!matcher.find())
            return "";
        return "." + matcher.group(1).toLowerCase().split("/")[1];
    }
}
