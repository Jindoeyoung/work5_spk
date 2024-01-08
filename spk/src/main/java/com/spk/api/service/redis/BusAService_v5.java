package com.spk.api.service.redis;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.spk.api.dao.redis.BusADAO_v5;
import com.spk.api.entity.redis.busa_v5.BusA;
import com.spk.api.entity.redis.busa_v5.BusA_depth_1;
//import com.spk.api.entity.busa_v4.BusADataDetail;

@Service
public class BusAService_v5 {

    private final BusADAO_v5 redisDAO;

    public BusAService_v5(BusADAO_v5 redisDAO) {
        this.redisDAO = redisDAO;
    }

    public BusA registerData(String userid, List<BusA_depth_1> flaginfo) throws IOException {
//    public BusA registerUser(String userid, List<BusADataDetail> flaginfo) throws IOException {
        BusA busA = new BusA();
        busA.setUser_id(userid);
        busA.setFlag_info(flaginfo);

        redisDAO.setKeyValue(busA);

        return redisDAO.getKeyValue(userid);
    }    

    public void deleteData(String userid) {
        redisDAO.deleteKeyValue(userid);
    }

    public BusA getData(String userid) throws IOException {
        return redisDAO.getKeyValue(userid);
    }

    public List<String> getDataList() {
        return redisDAO.getAllKeyValues();
    }
}