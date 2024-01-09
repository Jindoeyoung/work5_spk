package com.spk.api.service.redis;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.spk.api.dao.redis.UserDetailsDAO_v5;
import com.spk.api.entity.redis.userdetails_v5.UserDetails;
import com.spk.api.entity.redis.userdetails_v5.UserDetails_depth_1;

@Service
public class UserDetailsService_v5 {

    private final UserDetailsDAO_v5 redisDAO;

    public UserDetailsService_v5(UserDetailsDAO_v5 redisDAO) {
        this.redisDAO = redisDAO;
    }

    public UserDetails registerData(String userid, UserDetails_depth_1 data) throws IOException {
        UserDetails userApi = new UserDetails();
        userApi.setUser_id(userid);
        userApi.setData(data);

        redisDAO.setKeyValue(userApi);

        return redisDAO.getKeyValue(userid);
    }    

    public void deleteData(String userid) {
        redisDAO.deleteKeyValue(userid);
    }

    public UserDetails getData(String userid) throws IOException {
        return redisDAO.getKeyValue(userid);
    }

    public List<String> getDataList() {
        return redisDAO.getAllKeyValues();
    }
}