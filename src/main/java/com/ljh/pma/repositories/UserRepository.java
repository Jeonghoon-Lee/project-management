package com.ljh.pma.repositories;

import com.ljh.pma.entities.UserAccount;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserAccount, Long> {

}
