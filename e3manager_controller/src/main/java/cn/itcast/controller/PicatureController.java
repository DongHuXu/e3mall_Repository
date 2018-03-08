package cn.itcast.controller;

import cn.itcast.utils.FastDFSClient;
import cn.itcast.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Controller
public class PicatureController {

    @Value("${image_server_url}")
    private String image_server_url;

    @RequestMapping(value = "/pic/upload",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String uploadPicture(MultipartFile uploadFile) {
        try {
            String originalFilename = uploadFile.getOriginalFilename();
            String etcFileName = originalFilename.substring(originalFilename.lastIndexOf(".")+1);
            FastDFSClient fastDFSClient = new FastDFSClient("classpath:conf/fastDFSClient.conf");
            String url = fastDFSClient.uploadFile(uploadFile.getBytes(), etcFileName);
            String path = image_server_url+url;
            Map map = new HashMap();
            map.put("error",0);
            map.put("url",path);
            return JsonUtils.objectToJson(map);
        } catch (Exception e) {
            e.printStackTrace();
            Map map = new HashMap();
            map.put("error",1);
            map.put("msg","上传失败");
            return  JsonUtils.objectToJson(map);
        }
    }
}
