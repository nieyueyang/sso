package com.deyun.sso.ctrl;

import com.deyun.common.annotation.ParaNotNull;
import com.deyun.common.dto.Result;
import com.deyun.sso.pojo.AppSystemFunc;
import com.deyun.sso.service.AppSystemFuncService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: nieyy
 * @Date: 2019/6/26 23:38
 * @Version 1.0
 * @Description:
 */
@RestController
@RequestMapping("/systemFunc")
public class AppSystemFuncCtrl {

    @Autowired
    private AppSystemFuncService appSystemFuncService;

    @ApiOperation(value="系统功能分页查询", notes="系统功能分页查询")
    @ParaNotNull(ParaName = {"pageNum","pageSize"})
    @GetMapping("/{pageNum}/{pageSize}")
    public Result queryForPage(@PathVariable int pageNum,@PathVariable int pageSize,AppSystemFunc appSystemFunc){

       // int pageNum = (int)map.get("pageNum");
        //int pageSize = (int)map.get("pageSize");
        Map map = new HashMap();
        PageInfo<AppSystemFunc> list = appSystemFuncService.queryFroPage(pageNum,pageSize,map);
        return new Result(list);
    }
}
