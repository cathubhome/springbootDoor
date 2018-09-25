package com.ciwr.service.solr;

import com.ciwr.modle.Customer;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;
import java.util.List;

/**
 * Created with IDEA
 * Author:catHome
 * Description:
 * Time:Create on 2018/9/20 11:14
 */
public interface CustomerSolrService {

    void add(Customer customer) throws IOException, SolrServerException;

    void addAll(List<Customer> customerList);
}
