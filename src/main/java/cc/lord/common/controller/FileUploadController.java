package cc.lord.common.controller;

import cc.lord.common.domain.ResponseBo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Controller
public class FileUploadController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value="/uploadImg",method= RequestMethod.POST)
    @ResponseBody
    private ResponseBo fildUpload(@RequestParam(value="file",required=false) MultipartFile file,
                                  HttpServletRequest request)throws Exception{
        //基本表单

        //获得物理路径webapp所在路径
        String pathRoot = request.getSession().getServletContext().getRealPath("");
        String path="";
        if(!file.isEmpty()){
            //生成uuid作为文件名称

            //获得文件类型（可以判断如果不是图片，禁止上传）
            String contentType=file.getContentType();
            //获得文件后缀名称
            String imageName=contentType.substring(contentType.indexOf("/")+1);
            path=/*"/static/images/"+*/System.currentTimeMillis()+"."+imageName;
            file.transferTo(new File(pathRoot+path));
        }
        System.out.println(path);
        request.setAttribute("imagesPath", path);
        Map result=new HashMap();
        result.put("img",path);
        return ResponseBo.ok(result);
    }
}
