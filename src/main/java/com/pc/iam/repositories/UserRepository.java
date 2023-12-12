package com.pc.iam.repositories;

import com.pc.iam.helpers.RefreshableCRUDRepository;
import com.pc.iam.entities.UserInfo;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends RefreshableCRUDRepository<UserInfo, Long> {

   public UserInfo findByUsername(String username);
   UserInfo findFirstById(Long id);

}
