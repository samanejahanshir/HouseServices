package ir.maktab.config;


import ir.maktab.data.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.sql.DataSource;

@ComponentScan
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDao userDao;

    @Autowired
    DataSource dataSource;

    private final AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    public WebSecurityConfig(AuthenticationSuccessHandler authenticationSuccessHandler) {
        this.authenticationSuccessHandler = authenticationSuccessHandler;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new MyUserDetail(userDao, passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/Signup", "/expert/Signup", "/customer/Signup", "/index", "/expert/register", "/customer/register", "/expert/confirm-account", "/customer/confirm-account")
                // .antMatchers("/**/**")
                .permitAll()
                .antMatchers("/customer/**").hasAuthority("CUSTOMER")
                .antMatchers("/expert/**").hasAuthority("EXPERT")
                .antMatchers("/manager/**").hasAuthority("MANAGER")
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login")
                .successHandler(authenticationSuccessHandler).permitAll()
                //.defaultSuccessUrl("/dologin",true)
                .usernameParameter("email")
                .passwordParameter("password")
                .failureUrl("/error");
        //.defaultSuccessUrl("/dologin",true)
        // .and().rememberMe().rememberMeCookieName("email")
        // .tokenValiditySeconds(1200)
        // .rememberMeParameter("email")
        // .and()
        //.logout();
    }

   /* @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("Select email, password from user where email = ?")
                .authoritiesByUsernameQuery("Select user.email,user.role from user where email =?")
                .passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }*/
}
