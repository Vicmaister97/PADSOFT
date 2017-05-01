package users;
public abstract class User implements java.io.Serializable{

	private static final long serialVersionUID = 3249562846791024636L;
	private String username;
	private String password;
	
	protected User(String username, String password){
		this.username=username;
		this.password=password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString(){
		return this.username + ' ' + this.password;
	}
	
}
