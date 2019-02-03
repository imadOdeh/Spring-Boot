
package com.example.shoppingCart.Models;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class Register {
    
    public Register()
    {
        username ="";
        password = "";
        repeate_password = "";
    }
    
    @NotNull
    @Email()
    private String username;
    
    @NotNull
    @Min(8)
    private String password;
    
    @NotNull
    @Min(8)
    private String repeate_password;
    
    public String getUsername()
    {
        return username;
    }
    
    public String getPassword()
    {
        return password;
    }
    
    public String getRepeate_password()
    {
        return repeate_password;
    }
    
    public void setUsername(String username)
    {
        this.username= username;
    }
    
    public void setPassword(String password)
    {
        this.password = password;
    }
    
    public void setRepeate_password(String repeate_password)
    {
        this.repeate_password = repeate_password;
    }
}
