package ${basePackage}.controller;
import ${basePackage}.entity.${modelNameUpperCamel};
import ${basePackage}.service.${modelNameUpperCamel}Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.seed.project.aop.annotation.LogAnnotation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.seed.project.utils.DataResult;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by ${author} on ${date}.
*/
@RestController
@RequestMapping("${baseRequestMapping}")
@Api(tags = "")
public class ${modelNameUpperCamel}Controller {
    @Resource
    private ${modelNameUpperCamel}Service ${modelNameLowerCamel}Service;

    @ApiOperation(value = "")
    @LogAnnotation(title = "",action = "")
    @RequiresPermissions("${modelNameLowerCamel}:add")
    @PostMapping("/add")
    public DataResult add(${modelNameUpperCamel} ${modelNameLowerCamel}) {
        ${modelNameLowerCamel}Service.save(${modelNameLowerCamel});
        return DataResult.success();
    }

    @ApiOperation(value = "")
    @LogAnnotation(title = "",action = "")
    @RequiresPermissions("${modelNameLowerCamel}:delete")
    @PostMapping("/delete")
    public DataResult delete(@RequestParam Integer id) {
        ${modelNameLowerCamel}Service.deleteById(id);
        return DataResult.success();
    }
    @ApiOperation(value = "")
    @LogAnnotation(title = "",action = "")
    @RequiresPermissions("${modelNameLowerCamel}:update")
    @PostMapping("/update")
    public DataResult update(${modelNameUpperCamel} ${modelNameLowerCamel}) {
        ${modelNameLowerCamel}Service.update(${modelNameLowerCamel});
        return DataResult.success();
    }

    @ApiOperation(value = "")
    @LogAnnotation(title = "",action = "")
    @RequiresPermissions("${modelNameLowerCamel}:detail")
    @PostMapping("/detail")
    public DataResult detail(@RequestParam Integer id) {
        ${modelNameUpperCamel} ${modelNameLowerCamel} = ${modelNameLowerCamel}Service.findById(id);
        return DataResult.success(${modelNameLowerCamel});
    }

    @ApiOperation(value = "")
    @LogAnnotation(title = "",action = "")
    @RequiresPermissions("${modelNameLowerCamel}:list")
    @PostMapping("/list")
    public DataResult list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<${modelNameUpperCamel}> list = ${modelNameLowerCamel}Service.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return DataResult.success(pageInfo);
    }
}
