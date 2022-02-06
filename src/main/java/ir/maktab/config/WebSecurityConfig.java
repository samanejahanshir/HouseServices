package ir.maktab.config;


import ir.maktab.data.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@ComponentScan
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDao userDao;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(){
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
    }
}
