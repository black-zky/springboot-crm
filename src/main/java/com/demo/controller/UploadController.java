package com.demo.controller;

import com.demo.utils.DateUtil;
import com.demo.utils.ResultDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;

@RestController
public class UploadController {
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    @PostMapping("/uploadImage.do")
    public ResultDto uploadImage(@RequestParam("headImage") MultipartFile headImage, HttpServletRequest request){
        //找到到服务器上的目录
        String uploadDir=request.getServletContext().getRealPath("/static/upload");
        //获取文件的后缀
        String fileSuffix=headImage.getOriginalFilename().substring(headImage.getOriginalFilename().lastIndexOf(".")+1);
        logger.info("后缀名:"+fileSuffix);
        //构建时间戳
        String fileName= "img_"+ DateUtil.timeStamp(new Date())+"."+fileSuffix;
        String imageFile=uploadDir+ File.separator+fileName;
        logger.info("文件的路径:"+imageFile);
        try {
            headImage.transferTo(new File(fileName));
            ResultDto rto= new ResultDto();
            rto.setCode(1002);
            rto.setMsg("上传文件成功");
            rto.setData(fileName);
            return rto;
        } catch (IOException e) {
            e.printStackTrace();
            return ResultDto.UPLOAD_FILE_FAIL;
        }
    }
}
