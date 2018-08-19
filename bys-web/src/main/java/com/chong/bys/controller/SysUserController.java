package com.chong.bys.controller;


import com.chong.bys.base.Result;
import com.chong.bys.domain.pojo.SysUser;
import com.chong.bys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lichong
 * @since 2018-08-16
 */
@Controller
@RequestMapping("/sysUser")
public class SysUserController extends BaseController {


    @Autowired
    SysUserService sysUserService;

    @GetMapping("/getAll")
    @ResponseBody
    public Result<List<SysUser>> getAll(){

        Result<List<SysUser>> success = Result.success(sysUserService.selectList(null));
//        int i = 2/0;
        return success;
    }


}

