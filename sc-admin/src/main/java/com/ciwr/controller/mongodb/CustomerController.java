package com.ciwr.controller.mongodb;

import com.ciwr.global.common.utils.G;
import com.ciwr.modle.Customer;
import com.ciwr.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created with IDEA
 * Author:catHome
 * Description:
 * Time:Create on 2018/9/11 16:44
 */
@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("put")
    public G put(Customer customer) {
        customerService.save(customer);
        return G.ok();
    }

    @GetMapping("delete")
    public G deleteById(String id){
        customerService.deleteById(id);
        return G.ok();
    }

    @GetMapping("list")
    public List<Customer> list() {
        List<Customer> customerList = customerService.queryAll();
        return customerList;
    }

    @GetMapping("get")
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
    public Page<Customer>  queryByFirstNameLikeAndPage(String firstName,@RequestParam(defaultValue = "1") int page,@RequestParam(defaultValue = "2") int rows) {
        Page<Customer> customList = customerService.findByFirstNameLike(firstName, page, rows);
        return customList;
    }



}
