package com.seed.project.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: UserPageReqVO
 * TODO:类文件简单描述
 * @Author: 小霍
 * @UpdateUser: 小霍
 * @Version: 0.0.1
 */
@Data
public class CodePageReqVO {

    @ApiModelProperty(value = "当前第几页")
    private Integer pageNum=1;

    @ApiModelProperty(value = "当前页数量")
    private Integer pageSize=10;

    @ApiModelProperty(value = "codeid")
    private Integer codeId;

    @ApiModelProperty(value = "父节点id")
    private String parentId;

    @ApiModelProperty(value = "名称")
    private String codeName;

    @ApiModelProperty(value = "类型")
    private String codeType;

    @ApiModelProperty(value = "内容")
    private String codeValue;

    @ApiModelProperty(value = "账户状态(1.正常 2.锁定 ")
    private Integer isuse;

    @ApiModelProperty(value = "关键字")
    private String keyword;


}
