package com.qst.crop.security.config;


import com.qst.crop.security.filter.CustomAuthenticationFilter;
import com.qst.crop.security.filter.JwtAuthenticationTokenFilter;
import com.qst.crop.security.filter.WebSecurityCorsFilter;
import com.qst.crop.security.handler.EntryPointUnauthorizedHandler;
import com.qst.crop.security.handler.MyAuthenticationFailureHandler;
import com.qst.crop.security.handler.MyAuthenticationSuccessHandler;
import com.qst.crop.security.handler.RestAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

/**
 * Spring Security 配置类
 *
 * @author QST
 * @EnableGlobalMethodSecurity 开启注解的权限控制，默认是关闭的。
 * prePostEnabled：使用表达式实现方法级别的控制，如：@PreAuthorize("hasRole('ADMIN')")
 * securedEnabled: 开启 @Secured 注解过滤权限，如：@Secured("ROLE_ADMIN")
 * jsr250Enabled: 开启 @RolesAllowed 注解过滤权限，如：@RolesAllowed("ROLE_ADMIN")
 * @create 2019/1/11
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
    @Autowired
    private EntryPointUnauthorizedHandler entryPointUnauthorizedHandler;
    @Autowired
    private RestAccessDeniedHandler restAccessDeniedHandler;
    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;

    /**
     * 从容器中取出 AuthenticationManagerBuilder，执行方法里面的逻辑之后，放回容器
     *
     * @param authenticationManagerBuilder
     * @throws Exception
     */
    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    //加密
    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /**
         * 在 UsernamePasswordAuthenticationFilter 之前添加 JwtAuthenticationTokenFilter
         */
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)//用登录之后的认证信息，再次访问时可以直接访问了，UsernamePasswordAuthenticationFilter之前添加拦截器
            .addFilterBefore(new WebSecurityCorsFilter(), ChannelProcessingFilter.class);//自定义拦截器解决跨域问题，在security过滤链之前拦截

        // 禁用 CSRF 保护（在使用 token 时通常不需要）
        http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests()
                // 允许所有 OPTIONS 请求（通常用于 CORS 预检请求）
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers("/swagger-ui.html",
                        "/map.html",
                        "/swagger-ui/*",
                        "/swagger-resources/**",
                        "/v2/api-docs",
                        "/v3/api-docs",
                        "/webjars/**",
                        "/user/**",
                        "/user/add/**",
                        "/order/goods/**","/order/All/**","/order/needs/**","/order/selectById/**","/order/searchGoodsByKeys/**","/order/searchNeedsByKeys/**","/order/searchAllByKeys/**",
                        "/knowledge/**",
                        "/paySuccessful/**",
                        "/file/**").permitAll()
                // 配置其他所有请求都需要认证
                .anyRequest().authenticated()   // 任何请求,登录后可以访问
                .and().addFilterAt(customAuthenticationFilter(),//JSON登陆实现，在 UsernamePasswordAuthenticationFilter，之前添加JSON格式
                UsernamePasswordAuthenticationFilter.class)
                //.addFilter(corsFilter())
                .formLogin().loginProcessingUrl("/user/login")
                .successHandler(myAuthenticationSuccessHandler)//密码正确；认证成功
                .failureHandler(myAuthenticationFailureHandler)//密码失败，认证失败
                .and().logout()
                .and().headers().cacheControl();

//        // 处理异常情况：认证失败和权限不足
        http.exceptionHandling().authenticationEntryPoint(entryPointUnauthorizedHandler).accessDeniedHandler(restAccessDeniedHandler);
        //执行默认的LoginUrlAuthenticationEntryPoint 会自动跳转到登录页面
      //  http.exceptionHandling().accessDeniedHandler(restAccessDeniedHandler);
    }

    //注册自定义的UsernamePasswordAuthenticationFilter
    @Bean
    CustomAuthenticationFilter customAuthenticationFilter() throws Exception {
        CustomAuthenticationFilter filter = new CustomAuthenticationFilter();
        filter.setAuthenticationSuccessHandler(myAuthenticationSuccessHandler);
        filter.setAuthenticationFailureHandler(myAuthenticationFailureHandler);
        filter.setFilterProcessesUrl("/user/login");
        //这句很关键，重用WebSecurityConfigurerAdapter配置的AuthenticationManager，不然要自己组装AuthenticationManager
        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
    }

}
