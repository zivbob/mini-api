package com.ziv.mini.modules.controller.mini;

import com.ziv.mini.common.response.JsonResult;
import com.ziv.mini.modules.entity.BasicFile;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 文件接口控制类
 *
 * @author ziv
 * @date 2020-02-29
 */
@Slf4j
@RestController
@RequestMapping(value = "file")
@Api(tags = {"文件接口"})
public class FileController {

    @Value("${fileSavePath}")
    private String fileSavePath;

    @Value("${resourcePath}")
    private String resourcePath;

    @PostMapping(value = "singleFile", headers="content-type=multipart/form-data")
    @ApiOperation(value = "单个文件上传接口")
    public JsonResult<BasicFile> singleImg(@ApiParam(value = "file", required = true)MultipartFile file) throws IOException {
        BasicFile basicFile = saveFile(file);
        return JsonResult.success(basicFile);
    }

    @PostMapping(value = "multipleFile", headers="content-type=multipart/form-data")
    @ApiOperation(value = "多文件上传接口")
    public JsonResult<List<BasicFile>> singleFile(@ApiParam(value = "file", required = true)MultipartFile[] files) throws IOException {
            List<BasicFile> fileList = new ArrayList<>();
            for (MultipartFile file : files) {
                BasicFile basicFile = saveFile(file);
                fileList.add(basicFile);
            }
        return JsonResult.success(fileList);
    }

    /**
     * 保存文件
     * @param file 文件
     * @return BasicFile
     * @throws IOException
     */
    private BasicFile saveFile(MultipartFile file) throws IOException {
        BasicFile basicFile = new BasicFile();
        // 日期文件夹
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dataPackage = dateFormat.format(new Date());
        // 文件名称
        String fileOriginName = file.getOriginalFilename();
        // 文件类型
        String fileType = fileOriginName.substring(fileOriginName.lastIndexOf(".") + 1);
        // 文件路径
        String filePath = "/"+ dataPackage + "/" + fileType + "/" + System.currentTimeMillis() + "." + fileType;
        // 服务器文件存储路径
        String savePath = fileSavePath + filePath;
        // 文件访问路径
        String readPath = resourcePath + filePath;
        File destFile = new File(savePath);
        // 创建目录
        destFile.getParentFile().mkdirs();
        // 保存文件
        file.transferTo(destFile);
        basicFile.setFileType(fileType);
        basicFile.setFileName(fileOriginName);
        basicFile.setResourcePath(readPath);
        return basicFile;
    }
}
