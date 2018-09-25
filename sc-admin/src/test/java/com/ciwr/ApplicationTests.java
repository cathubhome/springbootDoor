package com.ciwr;

import com.ciwr.service.hbase.HbaseService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ApplicationTests {

    @Autowired
    private HbaseService hbaseService;

    @Test
    public void test() {

//        Result row = hbaseService.getRow("user", "1");
//        for (KeyValue kv : row.raw()) {
//            System.out.print(new String(kv.getRow()) + " ");
//            System.out.print(new String(kv.getFamily()) + ":");
//            System.out.print(new String(kv.getQualifier()) + " = ");
//            System.out.print(new String(kv.getValue()));
//            System.out.print(" timestamp = " + kv.getTimestamp() + "\n");
//
//        }
        hbaseService.put("2","user","info1",new String[]{"name","age"},new String[]{"tom","26"});
    }



}
