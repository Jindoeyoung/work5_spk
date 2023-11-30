package com.spk.api.service.redis;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.spk.api.dao.redis.UserDetailsDAO_v3;
import com.spk.api.entity.redis.userdetails_v3.UserDetails;
//import com.spk.api.entity.userdetails_v3.UserDetailsData;
//import com.spk.api.entity.usergrids.UserGridsDataDetail;
import com.spk.api.entity.redis.userdetails_v3.UserDetails_depth_1;

@Service
public class UserDetailsService_v3 {

    private final UserDetailsDAO_v3 redisDAO;

    public UserDetailsService_v3(UserDetailsDAO_v3 redisDAO) {
        this.redisDAO = redisDAO;
    }

    public UserDetails registerUser(String userid, UserDetails_depth_1 data) throws IOException {
        UserDetails userApi = new UserDetails();
        userApi.setUser_id(userid);
        userApi.setData(data);
        redisDAO.setUser(userApi);
        return redisDAO.getUser(userid);
    }    

    public void deleteUser(String userid) {
        redisDAO.deleteUser(userid);
    }

    public UserDetails getUser(String userid) throws IOException {
        return redisDAO.getUser(userid);
    }

    public List<String> getUsernameList() {
        return redisDAO.getAllUsers();
    }
}