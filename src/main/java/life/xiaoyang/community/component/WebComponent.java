package life.xiaoyang.community.component;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebComponent implements WebMvcConfigurer {

    @Value("${exclude.interceptor}")
    private String value;

    @Autowired
    UserInteceptor interceptor;

    public void addInterceptors(InterceptorRegistry registry) {
        String[] split = value.split(",");
        registry.addInterceptor(interceptor).addPathPatterns("/*").excludePathPatterns(split[0],split[1],split[2]
        ,split[3],split[4],split[5],split[6],split[7]);
    }

}
