package com.esop.airport.config;

import com.esop.airport.utils.StringUtils;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author arvin liliqiang
 * @create 2018-11-29 3:37 PM
 **/

@Configuration
@ConditionalOnClass(WxPayService.class)
@EnableConfigurationProperties(WxPayProperties.class)
public class WxPayConfiguration {

    @Autowired
    WxPayProperties wxPayProperties;


    @Bean("wxPayService")
    @ConditionalOnMissingBean
    public WxPayService wxPayService() {
        WxPayConfig payConfig = new WxPayConfig();
        payConfig.setAppId(StringUtils.trimToNull(this.wxPayProperties.getAppId()));
        payConfig.setMchId(StringUtils.trimToNull(this.wxPayProperties.getMchId()));
        payConfig.setMchKey(StringUtils.trimToNull(this.wxPayProperties.getMchKey()));
        payConfig.setSubAppId(StringUtils.trimToNull(this.wxPayProperties.getSubAppId()));
        payConfig.setSubMchId(StringUtils.trimToNull(this.wxPayProperties.getSubMchId()));
        payConfig.setKeyPath(StringUtils.trimToNull(this.wxPayProperties.getKeyPath()));

        // 可以指定是否使用沙箱环境
        payConfig.setUseSandboxEnv(false);

        WxPayService wxPayService = new WxPayServiceImpl();
        wxPayService.setConfig(payConfig);
        return wxPayService;
    }

}
