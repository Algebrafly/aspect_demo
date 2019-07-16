package com.algebra.aspect.controller.file;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * @author al
 * @date 2019/7/16 9:35
 * @description
 */
@RestController
@Slf4j
public class FileDownload {

    @GetMapping(value = "/testDownload")
    public void downloadFile(HttpServletRequest request, HttpServletResponse response) {
        String fileName = "20190529_102802315.zip";
        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(new File("E:\\zip\\admin_admin\\" + fileName)));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("success");
    }

    @PostMapping("/downloadDocuments")
    public void downloadDocuments(HttpServletRequest request, HttpServletResponse response){

        BufferedOutputStream out = null;
        FileInputStream in = null;
        try{
            String downloadZipFilePath = "E:\\zip\\admin_admin\\啊啊啊啊.zip";
            log.info("zip包下载路径：{}",downloadZipFilePath);

            in = new FileInputStream(downloadZipFilePath);
            out = new BufferedOutputStream(response.getOutputStream());
            //通知浏览器以附件形式下载
            response.setContentType("application/doc");


            final String userAgent = request.getHeader("USER-AGENT");
            if(StringUtils.contains(userAgent, "MSIE")){
                downloadZipFilePath = URLEncoder.encode(downloadZipFilePath,"UTF-8");
            }else if(StringUtils.contains(userAgent, "Mozilla")){
                downloadZipFilePath = new String(downloadZipFilePath.getBytes(), "UTF-8");
            }else{
                downloadZipFilePath = URLEncoder.encode(downloadZipFilePath,"UTF-8");
            }
//            response.addHeader("Content-Disposition", "attachment;filename=" +downloadZipFilePath);
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(downloadZipFilePath.getBytes("UTF-8"),"ISO-8859-1"));

            byte[] car = new byte[1024];
            int l;
            while((l = in.read(car)) != -1){
                out.write(car, 0,l);
                out.flush();
            }
        } catch(Exception e) {
            e.printStackTrace();
            log.info("下载异常！");
        } finally {
            FileTooUtil.closeQuietly(in);
            FileTooUtil.closeQuietly(out);
        }
    }

}
