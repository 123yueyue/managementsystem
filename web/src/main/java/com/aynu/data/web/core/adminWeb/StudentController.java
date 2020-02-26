package com.aynu.data.web.core.adminWeb;


import com.aynu.data.web.core.adminIService.IStudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.aynu.data.common.bean.ResponseEntity;

/**
 * @Auther: zhangyue
 * @Date: 2020/2/24
 * @Description:
 */
@Api(value = "/data/student",description = "学生管理接口")
@RestController
@RequestMapping("/data/student")
public class StudentController {

    @Autowired
    private IStudentService iStudentService;


    @ApiOperation(value = "/login", notes = "登录接口")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "id",value = "姓名")
    )
    @GetMapping("/login")
    public ResponseEntity test( ){
        ResponseEntity responseEntity = new ResponseEntity();
        String a = "1";
        String b = "1";
        Boolean c =  a == b;
        responseEntity.setResponseObject(c);
        return responseEntity;
        //return "yueyue";
        //return iStudentService.getName();
    }

}
