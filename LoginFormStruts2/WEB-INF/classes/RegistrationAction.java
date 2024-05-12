import java.util.*;
import java.io.*;
import com.opensymphony.xwork2.ActionSupport;
public class RegistrationAction extends ActionSupport {

    private String email;
    private String password;
	

   
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Validate method
    @Override
    public void validate() {
       
        
        if (email == null || email.trim().isEmpty()) {
            addFieldError("email", "Email is required");
        } else if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            addFieldError("email", "Invalid email format");
        }else{// password check null or not start
			if (password == null || password.trim().isEmpty()) {
				addFieldError("password", "Password is required");
			}else{// user and passward matching code start
				
				try{
		
					Properties properties = new Properties();
					properties.load(new FileInputStream("D:/Tomcat/webapps/LoginFormStruts2/WEB-INF/classes/password.properties"));
						
					   
					String checkPassword = properties.getProperty(email);
					
					if(!(checkPassword==null)){
						if(password.equals(checkPassword)){
						
							System.out.println("LOgin Successfully done");
						}else{
							addFieldError("password", "Password not matching....");
						}
					}else{
						addFieldError("email", "Usre Name not matching....");
						
					}
				}catch(Exception e){
					e.printStackTrace();
				}
				
				
			}// user and passward matching code End
		}// password check null or not End
        
        
		
		
    }

    // Execute method
    public String execute() {
        // Code to process registration
        return SUCCESS;
    }
}