package com.spk.api.service.redis;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.spk.api.dao.redis.BusADAO_v4;
import com.spk.api.entity.redis.busa_v4.BusA;
import com.spk.api.entity.redis.busa_v4.BusA_depth_1;
//import com.spk.api.entity.busa_v4.BusADataDetail;

@Service
public class BusAService_v4 {

    private final BusADAO_v4 redisDAO;

    public BusAService_v4(BusADAO_v4 redisDAO) {
        this.redisDAO = redisDAO;
    }

    public BusA registerUser(String userid, List<BusA_depth_1> flaginfo) throws IOException {
//    public BusA registerUser(String userid, List<BusADataDetail> flaginfo) throws IOException {
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