package com.spk.api.service.redis;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.spk.api.dao.redis.UserGridsDAO_v3;
import com.spk.api.entity.redis.usergrids_v3.UserGrids;
import com.spk.api.entity.redis.usergrids_v3.UserGrids_depth_1;

@Service
public class UserGridsService_v3 {

    private final UserGridsDAO_v3 redisDAO;

    public UserGridsService_v3(UserGridsDAO_v3 redisDAO) {
        this.redisDAO = redisDAO;
    }

    public UserGrids registerUser(String userid, UserGrids_depth_1 data) throws IOException {
        UserGrids userApi = new UserGrids();
        userApi.setUser_id(userid);
        userApi.setData(data);

        redisDAO.setUser(userApi);

        return redisDAO.getUser(userid);
    }    

    public void deleteUser(String userid) {
        redisDAO.deleteUser(userid);
    }

    public UserGrids getUser(String userid) throws IOException {
        return redisDAO.getUser(userid);
    }

    public List<String> getUsernameList() {
        return redisDAO.getAllUsers();
    }
}