package com.ciwr.controller.webupload;

import com.ciwr.global.common.utils.WebUploadFileUtil;
import com.ciwr.service.sys.WebUploadService;
import com.ciwr.vo.WebUploadVo;
import org.jsoup.helper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

@SuppressWarnings("all")
@RequestMapping(value = "webupload")
@Controller
public class WebUploadController {

    @Autowired
    private WebUploadService webUploadService;

    @RequestMapping(value="index",method = RequestMethod.GET)
    public String index(){
        return "index";
    }

    @RequestMapping(value="getFormats",method = RequestMethod.GET)
    public Object getFormats(){
        //TODO 限制上传文档格式
        return null;
    }

    @RequestMapping(value = "check", method = RequestMethod.POST)
    @ResponseBody
    public Object check(@ModelAttribute("form-data")WebUploadVo vo, HttpServletRequest request) {
        return webUploadService.check(vo);
    }

    @RequestMapping(value = "upload", method = RequestMethod.POST)
    @ResponseBody
    public Object upload(@ModelAttribute("form-data")WebUploadVo vo,HttpServletRequest request) {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        MultipartFile multipartFile = files.get(0);
        String fileName = multipartFile.getName();
        File f = new File(multipartFile.getOriginalFilename());
        Object response;
        try{
            WebUploadFileUtil.inputStreamToFile(multipartFile.getInputStream(),f);
            if(StringUtil.isBlank(request.getParameter("chunk"))){
                response = webUploadService.unChunkUpload(f,vo);
            }else{
                response = webUploadService.chunkUpload(f,vo);
            }
        }catch(IOException e){
            e.printStackTrace();
            return null;
        } finally {
            File del = new File(f.toURI());
            if(del.exists()){
                f.delete();
            }
        }
        return response;
    }
}