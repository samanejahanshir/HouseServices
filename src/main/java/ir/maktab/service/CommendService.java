package ir.maktab.service;

import ir.maktab.data.dao.CommendDao;
import ir.maktab.data.model.Commend;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Data
public class CommendService {
    final CommendDao commendDao;

    public void saveCommend(Commend commend) {
        commendDao.save(commend);
    }
}
