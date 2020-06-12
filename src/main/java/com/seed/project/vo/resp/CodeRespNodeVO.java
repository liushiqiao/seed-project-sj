package com.seed.project.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class CodeRespNodeVO {

    @ApiModelProperty(value = "编码id")
    private Integer id;

    @ApiModelProperty(value = "编码名称")
    private String title;

    @ApiModelProperty("是否展开 默认false")
    private boolean spread=false;

    @ApiModelProperty(value = "子集叶子节点")
    private List<?> children;

    @ApiModelProperty("父节点")
    private String pid;
}
