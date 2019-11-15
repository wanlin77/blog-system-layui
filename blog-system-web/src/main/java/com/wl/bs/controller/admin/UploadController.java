package com.wl.bs.controller.admin;

import com.wl.bs.common.constants.HttpStatusConstants;
import com.wl.bs.common.constants.UploadConstants;
import com.wl.bs.common.dto.Result;
import com.wl.bs.common.util.ResultGenerator;
import com.wl.bs.util.BlogUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @description: 上传图片controller
 * @author: wanlin
 * @version: 1.0
 * @createtime: 2019/10/6 16:03
 */
@Controller
@RequestMapping("/admin")
public class UploadController {

    /**
     * @description: 用户头像上传
     * @param request
     * @param file
     * @return: com.wl.bs.common.dto.Result
     * @author: wanlin
     * @createtime: 2019/10/6 22:30
     */
    @PostMapping("/upload/authorImg")
    @ResponseBody
    public Result upload(HttpServletRequest request, @RequestParam("file") MultipartFile file) throws URISyntaxException {
        String suffixName = getSuffixName(file);
        String newFileName = getNewFileName(suffixName);
        File fileDirectory = new File(UploadConstants.UPLOAD_AUTHOR_IMG);
        // 创建文件
        File destFile = new File(UploadConstants.UPLOAD_AUTHOR_IMG + newFileName);
        try {
            if (!fileDirectory.exists()) {
                if (!fileDirectory.mkdirs()) {
                    throw new IOException("文件夹创建失败,路径为：" + fileDirectory);
                }
            }
            file.transferTo(destFile);
            Result resultSuccess = ResultGenerator.getResultByHttp(HttpStatusConstants.OK);
            resultSuccess.setData(BlogUtils.getHost(new URI(request.getRequestURI() + ""))
                    + UploadConstants.SQL_AUTHOR_IMG + newFileName);
            return resultSuccess;
        } catch (IOException e) {
            e.printStackTrace();
            return ResultGenerator.getResultByHttp(HttpStatusConstants.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * @description: 获取图片后缀
     * @param file
     * @return: java.lang.String
     * @author: wanlin
     * @createtime: 2019/10/6 22:27
     */
    private String getSuffixName(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        return fileName.substring(fileName.lastIndexOf("."));
    }

    /**
     * @description: 生成文件名称
     * @param suffixName
     * @return: java.lang.String
     * @author: wanlin
     * @createtime: 2019/10/6 22:30
     */
    private String getNewFileName(String suffixName){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        int random = new Random().nextInt(100);
        StringBuilder tempName = new StringBuilder();
        tempName.append(sdf.format(new Date())).append(random).append(suffixName);
        return tempName.toString();
    }

}
