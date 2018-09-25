package com.ciwr.service.solr;

import com.ciwr.global.constants.Constants;
import com.ciwr.modle.Customer;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * Created with IDEA
 * Author:catHome
 * Description:
 * Time:Create on 2018/9/20 11:15
 */
@Service
public class CustomerSolrServiceImpl implements CustomerSolrService {

    @Autowired
    private SolrClient solrClient;

    @Override
    public void add(Customer customer) throws IOException, SolrServerException {
        SolrInputDocument solrInputDocument = new SolrInputDocument();
        solrInputDocument.addField("customer_firstname",customer.getFirstName());
        solrInputDocument.addField("customer_lastname",customer.getLastName());
        solrClient.add(Constants.SOLR_COLLECION_NAME, solrInputDocument);
        solrClient.commit(Constants.SOLR_COLLECION_NAME);
    }

    @Override
    public void addAll(List<Customer> customerList) {

    }
}
