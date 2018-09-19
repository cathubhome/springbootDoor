package com.ciwr;

import com.ciwr.global.utils.HttpClientApi;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    private HttpClientApi httpClientApi;

    @Test
    public void testGet(){
        try {
            String response = httpClientApi.doGet("http://www.baidu.com");
            log.info("请求返回：{}",response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
