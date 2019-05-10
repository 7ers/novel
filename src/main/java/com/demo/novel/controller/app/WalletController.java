package com.demo.novel.controller.app;

import com.demo.novel.entity.UserBalance;
import com.demo.novel.service.UserBalanceService;
import com.demo.novel.util.Constants;
import com.demo.novel.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/wallet")
public class WalletController {
    @Autowired
    UserBalanceService userBalanceService;

    /**
     * 充值、消费接口
     * @param username
     * @param money 金额
     * @param type 0-消费，1-充值
     * @return
     */
    @PostMapping("/trans")
    public JsonResult trans(String username, String money, String type){
        try{
            UserBalance ub = userBalanceService.selectByUserName(username);
            UserBalance inUserBalance = new UserBalance();
            BigDecimal cash = BigDecimal.valueOf(Double.parseDouble(money));
            inUserBalance.setUsername(username);
            if(ub==null){
                inUserBalance.setMoney(cash);
                userBalanceService.addUserBalance(inUserBalance);
            }else{
                if("0".equals(type)){
                    inUserBalance.setMoney(ub.getMoney().add(cash));
                }else if("1".equals(type)){
                    inUserBalance.setMoney(ub.getMoney().subtract(cash));
                }else{
                    return new JsonResult(Constants.RET_CODE_ERROR,"类型错误");
                }
                inUserBalance.setId(ub.getId());
                userBalanceService.updateUserBalance(inUserBalance);
            }
            return new JsonResult(Constants.RET_CODE_00000,Constants.RET_DESC_00000);
        }catch (Exception e){
            return new JsonResult(Constants.RET_CODE_ERROR,"充值失败");
        }
    }
}
