package com.ciwr.mapper;

import com.ciwr.modle.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 * Created with IDEA
 * Author:catHome
 * Description: 客户仓库
 * Time:Create on 2018/9/11 16:39
 */
public interface CustomerRepository extends MongoRepository<Customer,String>{

    /**
     * 根据poojo的属性进行查询
     * ongoRepository提供的方法已经不能满足,需要在仓库中定义方法，定义方法名的规则为：find + By + 属性名（首字母大写）
     * @param firstName
     * @return
     */
    Customer findByFirstName(String firstName);

    /**
     * 根据pojo属性模糊查询
     * 模糊查询定义方法名的规则为：find + By + 属性名（首字母大写） + Like
     * @param firstName
     * @return
     */
    List<Customer> findByFirstNameLike(String firstName);


    /**
     * 根据pojo属性模糊查询并分页，@Query注解的fields指定返回的field域
     * @param firstName
     * @param pageable
     * @return
     */
    @Query(fields = "{'firstName':1,'lastName':1}")
    Page<Customer> findByFirstNameLike(String firstName, Pageable pageable);
}
