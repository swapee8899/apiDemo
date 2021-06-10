package resources;

import pojo.LoginPojo;
import pojo.SessionIdPojo;

public class TestDataBuild {
	
	public LoginPojo  loginPayload(String email, String password) {
		LoginPojo loginpojo = new LoginPojo();
		loginpojo.setMail(email);
		loginpojo.setPassword(password);
		return loginpojo;
	}
	public SessionIdPojo sessionPayload(String sessionid) {
		SessionIdPojo logoutpojo = new SessionIdPojo();
		logoutpojo.setSessionId(sessionid);
		return logoutpojo;
	}

}
