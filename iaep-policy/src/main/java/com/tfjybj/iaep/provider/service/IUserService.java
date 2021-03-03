package com.tfjybj.iaep.provider.service;

import com.tfjybj.iaep.model.user.CompanyOrganizationModel;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author 孙彤
 * @Date 2021/1/2217:12
 * @Version 1.0
 */
@Service
public interface IUserService {
    List<CompanyOrganizationModel> queryOrganizationUser ();
}
