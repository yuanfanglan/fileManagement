package com.yfl.common.util;

/**  
 * @Description:该类为用于添加samba用户所做一些操作的工具类   
 * @author yfl
 * @param  
 * @date 2018年7月13日  
 */
public class AddSambaUser {
    
	/**
	 * linux下执行添加samba用户所执行的一系列操作
	 * @param userName samba账户
	 * @param password samba密码
	 * */
	public void addSambaUser(String userName,String password) {
		try {
			if (userName!=null&&userName!="") {
				String[] cmd= new String[2];
				//添加用户的同时为用户创建家目录
				cmd[0]="useradd -d /mysamba/user/"+userName+" -m  "+userName;
				cmd[1]="mkdir -p /mysamba/user/"+userName+"/照片";
				for(int i=0;i<cmd.length;i++) {
					String[] cmd2= { "/bin/sh", "-c", cmd[i] };
					System.out.println(cmd2);
					Runtime.getRuntime().exec(cmd2);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
