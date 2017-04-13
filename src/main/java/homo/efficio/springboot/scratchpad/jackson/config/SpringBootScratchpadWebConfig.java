package homo.efficio.springboot.scratchpad.jackson.config;

import homo.efficio.springboot.scratchpad.jackson.filter.CORSFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

/**
 * @author homo.efficio@gmail.com
 *         created on 2017-04-13
 */
@Configuration
public class SpringBootScratchpadWebConfig {

    @Bean
    public FilterRegistrationBean corsFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new CORSFilter());
        registration.setOrder(5);
        registration.addInitParameter("cors.allowed.methods", "GET, POST, HEAD, OPTIONS, PUT, DELETE");
        registration.setDispatcherTypes(EnumSet.allOf(DispatcherType.class));
        return registration;
    }
}
