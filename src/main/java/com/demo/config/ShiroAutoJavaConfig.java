package com.demo.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.demo.realm.MyRealm;
import lombok.Data;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import java.util.HashMap;
import java.util.Map;

@Data
@Configuration
@ConfigurationProperties(prefix = "shiro")
public class ShiroAutoJavaConfig {
    private String hashAlgorithmName;
    private int hashIterations;
    private String loginUrl= "/static/login.html";
    private String unauthorizedUrl;
    private String[] anonUrls; //放行的url
    private String logoutUrl="/logout.html"; //登出的url
    private String[] authcUrls; //需要认证的url

    /**
     * 声明凭证匹配器
     * @return
     */
    @Bean
    public HashedCredentialsMatcher getHashedCredentialsMatcher(){
        HashedCredentialsMatcher credentialsMatcher=new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName(hashAlgorithmName);
        credentialsMatcher.setHashIterations(hashIterations);
        return credentialsMatcher;
    }
    /**
     * 声明realm
     */
    @Bean
    public MyRealm getMyRealm(HashedCredentialsMatcher hashedCredentialsMatcher){
        MyRealm realm=new MyRealm();
        realm.setCredentialsMatcher(hashedCredentialsMatcher);
        return realm;
    }
    /**
     * 声明securityManager
     */
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(MyRealm realm){
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
        return securityManager;
    }
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager)
    {
        ShiroFilterFactoryBean filterFactoryBean=new ShiroFilterFactoryBean();
        filterFactoryBean.setSecurityManager(securityManager);
        filterFactoryBean.setLoginUrl(this.loginUrl);
        filterFactoryBean.setUnauthorizedUrl(this.unauthorizedUrl);
        filterFactoryBean.setSuccessUrl("index.do");
        Map<String,String> filterChainDefinitionMap=new HashMap<>();
        if(this.isArrayIsEmpty(anonUrls)){
            for(String url:anonUrls){
                filterChainDefinitionMap.put(url,"anon");
            }
        }
        filterChainDefinitionMap.put(logoutUrl,"logout");
        if(this.isArrayIsEmpty(authcUrls)){
            for(String url:authcUrls){
                filterChainDefinitionMap.put(url,"authc");
            }
        }
        filterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return filterFactoryBean;
    }
    @Bean
    public AuthorizationAttributeSourceAdvisor
    authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor=new
                AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
    /**
     * 委托过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean<DelegatingFilterProxy> getDelegatingFilterProxy(){
        DelegatingFilterProxy filterProxy=new DelegatingFilterProxy();
        filterProxy.setTargetFilterLifecycle(true);
        filterProxy.setTargetBeanName("shiroFilter");
        FilterRegistrationBean<DelegatingFilterProxy> bean=new FilterRegistrationBean<>();
        bean.setFilter(filterProxy);
        return bean;
    }
    @Bean("shiroDialect")
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }
    public boolean isArrayIsEmpty(String[] arrays){
        if(this.anonUrls!=null&&this.anonUrls.length>0){
            return true;
        }else{
            return false;
        }
    }

}
