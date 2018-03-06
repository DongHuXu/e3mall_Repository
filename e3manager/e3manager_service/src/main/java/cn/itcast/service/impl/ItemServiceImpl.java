package cn.itcast.service.impl;

import cn.itcast.DataGridResult;
import cn.itcast.mapper.TbItemMapper;
import cn.itcast.pojo.TbItem;
import cn.itcast.service.ItemService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    TbItemMapper tbItemMapper;
    @Override
    public TbItem findTbItemById(long id) {
        return tbItemMapper.selectByPrimaryKey(id);
    }

    @Override
    public DataGridResult findAll(int page ,int rows) {

        PageHelper.startPage(page,rows);
        List<TbItem> tbItems = tbItemMapper.selectAll();
        DataGridResult dataGridResult = new DataGridResult();
        dataGridResult.setRows(tbItems);
        dataGridResult.setTotal((int) new PageInfo<>(tbItems).getTotal());
        return dataGridResult;
    }
}
