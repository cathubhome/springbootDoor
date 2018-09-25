package com.ciwr.service.mongodb;

import com.ciwr.global.constants.Constants;
import com.ciwr.modle.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IDEA
 * Author:catHome
 * Description: 注入MongoTemplate操作Mongodb
 * Time:Create on 2018/9/19 13:21
 */
@Service(value = "CustomerServiceMongoTemplateService")
public class CustomerServiceMongoTemplateImpl implements CustomerService {

    @Autowired(required = true)
    private MongoTemplate mongoTemplate;

    @Override
    public List<Customer> queryAll() {
        List<Customer> customerList = mongoTemplate.findAll(Customer.class, Constants.CUSTEOMER_COLLECTION_NAME);
        return customerList;
    }

    @Override
    public void save(Customer customer) {
        mongoTemplate.save(customer, Constants.CUSTEOMER_COLLECTION_NAME);
    }

    @Override
    public void deleteById(String id) {
        Query query = Query.query(Criteria.where("id").is(id));
        mongoTemplate.remove(query, Constants.CUSTEOMER_COLLECTION_NAME);
    }

    @Override
    public Customer findById(String id) {
        Query query = Query.query(Criteria.where("id").is(id));
        List<Customer> customerList = mongoTemplate.find(query, Customer.class, Constants.CUSTEOMER_COLLECTION_NAME);
        return customerList == null || customerList.isEmpty() ? null : customerList.get(0);
    }

    @Override
    public Page<Customer> queryAllByPage(int page, int rows) {
        Query query = new Query();
        PageRequest pageRequest = PageRequest.of(page - 1, rows);
        query.with(pageRequest);
        List<Customer> customerList = mongoTemplate.find(query, Customer.class, Constants.CUSTEOMER_COLLECTION_NAME);
        long totalCount = count();
        Page<Customer> customers = new PageImpl<>(customerList,pageRequest,totalCount);
        return customers ;
    }

    @Override
    public long count() {
        long count = mongoTemplate.count(new Query(), Customer.class, Constants.CUSTEOMER_COLLECTION_NAME);
        return count;
    }

    @Override
    public Customer queryByFirstName(String firstName) {
        Query query = Query.query(Criteria.where("firstName").is(firstName));
        List<Customer> customerList = mongoTemplate.find(query, Customer.class, Constants.CUSTEOMER_COLLECTION_NAME);
        return customerList == null || customerList.isEmpty() ? null : customerList.get(0);
    }

    @Override
    public List<Customer> queryByFirstNameLike(String firstName) {
        Query query = new Query();
        Criteria criteria = Criteria.where("firstName").regex(".*?" + firstName + ".*");
        query.addCriteria(criteria);
        List<Customer> customerList = mongoTemplate.find(query, Customer.class, Constants.CUSTEOMER_COLLECTION_NAME);
        return customerList;
    }

    @Override
    public Page<Customer> findByFirstNameLike(String firstName, int page, int rows) {
        //模糊匹配
        Query query  = Query.query(Criteria.where("firstName").regex(".*?\\"+firstName+".*"));
        long totalCount = mongoTemplate.count(query, Customer.class, Constants.CUSTEOMER_COLLECTION_NAME);
        PageRequest pageRequest = PageRequest.of(page - 1, rows);
        Sort sort = new Sort(Sort.Direction.DESC,"lastName");
        //返回的field域
//        query.fields().exclude("lastName");
        //指定排序与分页
        query.with(sort).with(pageRequest);
        List<Customer> customerList = mongoTemplate.find(query, Customer.class, Constants.CUSTEOMER_COLLECTION_NAME);
        Page<Customer> customerPage = new PageImpl<>(customerList,pageRequest,totalCount);
        return customerPage;
    }
}
