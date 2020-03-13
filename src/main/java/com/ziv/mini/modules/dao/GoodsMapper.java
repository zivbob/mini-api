package com.ziv.mini.modules.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ziv.mini.modules.entity.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品数据持久层接口
 *
 * @author ziv
 * @date 2020-02-28
 */
@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {

    /**
     * 查询商品分页
     * @param searchKeyWord 查询关键字
     * @return List<Goods>
     */
    List<Goods> page(@Param("searchKeyWord") String searchKeyWord);
}
