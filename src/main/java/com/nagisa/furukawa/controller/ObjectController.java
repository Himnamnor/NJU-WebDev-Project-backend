package com.nagisa.furukawa.controller;

import com.nagisa.furukawa.Util.OssUtil;
import com.nagisa.furukawa.VO.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/api/object")
public class ObjectController {

    @Autowired
    OssUtil ossUtil;

    @PostMapping
    public Response<String> upload(@RequestParam("file") MultipartFile file){
        InputStream inputStream;
        String url;
        try{
            inputStream=file.getInputStream();
            url=ossUtil.upload(file.getName(), inputStream);
        } catch (IOException e) {
            return Response.buildFailure("上传失败","InputStream或Oss上传问题",500);
        }
        return Response.buildSuccess(url);
    }

    @DeleteMapping
    public Response<Boolean> delete(@RequestBody String url){
        try{
            ossUtil.delete(url);
        } catch (RuntimeException e) {
            return Response.buildFailure(false,"删除失败，内部问题或url错误",500);
        }
        return Response.buildSuccess(true);
    }

}
