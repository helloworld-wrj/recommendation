package or.wr.bookrecommendationsystem.config;

import or.wr.bookrecommendationsystem.interceptor.LoginInterceptor;
import or.wr.bookrecommendationsystem.interceptor.OrdinaryUserInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;


@Configuration
public class WebConfigurer implements WebMvcConfigurer {

    @Resource
    private LoginInterceptor loginInterceptor;

    @Resource
    private OrdinaryUserInterceptor ordinaryUserInterceptor;



    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor).addPathPatterns("/manager/**")
                .excludePathPatterns("/login","/register/**", "/webjars/**","/css/**","/images/**","/dist/**");

        registry.addInterceptor(ordinaryUserInterceptor).addPathPatterns("/user/**")
                .excludePathPatterns("/login","/register/**", "/webjars/**","/css/**","/images/**","/dist/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

    }
}
