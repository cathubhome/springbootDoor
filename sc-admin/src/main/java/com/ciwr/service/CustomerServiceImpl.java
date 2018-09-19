package com.ciwr.service;

import com.ciwr.mapper.CustomerRepository;
import com.ciwr.modle.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created with IDEA
 * Author:catHome
 * Description: 客户服务实现类
 * Time:Create on 2018/9/11 16:42
 */
@Service(value = "customerService")
@SuppressWarnings("all")
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> queryAll() {
        return customerRepository.findAll();
    }

    @Override
    public void save(Customer customer){
        customerRepository.insert(customer);
    }

    @Override
    public void deleteById(String id){
        customerRepository.deleteById(id);
    }


    @Override
    public Customer findById(String id){
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        return optionalCustomer.get();
    }

    @Override
    public Page<Customer> queryAllByPage(int page, int rows) {
        PageRequest pageRequest = PageRequest.of(page-1,rows);
        return customerRepository.findAll(pageRequest);
    }

    @Override
    public long count() {
        return customerRepository.count();
    }

    @Override
    public Customer queryByFirstName(String firstName) {
        return customerRepository.findByFirstName(firstName);
    }

    @Override
    public List<Customer> queryByFirstNameLike(String firstName) {
        return customerRepository.findByFirstNameLike(firstName);
    }

    @Override
    public Page<Customer> findByFirstNameLike(String firstName, int page,int rows) {
        PageRequest pageRequest = PageRequest.of(page-1,rows);
        return customerRepository.findByFirstNameLike(firstName,pageRequest);
    }

}
