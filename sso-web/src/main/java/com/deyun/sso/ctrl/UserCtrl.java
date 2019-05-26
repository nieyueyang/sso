package com.deyun.sso.ctrl;

import com.deyun.common.annotation.ParaNotNull;
import com.deyun.common.domain.PageParameter;
import com.deyun.common.dto.Result;
import com.deyun.sso.service.UserService;
import com.deyun.user.dto.AppUser;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;;import java.util.Map;

/**
 * @Author: nieyy
 * @Date: 2019/4/27 19:49
 * @Version 1.0
 * @Description:
 */

@RestController
@RequestMapping("/user")
public class UserCtrl {

    @Autowired
    private UserService userService;

    /**
     * 单条查询
     * @return
     */
    @ApiOperation(value="获取用户列表", notes="获取用户列表")
    @ParaNotNull(ParaName = {"account"})
    @GetMapping(value = "/{account}")
    public AppUser selectByAccount(@PathVariable("account") String account){
        return userService.selectByAccount(account);
    }

    /**
     * 分页查询
     * @return
     */
    @ApiOperation(value="分页查询", notes="分页查询")
    @ParaNotNull(ParaName = {"pageNum","pageSize"})
    @GetMapping(value = "/{pageNum}/{pageSize}")
    public Result selectForPage(HttpServletRequest request,@PathVariable("pageNum") int pageNum,
                                @PathVariable("pageSize") int pageSize){

        String account = request.getParameter("account");
        String name = request.getParameter("name");
        String orderby = request.getParameter("orderby");

        PageParameter pageParameter = new PageParameter();
        pageParameter.setPageNum(pageNum);
        pageParameter.setPageSize(pageSize);
        if (StringUtils.isEmpty(orderby)){
            orderby="create_date";
        }
        pageParameter.setOrderBy(orderby);

        pageParameter.put("account",account );
        pageParameter.put("name",name );

        PageInfo<AppUser> list = userService.selectForPage(pageParameter);
        return new Result(list);
    }
    


}
