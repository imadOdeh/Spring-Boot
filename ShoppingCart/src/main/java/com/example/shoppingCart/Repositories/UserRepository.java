package com.example.shoppingCart.Repositories;

import com.example.shoppingCart.Entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<AppUser,Long> {
    
    @Query("SELECT u FROM AppUser u  WHERE userName = :userName")
    AppUser findByUserName(@Param("userName") String userName); 
}
