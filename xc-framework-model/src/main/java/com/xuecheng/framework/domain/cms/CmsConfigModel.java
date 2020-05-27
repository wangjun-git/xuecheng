package com.xuecheng.framework.domain.cms;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.Map;

/**
 * Created by admin on 2018/2/6.
 */
@Data
@ToString
public class CmsConfigModel {
    @ApiModelProperty("主键")
    private String key;
    @ApiModelProperty("项目名称")
    private String name;
    @ApiModelProperty("项目URL")
    private String url;
    @ApiModelProperty("项目复杂值")
    private Map mapValue;
    @ApiModelProperty("项目简单值")
    private String value;

}
