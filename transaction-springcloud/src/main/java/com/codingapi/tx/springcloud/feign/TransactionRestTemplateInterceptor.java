package com.codingapi.tx.springcloud.feign;

import com.codingapi.tx.aop.bean.TxTransactionLocal;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by lorne on 2017/6/26.
 */
public class TransactionRestTemplateInterceptor implements RequestInterceptor {


    private Logger logger = LoggerFactory.getLogger(TransactionRestTemplateInterceptor.class);

    @Override
    public void apply(RequestTemplate requestTemplate) {
//        String currentToken = BizDataInterface.getBean().currentToken();
//        if (StringUtils.isEmpty(currentToken)) {
//            System.out.println("currentToken 111 = " + currentToken);
//            currentToken = "aaaaaaa";
//        } else {
//            currentToken = currentToken;
//            System.out.println("currentToken 222 = " + currentToken);
//        }
//        requestTemplate.header(GlobalConstant.TOKEN_NAME_HEADER, currentToken);
//        logger.info(String.format(" ++++++++++ LCN      TransactionRestTemplateInterceptor   feign client 调用 service：%s    token:%s", requestTemplate.url(), currentToken));
        logger.info(String.format(" ********* LCN      TransactionRestTemplateInterceptor   feign client 调用 service：%s ", requestTemplate.url()));

        TxTransactionLocal txTransactionLocal = TxTransactionLocal.current();
        String groupId = txTransactionLocal == null ? null : txTransactionLocal.getGroupId();

        logger.info("LCN-SpringCloud TxGroup info -> groupId:"+groupId);

        if (txTransactionLocal != null) {
            requestTemplate.header("tx-group", groupId);
        }
    }

}
