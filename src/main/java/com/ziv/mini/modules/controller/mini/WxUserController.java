package com.ziv.mini.modules.controller.mini;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.ziv.mini.common.config.WxMaConfiguration;
import com.ziv.mini.common.response.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"小程序用户接口"})
@RestController
@RequestMapping(value = "mini/user")
public class WxUserController {

    @ApiOperation(value = "获取用户微信开放信息")
    @GetMapping(value = "getSessionInfo")
    public JsonResult getSessionInfo(String code) throws WxErrorException {
        String appId = "wxad89b801ac9479ad";

        final WxMaService wxService = WxMaConfiguration.getMaService();
        WxMaJscode2SessionResult result = wxService.getUserService().getSessionInfo(code);
        return JsonResult.success(result);
    }
}
