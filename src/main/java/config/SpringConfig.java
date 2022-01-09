package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = {HibernateUtil.class})
@ComponentScan(basePackages = {"data.dao", "service"})
public class SpringConfig {
}
