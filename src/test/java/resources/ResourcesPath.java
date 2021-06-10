package resources;

public enum ResourcesPath {
	
     LoginAPI("api/fr/login"),
     LogoutAPI("api/fr/logout"),
     PasswordPolicyAPI("api/fr/passwordPolicy"),
     GetProfileAPI("api/fr/getProfile");
	
	String APIpath;
	ResourcesPath(String APIpath){
		this.APIpath = APIpath;
	}
	
	public String getResource() {
		return APIpath;
		
	}
}
