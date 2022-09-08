package com.lht.controller;

import com.lht.pojo.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

/**
 * 公共的控制器
 * @Author lihetao
 * @Date 2022/9/8 11:29
 */
@RestController
@RequestMapping("/common")
@Slf4j
public class CommonController {

    @Value("${21file.path}")
    private String filePath;

    /**
     * 文件上传
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) {

        //原文件名
        String originalFilename = file.getOriginalFilename();

        //图片前缀名
        String prefix = UUID.randomUUID().toString();

        //图片后缀名
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));

        //新文件名
        String fileName = prefix + suffix;

        //新建一个文件夹对象
        File fileDir = new File(filePath);

        //如何文件夹不存在，则创建多级目录
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }

        try {
            //将临时文件转存在指定位置
            file.transferTo(new File(filePath + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Result.success(fileName);
    }

    /**
     * 文件下载（查看）
     * @param name
     * @param rsp
     */
    @GetMapping("/download")
    public void download(String name, HttpServletResponse rsp) {

        try {
            //通过输入流读取文件的具体内容
            FileInputStream fileInputStream = new FileInputStream(new File(filePath + name));

            //再通过输出流写到浏览器，这样在浏览器端就可以显示图片了
            ServletOutputStream outputStream = rsp.getOutputStream();

            //设置浏览器的响应类型
            rsp.setContentType("image/jpg");

            int len = 0;
            //定义字节数组
            byte[] bytes = new byte[1024];

            //len不等于-1时一直读取
            while ((len = fileInputStream.read(bytes)) != -1) {

                //从0开始直到最后，写入数组字符数组，
                outputStream.write(bytes,0, len);

                //刷新
                outputStream.flush();
            }

            //流属于资源需要关闭
            outputStream.close();
            fileInputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
