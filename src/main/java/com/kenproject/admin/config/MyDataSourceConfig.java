package com.kenproject.admin.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;
@Deprecated
//@Configuration
public class MyDataSourceConfig {

    //默认的自动配置类容器中没有才会配@ConditionalOnMissingBean(DataSource.class)
    @ConfigurationProperties("spring.datasource")
    @Bean
    public DataSource dataSource() throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
//        dataSource.setDriver();
//        dataSource.setUrl();
//        dataSource.setUsername();
//        dataSource.setPassword();
        //加入监控功能
//        dataSource.setFilters("stat,wall");
//        dataSource.setMaxActive(10);
        return dataSource;
    }

    /**
     * 配置 druid的监控页面
     * @return
     */
    @Bean
    public ServletRegistrationBean statViewServlet(){
        StatViewServlet statViewServlet = new StatViewServlet();
        ServletRegistrationBean<StatViewServlet> registrationBean = new ServletRegistrationBean<>(statViewServlet, "/druid/*");
        registrationBean.addInitParameter("loginUsername","admin");
        registrationBean.addInitParameter("loginPassword","123456");

        return registrationBean;
    }


    /**
     * webStatFilter用来采集web-jdbc关联监控的数据
     */
    @Bean
    public FilterRegistrationBean webStatFilter(){
        WebStatFilter webStatFilter = new WebStatFilter();
        FilterRegistrationBean<WebStatFilter> registrationBean = new FilterRegistrationBean<>(webStatFilter);
        registrationBean.setUrlPatterns(Arrays.asList("/*"));
        registrationBean.addInitParameter("exclusion","*.js,*.jpg,*.png,*.ico,*.css,/druid/*");
        return registrationBean;
    }
}
