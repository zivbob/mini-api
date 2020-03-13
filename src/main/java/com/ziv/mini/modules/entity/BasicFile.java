package com.ziv.mini.modules.entity;

import lombok.Data;

/**
 * 文件信息类
 * @author ziv
 * @date 2019-05-24
 */
@Data
public class BasicFile {

    /**
     * 主键
     */
    private Long fileId;

    /**
     * 文件类型
     */
    private String fileType;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 资源路径
     */
    private String resourcePath;
}
