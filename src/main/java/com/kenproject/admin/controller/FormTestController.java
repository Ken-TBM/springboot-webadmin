package com.kenproject.admin.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * 文件测试
 */
@Slf4j
@Controller
public class FormTestController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @ResponseBody
    @GetMapping("/sql")
    public String queryFormDb(){
        Long aLong = jdbcTemplate.queryForObject("select count(*) from user_table", Long.class);
        return  aLong.toString();
    }

    @GetMapping("/form_layouts")
    public String form_layouts(){
        return "form/form_layouts";
    }

    /**
     * multipartFile 自动封装上传过来的文件
     * @param email
     * @param userName
     * @param headerImg
     * @param photos
     * @return
     */
    @PostMapping("/upload")
    public String upload(@RequestParam("email") String email,
                         @RequestParam("username") String userName,
                         @RequestPart("headerImg") MultipartFile headerImg,
                         @RequestPart("photos") MultipartFile[] photos) throws IOException {
        log.info("上传的信息 email={},username={},headerImg={},photos={}",
                email,userName,headerImg.getSize(),photos.length);

        if (!headerImg.isEmpty()){
            String originalFilename = headerImg.getOriginalFilename();
                headerImg.transferTo(new File("D:\\AA\\"+originalFilename));
        }
        if (photos.length>0){
            for (MultipartFile photo : photos) {
                if (!photo.isEmpty()){
                    String originalFilename = photo.getOriginalFilename();
                    photo.transferTo(new File("D:\\AA\\" +originalFilename));
                }
            }
        }
        return "main";
    }
}
