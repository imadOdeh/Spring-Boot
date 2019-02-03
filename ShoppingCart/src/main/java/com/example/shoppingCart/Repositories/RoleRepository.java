package com.example.shoppingCart.Repositories;

import com.example.shoppingCart.Entities.AppRole;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<AppRole,Long> {
    
    @Query(value="select r.role_name from user_role ur, app_role r where ur.role_id = r.role_id and ur.user_id = :userId",nativeQuery=true)
    List<String> getRoleNames(@Param("userId") Long userId); 
}
