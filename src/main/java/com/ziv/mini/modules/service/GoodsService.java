package com.ziv.mini.modules.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ziv.mini.common.response.JsonResult;
import com.ziv.mini.modules.dao.GoodsMapper;
import com.ziv.mini.modules.entity.Goods;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 商品业务逻辑类
 *
 * @author ziv
 * @date 2020-02-28
 */
@Service
public class GoodsService extends ServiceImpl<GoodsMapper, Goods> {

    @Resource
    private GoodsMapper goodsMapper;

    /**
     * 查询商品分页
     * @param pageNum 页码
     * @param pageSize 每页显示数
     * @param searchKeyWord 查询关键字
     * @return
     */
    public PageInfo<Goods> page(Integer pageNum, Integer pageSize, String searchKeyWord) {
        Page<Goods> page = PageHelper.startPage(pageNum, pageSize);
        goodsMapper.page(searchKeyWord);
        return new PageInfo<Goods>(page);

    }

    /**
     * 添加商品
     * @param goods 商品信息
     */
    public void insert(Goods goods) {
        goods.setCreateTime(new Date());
        goods.setUpdateTime(new Date());
        goodsMapper.insert(goods);
    }

    /**
     * 添加商品
     * @param goodsId
     */
    public void deleteById(Integer goodsId) {
        goodsMapper.deleteById(goodsId);
    }
}
