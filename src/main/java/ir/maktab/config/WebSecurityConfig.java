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

import javax.sql.DataSource;

@ComponentScan
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String AUTHORITY_QUERY = "Select user.email,user.role from user, role where email =?";
    private static final String USERNAME_QUERY = "Select email, password, 1 from user where email = ?";
    @Autowired
    private UserDao userDao;

    @Autowired
    DataSource dataSource;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetail(userDao, passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
      /*  http
                .authorizeRequests()
                .antMatchers("/","/Signup","/expert/Signup","/customer/Signup","/index","/expert/register","/customer/register","/expert/confirm-account","/customer/confirm-account")
                .permitAll()
                .antMatchers()
                .authenticated()
                .and()
                .formLogin()
                .and()
                .logout();*/
        http.csrf().disable()
                .authorizeRequests()
                //.antMatchers("/", "/Signup", "/expert/Signup", "/customer/Signup", "/index", "/expert/register", "/customer/register", "/expert/confirm-account", "/customer/confirm-account")
                .antMatchers("/**/**")
                .permitAll()
                //.anyRequest().authenticated()
               // .antMatchers("/customer/**").hasAuthority("CUSTOMER")
              //  .antMatchers("/expert/**").hasAuthority("EXPERT")
              //  .antMatchers("/manager/**").hasAuthority("MANAGER")
                .and()
                .formLogin().loginPage("/login")
                .usernameParameter("email")
                .passwordParameter("password")
                .successHandler(new LoginSuccessHandler()).permitAll();
                //.defaultSuccessUrl("/dologin",true)
               // .and().rememberMe().rememberMeCookieName("email")
               // .tokenValiditySeconds(1200)
               // .rememberMeParameter("email")
               // .and()
                //.logout();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery(USERNAME_QUERY)
                .authoritiesByUsernameQuery(AUTHORITY_QUERY)
                .passwordEncoder(passwordEncoder());
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }
}
