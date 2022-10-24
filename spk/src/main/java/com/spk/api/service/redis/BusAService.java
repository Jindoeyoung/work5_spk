package com.spk.api.service.redis;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.spk.api.entity.redis.BusADataDetail;
import com.spk.api.dao.redis.BusADAO;
import com.spk.api.entity.redis.BusA;

@Service
public class BusAService {

    private final BusADAO redisDAO;

    public BusAService(BusADAO redisDAO) {
        this.redisDAO = redisDAO;
    }

    public BusA registerUser(String userid, List<BusADataDetail> flaginfo) throws IOException {
        BusA busA = new BusA();
        busA.setUser_id(userid);
        busA.setFlag_info(flaginfo);

        redisDAO.setUser(busA);

        return redisDAO.getUser(userid);
    }    

    public void deleteUser(String userid) {
        redisDAO.deleteUser(userid);
    }

    public BusA getUser(String userid) throws IOException {
        return redisDAO.getUser(userid);
    }

    public List<String> getUsernameList() {
        return redisDAO.getAllUsers();
    }
}
