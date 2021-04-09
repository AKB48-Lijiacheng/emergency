package com.westcatr.emergency.business.docking.JingxinPlatformDocking.service;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.westcatr.rd.base.acommon.domain.IUser;
import com.westcatr.rd.base.authority.authority.cache.TokenCache;
import com.westcatr.rd.base.authority.authority.domain.LoginDTO;
import com.westcatr.rd.base.authority.authority.properties.ISecurityProperties;
import com.westcatr.rd.base.authority.authority.provider.UserAndPermissionProvider;
import com.westcatr.rd.base.authority.entity.LoginLog;
import com.westcatr.rd.base.authority.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

/**
 * @author lijiacheng
 * @Date 2021/4/7
 */
@Service
public class SsoService {
    @Autowired
    UserAndPermissionProvider userAndPermissionProvider;
    @Autowired
    protected TokenCache tokenCache;
    @Autowired
    protected LoginLogService loginLogService;
    @Autowired
    protected ISecurityProperties iSecurityProperties;


    public String LoginAndgetToken(LoginDTO dto) {

        IUser iUser = userAndPermissionProvider.getByUsername(dto.getUsername());
        long time = iSecurityProperties.getNoSaveTime();
        if (dto.getSaveLogin()){
            time = iSecurityProperties.getSaveTime();
        }
        iUser.setClientCode(dto.getClientCode());
        String newToken = SecureUtil.md5(UUID.randomUUID().toString() + new Date());
        String oldToken = tokenCache.getClientOnlineToken(iUser.getId() + "", dto.getClientCode());
        if (StrUtil.isNotBlank(oldToken)) {
            if (iSecurityProperties.isManyLogin()) {
                tokenCache.setClientOnlineToken(iUser.getId() + "", dto.getClientCode(), oldToken, time);
                tokenCache.setUser(oldToken, iUser, time);
                return oldToken;
            } else {
                tokenCache.deleteUser(oldToken);
                tokenCache.deleteUserAndPermission(oldToken);
            }
        }
        tokenCache.setClientOnlineToken(iUser.getId() + "", dto.getClientCode(), newToken, time);
        tokenCache.setUser(newToken, iUser, time);
        LoginLog loginLog = new LoginLog();
        loginLog.setUsername(dto.getUsername());
        loginLog.setUserId(iUser.getId());
        loginLog.setClientCode(dto.getClientCode());
        loginLogService.isave(loginLog);
//todo   HttpContextUtil.getHttpServletResponse().setHeader("token", token);//为什么不生效
        return newToken;
    }
}
