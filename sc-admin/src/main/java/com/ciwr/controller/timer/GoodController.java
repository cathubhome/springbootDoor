package com.ciwr.controller.timer;

import com.ciwr.global.common.utils.G;
import com.ciwr.modle.Good;
import com.ciwr.service.sys.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/good")
public class GoodController {

    @Autowired
    private GoodService goodService;

    @RequestMapping(value = "/save")
    public G saveGood(Good good) throws Exception {
        goodService.save(good);
        return G.ok();
    }

    @RequestMapping("/bussinessLock")
    public G businessLock(){
        Good good = new Good();
        good.setId(1000L);
        good.setName("aaaa");
        good.setPrice(new BigDecimal("1.88"));
        goodService.updateGood(good);
        return G.ok();
    }
}
