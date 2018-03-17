package cn.itcast.service.impl;

import cn.itcast.mapper.TbItemDescMapper;
import cn.itcast.pojo.*;
import cn.itcast.mapper.TbItemMapper;
import cn.itcast.service.ItemService;
import cn.itcast.utils.IDUtils;
import cn.itcast.utils.JsonUtils;
import com.alibaba.dubbo.common.json.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;

import javax.annotation.Resource;
import javax.jms.*;
import java.util.Date;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    JmsTemplate jmsTemplate;
    @Autowired
    TbItemDescMapper tbItemDescMapper;
    @Autowired
    TbItemMapper tbItemMapper;

    @Autowired
    JedisCluster jedisCluster;
    @Resource
    Destination topicDestination;
    @Value("${redis.itemInfo.expire}")
    Integer redis_itemInfo_expire;
    @Override
    public TbItem findTbItemById(long id) {
        String s = jedisCluster.get("item_info:" + id + ":base");
        if (StringUtils.isNotBlank(s)){
            return JsonUtils.jsonToPojo(s,TbItem.class);
        }
        TbItem tbItem = tbItemMapper.selectByPrimaryKey(id);
        jedisCluster.set("item_info:"+id+":base", JsonUtils.objectToJson(tbItem));
        jedisCluster.expire("item_info:"+id+":base", redis_itemInfo_expire);
        return tbItem;
    }

    @Override
    public TbItemDesc findItemDescById(long id) {
        String s = jedisCluster.get("item_info:" + id + ":desc");
        if (StringUtils.isNotBlank(s)){
            return JsonUtils.jsonToPojo(s,TbItemDesc.class);
        }
        TbItemDesc tbItemDesc = tbItemDescMapper.selectByPrimaryKey(id);
        jedisCluster.set("item_info:"+id+":desc", JsonUtils.objectToJson(tbItemDesc));
        jedisCluster.expire("item_info:"+id+":desc", redis_itemInfo_expire);
        return tbItemDesc;
    }

    @Override
    public DataGridResult findAll(int page ,int rows) {

        PageHelper.startPage(page,rows);
        TbItemExample example = new TbItemExample();
        List<TbItem> tbItems = tbItemMapper.selectByExample(example);
        DataGridResult dataGridResult = new DataGridResult();
        dataGridResult.setRows(tbItems);
        dataGridResult.setTotal((int) new PageInfo<>(tbItems).getTotal());
        return dataGridResult;
    }

    @Override
    public E3Result saveItem(TbItem tbItem, String desc) {
        //保存商品
        long itemId = IDUtils.genItemId();
        tbItem.setId(itemId);
        tbItem.setCreated(new Date());
        tbItem.setUpdated(new Date());
        //1正常 2 下架  3 删除
        tbItem.setStatus((byte) 1);
        tbItemMapper.insert(tbItem);
        //保存商品描述
        TbItemDesc tbItemDesc = new TbItemDesc();
        tbItemDesc.setItemId(itemId);
        tbItemDesc.setCreated(new Date());
        tbItemDesc.setItemDesc(desc);
        tbItemDesc.setUpdated(new Date());
        tbItemDescMapper.insert(tbItemDesc);
        new Thread(new Runnable() {
            @Override
            public void run() {
                jmsTemplate.send(topicDestination, new MessageCreator() {
                    @Override
                    public Message createMessage(Session session) throws JMSException {
                        TextMessage message = session.createTextMessage(tbItem.getId() + "");
                        return message;
                    }
                });
            }
        }).start();
        return E3Result.ok();
    }

    @Override
    public TbItemDesc updateitem(Long id) {
        TbItemDesc tbItemDesc = tbItemDescMapper.selectByPrimaryKey(id);
        return tbItemDesc;
    }

    @Override
    public E3Result updateitem(TbItem tbItem, String desc) {

        TbItem tbItem1 = tbItemMapper.selectByPrimaryKey(tbItem.getId());
        tbItem.setUpdated(new Date());
        tbItem.setCreated(tbItem1.getCreated());
        tbItem.setStatus(tbItem1.getStatus());
        tbItemMapper.updateByPrimaryKey(tbItem);
        TbItemDesc tbItemDesc1 = tbItemDescMapper.selectByPrimaryKey(tbItem.getId());
        TbItemDesc tbItemDesc = new TbItemDesc();
        tbItemDesc.setItemDesc(desc);
        tbItemDesc.setCreated(tbItemDesc1.getCreated());
        tbItemDesc.setUpdated(new Date());
        tbItemDesc.setItemId(tbItem1.getId());
        tbItemDescMapper.updateByPrimaryKey(tbItemDesc);
        return E3Result.ok();
    }

    @Override
    public E3Result deleteItem(String ids,Byte status) {
        if (null!=ids&&ids.trim().length()>0){
            String[] split = ids.split(",");
            for (String s : split) {
                TbItemExample tbItemExample = new TbItemExample();
                TbItem tbItem = new TbItem();
                long itemId = Long.parseLong(s);
                tbItemExample.createCriteria().andIdEqualTo(itemId);
                tbItem.setStatus(status);
                tbItem.setUpdated(new Date());
                tbItemMapper.updateByExampleSelective(tbItem,tbItemExample);
            }

        }
        return E3Result.ok();
    }

}
