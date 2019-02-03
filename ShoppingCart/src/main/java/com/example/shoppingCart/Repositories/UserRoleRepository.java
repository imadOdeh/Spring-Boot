package com.example.shoppingCart.Repositories;

import com.example.shoppingCart.Entities.UserRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends CrudRepository<UserRole,Long> { 
}
