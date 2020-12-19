package com.niu.springbootuploadfile.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @description: 上传文件控制器
 * @author: nxq email: niuxiangqian163@163.com
 * @createDate: 2020/12/19 4:09 下午
 * @updateUser: nxq email: niuxiangqian163@163.com
 * @updateDate: 2020/12/19 4:09 下午
 * @updateRemark:
 * @version: 1.0
 **/
@RestController
public class UpLoadController {

    @PostMapping("/upload")
    public Object upload(@RequestParam("file")MultipartFile file){
        return saveFile(file);
    }
    @PostMapping("/multiUpload")
    public Object multiUpload(@RequestParam("file")MultipartFile[] files){
        System.out.println("文件的个数:"+files.length);
        for (MultipartFile f : files){
            saveFile(f);
        }
        return "ok";
    }

    private Object saveFile(MultipartFile file){
        if (file.isEmpty()){
            return "未选择文件";
        }
        String filename = file.getOriginalFilename(); //获取上传文件原来的名称
        String filePath = "/Users/laoniu/temp/";
        File temp = new File(filePath);
        if (!temp.exists()){
            temp.mkdirs();
        }

        File localFile = new File(filePath+filename);
        try {
            file.transferTo(localFile); //把上传的文件保存至本地
            System.out.println(file.getOriginalFilename()+" 上传成功");
        }catch (IOException e){
            e.printStackTrace();
            return "上传失败";
        }


        return "ok";
    }
}
