package model;

public class forgotpasswordmodel {
    private String username;
    private String password;
    private String email;
    private int user_id;
    
    
public forgotpasswordmodel(String username, String email, String password){
    this.username = username;
    this.password = password;
    this.email = email;
}
    
    public void setusername(String username) {
        this.username = username;
    }
    public String getusername() {
        return username;
    }
    


public void setId(int user_id) {
    this.user_id = user_id;
}
public int getId() {
        return user_id;
    }

 public void setpassword(String password) {
        this.password = password;
    }
    public String getpassword() {
        return password;
    }


 public void setemail(String email) {
         this.email = email;
    }
    public String getemail() {
        return email;
    }  
}

