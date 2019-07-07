package com.deyun.sso.ctrl;

import com.deyun.common.annotation.ParaNotNull;
import com.deyun.common.dto.Result;
import com.deyun.sso.pojo.AppSystemFunc;
import com.deyun.sso.service.AppSystemFuncService;
import com.deyun.user.util.UserAgent;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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
    public Result queryForPage(HttpServletRequest request,@PathVariable int pageNum, @PathVariable int pageSize, AppSystemFunc appSystemFunc){
        String orderBy = request.getParameter("orderBy");
        if (StringUtils.isEmpty(orderBy)){
            orderBy = "sort";
        }
        PageInfo<AppSystemFunc> list = appSystemFuncService.queryFroPage(pageNum,pageSize,orderBy,appSystemFunc);
        return new Result(list);
    }

    @ApiOperation(value="系统功能分页查询", notes="系统功能分页查询")
    @ParaNotNull(ParaName = {"pageNum","pageSize"})
    @GetMapping("/{funcGroupId}")
    public Result queryForFuncGroup(HttpServletRequest request,@PathVariable String funcGroupId){
        List<AppSystemFunc> list = appSystemFuncService.queryForFuncGroup(funcGroupId);
        return new Result(list);
    }

    @ApiOperation(value="添加系统功信息", notes="添加系统功信息")
    @ParaNotNull(ParaName = {"systemCode","funcCode","funcName"})
    @PostMapping
    public Result addAppSystemFunc(@RequestBody AppSystemFunc appSystemFunc) throws Exception {
        String account = UserAgent.getAccount();
        String name = UserAgent.getUsername();
        appSystemFunc.setCreateAccount(account);
        appSystemFunc.setCreateName(name);
        appSystemFunc.setCreateDate(Instant.now());
        int i = appSystemFuncService.addAppSystemFunc(appSystemFunc);
        Result result = new Result(i);
        return result;
    }

    @ApiOperation(value="更新系统功信息", notes="更新系统功信息")
    @RequestMapping(value = "/{id}" ,method = RequestMethod.PUT)
    public Result updateAppSystemFunc(@PathVariable("id") String id,@RequestBody AppSystemFunc appSystemFunc) throws Exception {
        String account = UserAgent.getAccount();
        Map map = new HashMap();
        map.put("id", id);
        appSystemFunc.setModifyAccount(account);
        appSystemFunc.setModifyDate(Instant.now());
        int i = appSystemFuncService.updateAppSystemFunc(appSystemFunc,map);
        Result result = new Result(i);
        return result;
    }

    @ApiOperation(value="删除系统功信息", notes="删除系统功信息")
    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    public Result deleteAppSystemFunc(@PathVariable("id") String id){
        String[] strs = id.split(",");
        List<String> list= Arrays.asList(strs);
        int i = appSystemFuncService.deleteAppSystemFunc(list);
        Result result = new Result(i);
        return result;
    }


}
