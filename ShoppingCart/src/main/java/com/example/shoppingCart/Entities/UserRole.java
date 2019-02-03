package com.example.shoppingCart.Entities;

import com.example.shoppingCart.Entities.AppUser;
import com.example.shoppingCart.Entities.AppRole;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
 
 
@Entity
@Table(name="user_role")
public class UserRole implements Serializable {
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="id")
    private Long Id;
    
    @ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE}, targetEntity=AppRole.class )
    @JoinColumn(name = "role_id")
    private Long roleId;
    
    @ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE}, targetEntity=AppUser.class )
    @JoinColumn(name = "user_id")
    private Long userId;  
    
    public UserRole(Long roleId,Long userId)
    {
        this.roleId = roleId;
        this.userId = userId;
    }
}