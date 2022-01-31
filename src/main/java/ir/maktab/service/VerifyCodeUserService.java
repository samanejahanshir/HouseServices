package ir.maktab.service;

import ir.maktab.data.dao.VerifyCodeUserDao;
import ir.maktab.data.model.VerifyCodeUser;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Data
public class VerifyCodeUserService {
    private final VerifyCodeUserDao verifyCodeUserDao;

    public void save(VerifyCodeUser verifyCodeUser) {
        verifyCodeUserDao.save(verifyCodeUser);
    }

    public int getVerifyCodeByEmail(String email) {
        Optional<VerifyCodeUser> codeUser = verifyCodeUserDao.findByEmail(email);
        if (codeUser.isPresent()) {
            return codeUser.get().getCode();
        } else {
            throw new RuntimeException("this user email not found");
        }
    }

    public void deleteVerifyCode(String email) {
        Optional<VerifyCodeUser> codeUser = verifyCodeUserDao.findByEmail(email);
        if (codeUser.isPresent()) {
            VerifyCodeUser codeUser1 = codeUser.get();
            verifyCodeUserDao.delete(codeUser1);
        }
    }
}
