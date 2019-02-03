package com.example.shoppingCart;

import com.example.shoppingCart.Entities.AppUser;
import com.example.shoppingCart.Repositories.UserRepository;
import com.example.shoppingCart.Repositories.RoleRepository;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RoleRepository roleRepository;

//   @PersistenceContext
//    private EntityManager entitymanager; 

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        AppUser appUser = userRepository.findByUserName(userName);

        if (appUser == null) {
            System.out.println("User not found! " + userName);
            throw new UsernameNotFoundException("User " + userName + " was not found in the database");
        }

        System.out.println("Found User: " + appUser);

        // [ROLE_USER, ROLE_ADMIN,..]
        List<String> roleNames =  roleRepository.getRoleNames(appUser.getUserId());
        
        for(String rn : roleNames)
            System.out.println("role name: " + rn);

        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        if (roleNames != null) {
            for (String role : roleNames) {
                // ROLE_USER, ROLE_ADMIN,..
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                grantList.add(authority);
            }
        }

        UserDetails userDetails = (UserDetails) new User(appUser.getUserName(), //
                appUser.getEncrytedPassword(), grantList);

        return userDetails;
    }
    
//    public List<String> getRoleNames(Long userId) {
//        String sql = "Select r.roleName " //
//                + " from UserRole ur, AppRole r " //
//                + " where ur.roleId = r.roleId and ur.userId = :userId ";
// 
//        Object[] params = new Object[] { userId };
// 
//        Query query = entitymanager.createQuery(sql);
//        query.setParameter("userId", userId);
//
//        return query.getResultList();
//    }

}
