package com.anz.banking.accountdetails.repository;

import com.anz.banking.accountdetails.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface IUserRepository extends CrudRepository<User, Long> {
}
