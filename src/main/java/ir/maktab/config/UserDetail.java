package ir.maktab.config;

import ir.maktab.data.dao.UserDao;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@RequiredArgsConstructor
@AllArgsConstructor
public class UserDetail implements UserDetailsService {
    private UserDao userDao;
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<ir.maktab.data.model.User> user = userDao.findByEmail(email);
        if (user.isEmpty())
            throw new UsernameNotFoundException(email);
        return User.withUsername(user.get().getEmail())
                .password(passwordEncoder.encode(user.get().getPassword()))
                .roles(user.get().getRole().name()).build();
    }
}
