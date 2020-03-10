package com.aynu.data.web.core.adminWeb;

import com.aynu.data.common.bean.BaseController;
import com.aynu.data.common.bean.GenericBean;
import com.aynu.data.common.bean.ResponseEntity;
import com.aynu.data.web.core.adminIService.ISchoolService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Auther: zhangyue
 * @Date: 2020/3/10
 * @Description:学校管理接口
 */
@Api(value = "/data/school",description = "学校管理接口")
@RestController
@RequestMapping("/data/school")
public class SchoolController extends BaseController {

    @Autowired
    private ISchoolService iSchoolService;


    @ApiOperation(value = "", notes = "新建学校")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "id",value = "姓名")
    )
    @PostMapping
    public ResponseEntity add(@RequestBody GenericBean genericBean){
        ResponseEntity responseEntity = new ResponseEntity();
        try{
            
        }catch (Exception e){

        }
        return responseEntity;
    }

    @ApiOperation(value = "", notes = "查询学校列表")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "id",value = "姓名")
    )
    @GetMapping
    public ResponseEntity get(){
        ResponseEntity responseEntity = new ResponseEntity();
        GenericBean genericBean = this.getPageData();

        return responseEntity;
    }

    @ApiOperation(value = "", notes = "修改学校信息接口")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "id",value = "姓名")
    )
    @PutMapping
    public ResponseEntity update(){
        ResponseEntity responseEntity = new ResponseEntity();
        GenericBean genericBean = this.getPageData();

        return responseEntity;
    }

    @ApiOperation(value = "", notes = "删除学校接口")
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
