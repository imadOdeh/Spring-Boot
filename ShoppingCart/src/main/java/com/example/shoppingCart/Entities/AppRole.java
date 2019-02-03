package com.example.shoppingCart.Entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
 
 
@Entity
@Table(name="app_role")
public class AppRole implements Serializable {
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="role_id")
    private Long roleId;
    
    @Column(name = "role_name")
    private String roleName;
    
    public AppRole(String roleName)
    {
        this.roleName = roleName;
    }
    
    public Long getRoleId() {
        return roleId;
    }
 
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
 
    public String getRoleName() {
        return roleName;
    }
 
    public void setUserName(String roleName) {
        this.roleName = roleName;
    }
    
    @Override
    public String toString() {
        return this.roleName;
    }
     
}