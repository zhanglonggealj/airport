package com.esop.airport.config;

import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.esop.airport.infrastructure.GeneralInterceptor;
import com.esop.airport.infrastructure.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liliqiang on 2018/1/31.
 */
@Configuration
public class MyWebAppConfigurer extends WebMvcConfigurationSupport {


    @Autowired
    TokenInterceptor tokenInterceptor;

    @Autowired
    GeneralInterceptor generalInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //配置拦截器 用于身份证 权限认证
        registry.addInterceptor(tokenInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/druid/**")
                .excludePathPatterns("/open/**")//这个下的不进行权限拦截。
                .excludePathPatterns("/third/**")
                .excludePathPatterns("/data/suppor/**");


        registry.addInterceptor(generalInterceptor).addPathPatterns("/general/**");//excludePathPatterns("")

        super.addInterceptors(registry);
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);//json序列化的配置

        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();

        FastJsonConfig fastJsonConfig = new FastJsonConfig();

//        fastJsonConfig.setSerializerFeatures(
//                SerializerFeature.PrettyFormat,
//                SerializerFeature.WriteMapNullValue,
//                SerializerFeature.WriteNullListAsEmpty,
//                SerializerFeature.WriteNullStringAsEmpty,
//                SerializerFeature.IgnoreNonFieldGetter
//        );
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.PrettyFormat,
//                SerializerFeature.IgnoreNonFieldGetter
                SerializerFeature.WriteMapNullValue
        );
        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
        fastJsonConfig.setSerializeConfig(getSerializeConfig());
        converter.setFastJsonConfig(fastJsonConfig);


        List<MediaType> mediaTypeList = new ArrayList<>();
        mediaTypeList.add(MediaType.APPLICATION_JSON);
        mediaTypeList.add(MediaType.APPLICATION_FORM_URLENCODED);
        converter.setSupportedMediaTypes(mediaTypeList);

        converters.add(converter);

    }

    public SerializeConfig getSerializeConfig() {

        //自定义拦截器
        SerializeConfig config = new SerializeConfig();
        config.put(BigDecimal.class, new BigDecimalFormatSerializer());
        return config;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        //配置静态资源映射
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");


        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

        super.addResourceHandlers(registry);
    }

//    @Override
//    protected void addViewControllers(ViewControllerRegistry registry) {
//
//        registry.addViewController("/").setViewName("redirect:/web/index");
//        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
//
//        super.addViewControllers(registry);
//    }

    //    @Bean
//    public MultipartConfigElement multipartConfigElement(){
//        MultipartConfigFactory factory = new MultipartConfigFactory();
//        //文件最大KB,MB
//        factory.setMaxFileSize("2MB");
//        //设置总上传数据总大小
//        factory.setMaxRequestSize("10MB");
//        return factory.createMultipartConfig();
//    }
}
