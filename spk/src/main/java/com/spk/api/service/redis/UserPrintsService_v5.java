package com.spk.api.service.redis;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.spk.api.dao.redis.UserPrintsDAO_v5;
import com.spk.api.entity.redis.usergrids_v5.UserGrids;
import com.spk.api.entity.redis.usergrids_v5.UserGrids_depth_1;

@Service
public class UserPrintsService_v5 {

    private final UserPrintsDAO_v5 redisDAO;

    public UserPrintsService_v5(UserPrintsDAO_v5 redisDAO) {
        this.redisDAO = redisDAO;
    }

    public UserGrids registerData(String userid, UserGrids_depth_1 data) throws IOException {
        UserGrids userApi = new UserGrids();
        userApi.setUser_id(userid);
        userApi.setData(data);
        redisDAO.setKeyValue(userApi);
        return redisDAO.getKeyValue(userid);
    }    

    public void deleteData(String userid) {
        redisDAO.deleteKeyValue(userid);
    }

    public UserGrids getData(String userid) throws IOException {
        return redisDAO.getKeyValue(userid);
    }

    public List<String> getDataList() {
        return redisDAO.getAllKeyValues();
    }
}