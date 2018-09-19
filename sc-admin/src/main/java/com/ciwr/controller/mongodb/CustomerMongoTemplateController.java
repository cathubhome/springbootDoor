package com.ciwr.controller.mongodb;

import com.ciwr.global.common.utils.G;
import com.ciwr.modle.Customer;
import com.ciwr.service.CustomerService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created with IDEA
 * Author:catHome
 * Description: 使用mongodb template操作mongodb
 * Time:Create on 2018/9/11 16:44
 */
@RestController
@RequestMapping("consumer")
@Api(description = "客户api")
public class CustomerMongoTemplateController {

    @Autowired
    @Qualifier(value = "CustomerServiceMongoTemplateService")
    private CustomerService customerService;

    @ApiOperation(value = "新添客户",response = Customer.class)
    @PostMapping("put")
    public G put(@RequestBody Customer customer) {
        customer.setId(null);
        customerService.save(customer);
        return G.ok();
    }

    @GetMapping("delete")
    @ApiOperation(value = "根据主键id删除客户")
    public G deleteById(@ApiParam(value = "id",required = true) String id){
        customerService.deleteById(id);
        return G.ok();
    }

    @GetMapping("list")
    @ApiOperation(value = "查询客户列表")
    public List<Customer> list() {
        List<Customer> customerList = customerService.queryAll();
        return customerList;
    }

    @GetMapping("get")
    @ApiOperation(value = "根据d查询客户")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "id",name = "客户id",required = true,paramType = "long")
    })
    public Customer findCustomById(String id) {
        Customer customer = customerService.findById(id);
        return customer;
    }

    @GetMapping("page")
    public Page<Customer> queryAllByPage(@RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "rows", defaultValue = "30") int rows) {
        Page<Customer> customerPage = customerService.queryAllByPage(page, rows);
        return customerPage;
    }

    @GetMapping("count")
    public long count() {
        long count = customerService.count();
        return count;
    }

    @GetMapping("queryByFirstName")
    public Customer queryByFirstName(String firstName) {
        Customer customer = customerService.queryByFirstName(firstName);
        return customer;
    }

    @GetMapping("queryByFirstNameLike")
    public List<Customer> queryByFirstNameLike(String firstName) {
        List<Customer> customerList = customerService.queryByFirstNameLike(firstName);
        return customerList;
    }

    @GetMapping("queryByFirstNameLikeAndPage")
    @ApiOperation(value = "分页模糊查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "firstName",value = "客户名称",paramType = "query",required = true,allowEmptyValue = true,dataType = "string"),
            @ApiImplicitParam(name = "page",value= "开始页",paramType = "query",dataType = "long",defaultValue = "1"),
            @ApiImplicitParam(name = "rows",value = "每页记录数",paramType = "query",dataType = "long",defaultValue = "2")
    })
    public Page<Customer>  queryByFirstNameLikeAndPage(@RequestParam(value = "firstName") String firstName,@RequestParam(defaultValue = "1") int page,@RequestParam(defaultValue = "2") int rows) {
        Page<Customer> customList = customerService.findByFirstNameLike(firstName, page, rows);
        return customList;
    }



}
