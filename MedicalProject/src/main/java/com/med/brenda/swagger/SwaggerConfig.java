package com.med.brenda.swagger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;

/**
 * Spring MVC RestFull接口文档解析器
 * <p>com.doctor330.medical</p>
 * <p>Title: SwaggerConfig.java</p>
 * <p>Description:$</p>
 * <p>Package:com.med.brenda.swagger</p>
 * @author chenhj(brenda)
 * @date 2016年8月1日 下午3:56:10
 * @tag 
 * @encode
@Configuration
@EnableSwagger
 */
public class SwaggerConfig {
	 private SpringSwaggerConfig springSwaggerConfig;

	    /**
	     * Required to autowire SpringSwaggerConfig
	     */
	    @Autowired
	    public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig)
	    {
	        this.springSwaggerConfig = springSwaggerConfig;
	    }

	    /**
	     * Every SwaggerSpringMvcPlugin bean is picked up by the swagger-mvc
	     * framework - allowing for multiple swagger groups i.e. same code base
	     * multiple swagger resource listings.
	     */
	    @Bean
	    public SwaggerSpringMvcPlugin customImplementation()
	    {
	        return new SwaggerSpringMvcPlugin(this.springSwaggerConfig)
	                .apiInfo(apiInfo())
	                .includePatterns(".*?");
	    }

	    private ApiInfo apiInfo()
	    {
	        ApiInfo apiInfo = new ApiInfo(
	                "Doctor330 App Restfull API",
	                "实时接口描述文档 - Spring MVC Restfull API",
	                "My Apps API terms of service",
	                "My Apps API Contact Email",
	                "My Apps API Licence Type",
	                "My Apps API License URL");
	        return apiInfo;
	    }
}
