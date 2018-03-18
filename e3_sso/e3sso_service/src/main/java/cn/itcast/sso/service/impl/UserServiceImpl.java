package cn.itcast.sso.service.impl;

import cn.itcast.jedis.JedisClient;
import cn.itcast.mapper.TbUserMapper;
import cn.itcast.pojo.E3Result;
import cn.itcast.pojo.TbUser;
import cn.itcast.pojo.TbUserExample;
import cn.itcast.sso.service.UserService;
import cn.itcast.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: 徐冬虎
 * Date: 2018/3/17 0017
 * Time: 下午 7:55
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    TbUserMapper tbUserMapper;

    @Value("${session.expire}")
    Integer sessionExpire;
    @Autowired
    JedisClient jedisClient;
    @Override
    public E3Result checkName(String Param, int type) {
        TbUserExample tbUserExample = new TbUserExample();
        TbUserExample.Criteria criteria = tbUserExample.createCriteria();
        //1代表用户名 2,代表手机  3 代表邮箱
        if (type==1){
            criteria.andUsernameEqualTo(Param);
        }else if (type==2){
            criteria.andPhoneEqualTo(Param);
        }else if (type==3){
            criteria.andEmailEqualTo(Param);
        }else {
            return E3Result.build(500,"非法字符");

        }
        List<TbUser> tbUsers = tbUserMapper.selectByExample(tbUserExample);
        if (tbUsers!=null&&tbUsers.size()>0){
            return E3Result.ok(false);

        }
        return E3Result.ok(true);
    }

    @Override
    public E3Result getUser(String token) {
        String json = jedisClient.hget("session:"+token, "user");
        if (StringUtils.isBlank(json)){
            return E3Result.build(400,"用户已过期,请您您重新登录");
        }
        TbUser user = JsonUtils.jsonToPojo(json, TbUser.class);
        jedisClient.expire("session:"+token,sessionExpire);
        return E3Result.ok(user);
    }

    @Override
    public E3Result login(String username, String password) {
        if (StringUtils.isBlank(username)&&StringUtils.isBlank(password)){
            return E3Result.build(400,"用户名或者密码不能为空");
        }
        TbUserExample tbUserExample = new TbUserExample();
        TbUserExample.Criteria criteria = tbUserExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<TbUser> tbUsers = tbUserMapper.selectByExample(tbUserExample);
        if (tbUsers==null||tbUsers.size()==0) {
            return E3Result.build(400,"用户名或者密码不正确");
        }
        TbUser user = tbUsers.get(0);
        if (!user.getPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes()))){
            return E3Result.build(400,"用户名或者密码不正确");
        }
        user.setPassword("");
        String token = UUID.randomUUID().toString();
        jedisClient.hset("session:"+token,"user", JsonUtils.objectToJson(user));
        jedisClient.expire("session:"+token,sessionExpire);
        return E3Result.ok(token);
    }

    @Override
    public E3Result SaveTbuser(TbUser tbUser) {
        tbUser.setCreated(new Date());
        tbUser.setUpdated(new Date());
        if (StringUtils.isBlank(tbUser.getUsername())){
            E3Result.build(500,"用户名不能为空");
        }
        if (StringUtils.isBlank(tbUser.getPassword())){
            E3Result.build(500,"密码不能为空");
        }
        E3Result e3Result = checkName(tbUser.getUsername(), 1);
        if (!(Boolean) e3Result.getData()){
            return  e3Result.build(500,"用户名已被使用");
        }

        if (StringUtils.isNotBlank(tbUser.getEmail())){

            E3Result e3Result1 = checkName(tbUser.getEmail(), 3);
            if (!(boolean)e3Result1.getData()){
                e3Result.build(500,"邮箱不可用");
            }
        }

        if (StringUtils.isNotBlank(tbUser.getPhone())){
            E3Result e3Result2 = checkName(tbUser.getPhone(), 2);
            if (!(boolean)e3Result2.getData()){
                e3Result.build(500,"手机不可用");
            }
        }

        String password = DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes());
        tbUser.setPassword(password);
        tbUserMapper.insert(tbUser);
        return E3Result.ok();
    }
}
