package com.aynu.data.web.core.adminWeb;


import com.aynu.data.common.bean.BaseController;
import com.aynu.data.common.bean.GenericBean;
import com.aynu.data.web.core.adminIService.IStudentService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.aynu.data.common.bean.ResponseEntity;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Auther: zhangyue
 * @Date: 2020/2/24
 * @Description:
 */
@Api(value = "/data/student",description = "学生管理接口")
@RestController
@RequestMapping("/data/student")
public class StudentController extends BaseController {

    @Autowired
    private IStudentService iStudentService;


    @ApiOperation(value = "/login", notes = "登录接口")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "id",value = "姓名")
    )
    @GetMapping("/login")
    public ResponseEntity test(){
        ResponseEntity responseEntity = new ResponseEntity();
        GenericBean genericBean = this.getPageData();
        PageInfo pageInfo = iStudentService.getName();

        responseEntity.setResponsePageInfo(pageInfo);
        return responseEntity;
        //return "yueyue";
        //return iStudentService.getName();
    }

}
