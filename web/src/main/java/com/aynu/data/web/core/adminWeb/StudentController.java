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
 * @Description:学生管理接口
 */
@Api(value = "/data/student",description = "学生管理接口")
@RestController
@RequestMapping("/data/student")
public class StudentController extends BaseController {

    @Autowired
    private IStudentService iStudentService;


    @ApiOperation(value = "", notes = "增加学生")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "id",value = "姓名")
    )
    @PostMapping
    public ResponseEntity add(@RequestBody GenericBean genericBean){
        ResponseEntity responseEntity = new ResponseEntity();

        return responseEntity;
    }

    @ApiOperation(value = "", notes = "查询学生列表")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "id",value = "姓名")
    )
    @GetMapping
    public ResponseEntity get(){
        ResponseEntity responseEntity = new ResponseEntity();
        GenericBean genericBean = this.getPageData();

        return responseEntity;
    }

    @ApiOperation(value = "", notes = "修改学生信息接口")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "id",value = "姓名")
    )
    @PutMapping
    public ResponseEntity update(){
        ResponseEntity responseEntity = new ResponseEntity();
        GenericBean genericBean = this.getPageData();

        return responseEntity;
    }

    @ApiOperation(value = "", notes = "删除学生接口")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "id",value = "姓名")
    )
    @DeleteMapping
    public ResponseEntity delete(){
        ResponseEntity responseEntity = new ResponseEntity();
        GenericBean genericBean = this.getPageData();

        return responseEntity;
    }


}
