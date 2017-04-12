package ru.kichenko.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.security.acls.model.MutableAcl;
import org.springframework.security.acls.model.Permission;

import ru.kichenko.model.UpdateUserDataDto;
import ru.kichenko.model.User;

public interface UserService {

    void edit(User user, UpdateUserDataDto data);
    
    List<User> findAllUsers();
    
    User findById(long id);
    
    MutableAcl addAclPermission2User(Serializable id, String principal, Permission p);
}
