package com.deyun.sso.ctrl;

import com.deyun.common.annotation.ParaNotNull;
import com.deyun.common.dto.Result;
import com.deyun.sso.pojo.AppFuncGroup;
import com.deyun.sso.service.AppFuncGroupService;
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
 * @Date: 2019/6/30 21:28
 * @Version 1.0
 * @Description:
 */
@RestController
@RequestMapping("/funcgroup")
public class FuncGroupCtrl {

    @Autowired
    private AppFuncGroupService appFuncGroupService;


    @ApiOperation(value="系统功能分页查询", notes="系统功能分页查询")
    @ParaNotNull(ParaName = {"pageNum","pageSize"})
    @GetMapping("/{pageNum}/{pageSize}")
    public Result queryForPage(HttpServletRequest request, @PathVariable int pageNum, @PathVariable int pageSize, AppFuncGroup appFuncGroup){
        String orderBy = request.getParameter("orderBy");
        if (StringUtils.isEmpty(orderBy)){
            orderBy = "create_date";
        }
        PageInfo<AppFuncGroup> list = appFuncGroupService.queryFroPage(pageNum,pageSize,orderBy,appFuncGroup);
        return new Result(list);
    }

    @ApiOperation(value="添加系统功能信息", notes="添加系统功能信息")
    @ParaNotNull(ParaName = {"groupCode","groupName"})
    @PostMapping
    public Result addFuncGroup(@RequestBody AppFuncGroup appFuncGroup) throws Exception {
        String account = UserAgent.getAccount();
        String name = UserAgent.getUsername();
        appFuncGroup.setCreateAccount(account);
        appFuncGroup.setCreateName(name);
        appFuncGroup.setCreateDate(Instant.now());
        int i = appFuncGroupService.addFuncGroup(appFuncGroup);
        Result result = new Result(i);
        return result;
    }

    @ApiOperation(value="更新系统功能信息", notes="更新系统功能信息")
    @RequestMapping(value = "/{id}" ,method = RequestMethod.PUT)
    public Result updateFuncGroup(@PathVariable("id") String id,@RequestBody AppFuncGroup appFuncGroup) throws Exception {
        String account = UserAgent.getAccount();
        Map map = new HashMap();
        map.put("id", id);
        appFuncGroup.setModifyAccount(account);
        appFuncGroup.setModifyDate(Instant.now());
        int i = appFuncGroupService.updateFuncGroup(appFuncGroup,map);
        Result result = new Result(i);
        return result;
    }

    @ApiOperation(value="删除系统功信息", notes="删除系统功信息")
    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    public Result deleteFcunGroup(@PathVariable("id") String id){
        String[] strs = id.split(",");
        List<String> list= Arrays.asList(strs);
        int i = appFuncGroupService.deleteAppFuncGroup(list);
        Result result = new Result(i);
        return result;
    }




}
