package ir.maktab.config;

import ir.maktab.data.dao.UserDao;
import ir.maktab.data.enums.UserType;
import ir.maktab.data.model.User;
import ir.maktab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private  UserDao userDao;
    DefaultRedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

   /* public LoginSuccessHandler(UserDao userDao) {
        this.userDao = userDao;
    }
*/
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        String principal =(String) authentication.getPrincipal();
        Optional<User> user = userDao.findByEmail(principal);
        if (user.isPresent()) {
            User user1 = user.get();
            if (user1.getRole().equals(UserType.CUSTOMER)) {
                redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, "/customer/home");
            } else if (user1.getRole().equals(UserType.EXPERT)) {
                redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, "/expert/home");
            } else {
                redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, "/manager/home");
            }
        }

    }
}
