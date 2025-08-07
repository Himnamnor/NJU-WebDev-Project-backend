package com.nagisa.furukawa.Util;


import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.PutObjectRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Date;

@Component
@Getter
@Setter
@NoArgsConstructor
@ConfigurationProperties("aliyun-oss")
public class OssUtil {

    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;

    public String upload(String objectName, InputStream inputStream){
        OSS ossClient=new OSSClientBuilder().build(endpoint,accessKeyId,accessKeySecret);
        PutObjectRequest putObjectRequest=new PutObjectRequest(bucketName,objectName,inputStream);
        try{
            ossClient.putObject(putObjectRequest);
        } catch (OSSException e) {
            throw new RuntimeException(e);
        }finally {
            if(ossClient!=null){
                ossClient.shutdown();
            }
        }
        return ossClient.generatePresignedUrl(bucketName,objectName,new Date()).toString().split("\\?Expires")[0];
    }

    public Boolean delete(String url){
        OSS ossClient=new OSSClientBuilder().build(endpoint,accessKeyId,accessKeySecret);
        String objectName=url.split("\\.")[1];
        try{
            ossClient.deleteObject(bucketName,objectName);
        } catch (OSSException e) {
            throw new RuntimeException(e);
        }finally {
            if(ossClient!=null){
                ossClient.shutdown();
            }
        }
        return true;
    }

}
