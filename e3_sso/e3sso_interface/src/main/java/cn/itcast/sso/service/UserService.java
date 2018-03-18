package cn.itcast.sso.service;

import cn.itcast.pojo.E3Result;
import cn.itcast.pojo.TbUser;

public interface UserService {
    E3Result checkName(String Param, int type);
    E3Result SaveTbuser(TbUser tbUser);
    E3Result login(String username,String password);
    E3Result getUser(String token);
}
