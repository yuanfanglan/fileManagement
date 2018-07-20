package com.yfl.service.interfaces;

import com.yfl.common.util.AjaxResult;

public interface HomeUserService {
 AjaxResult selectAllHomeUser() ;
 
 AjaxResult login(String userName, String password);
}
