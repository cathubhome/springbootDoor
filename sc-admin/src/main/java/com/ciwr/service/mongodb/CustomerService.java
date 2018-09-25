package com.ciwr.service.mongodb;

import com.ciwr.modle.Customer;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created with IDEA
 * Author:catHome
 * Description: 客户服务接口
 * Time:Create on 2018/9/11 16:40
 */
public interface CustomerService {


    /**
     * 查询所有客户信息
     * @return
     */
    List<Customer> queryAll();

    /**
     * 保存或修改用户
     * @param customer
     */
    void save(Customer customer);

    /**
     * 根据id删除客户
     * @param id
     */
    void deleteById(String id);

    /**
     * 根据id查询客户信息
     * @param id
     * @return
     */
    Customer findById(String id);

    /**
     * 查询所有的客户信息并分页
     * @param page
     * @param rows
     * @return
     */
    Page<Customer> queryAllByPage(int page, int rows);

    /**
     * 查询所有的客户数量
     * @return
     */
    long count();

    /**
     * 根据客户的firstName属性查询客户信息
     * @param firstName
     * @return
     */
    Customer queryByFirstName(String firstName);

    /**
     * 根据客户的firstName属性模糊查询客户信息
     * @param firstName
     * @return
     */
    List<Customer> queryByFirstNameLike(String firstName);

    /**
     * 根据客户的firstName属性模糊查询客户信息并分页
     * @param firstName
     * @param page
     * @param rows
     * @return
     */
    Page<Customer> findByFirstNameLike(String firstName, int page, int rows);

}
