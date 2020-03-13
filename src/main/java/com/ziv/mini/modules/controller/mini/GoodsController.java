package com.ziv.mini.modules.controller.mini;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.ziv.mini.common.response.JsonResult;
import com.ziv.mini.modules.entity.Goods;
import com.ziv.mini.modules.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 商品接口控制类
 *
 * @author ziv
 * @date 220-02-28
 */
@RestController
@RequestMapping(value = "mini/goods")
@Api(tags = {"商品接口"})
public class GoodsController {

    @Resource
    private GoodsService goodsService;

    @GetMapping(value = "page")
    @ApiOperation(value = "分页接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", dataType = "int", example = "1", required = true),
            @ApiImplicitParam(name = "pageSize", value = "每页显示数", dataType = "int", example = "10", required = true),
            @ApiImplicitParam(name = "searchKeyWord", value = "查询关键字", dataType = "string", example = "商品名称", required = false)
    })
    public JsonResult<PageInfo<Goods>> page(Integer pageNum, Integer pageSize, String searchKeyWord) {
        PageInfo<Goods> page = goodsService.page(pageNum, pageSize, searchKeyWord);
        return JsonResult.success(page);
    }

    @PostMapping
    @ApiOperation(value = "添加接口")
    public JsonResult insert(@RequestBody Goods goods) {
        goodsService.insert(goods);
        return JsonResult.success();
    }

    @PutMapping
    @ApiOperation(value = "更新接口")
    public JsonResult update(@RequestBody Goods goods) {
        goodsService.updateById(goods);
        return JsonResult.success();
    }

    @DeleteMapping
    @ApiOperation(value = "删除接口")
    public JsonResult update(Integer goodsId) {
        goodsService.deleteById(goodsId);
        return JsonResult.success();
    }
}
