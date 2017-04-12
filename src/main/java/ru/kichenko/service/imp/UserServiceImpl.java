package ru.kichenko.service.imp;

import java.io.Serializable;
import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.model.MutableAcl;
import org.springframework.security.acls.model.MutableAclService;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.security.acls.model.ObjectIdentity;
import org.springframework.security.acls.model.Permission;
import org.springframework.security.acls.model.Sid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.kichenko.model.UpdateUserDataDto;
import ru.kichenko.model.User;
import ru.kichenko.repository.UserRepository;
import ru.kichenko.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MutableAclService aclService;

    @Override
    @RolesAllowed("ADMIN")
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Secured("ROLE_RUN_AS_SUPER_ADMIN")
    public User findById(long id) {
        return userRepository.findOne(id);
    }
    
    @Override
    @PreAuthorize("hasPermission(#user, write)")
    public void edit(User user, UpdateUserDataDto data) {
        user.setFirstName(data.getFirstName());
        user.setSecondName(data.getSecondName());
        user.setEmail(data.getEmail());
        userRepository.save(user);
    }
    
    @Override
    @Transactional
    public MutableAcl addAclPermission2User(Serializable id, String principal, Permission p) {

        ObjectIdentity oi = new ObjectIdentityImpl(User.class, id);
        Sid sid = new PrincipalSid(principal);

        MutableAcl acl = null;
        try {
            acl = (MutableAcl) aclService.readAclById(oi);
        } catch (NotFoundException nfe) {
            acl = aclService.createAcl(oi);
        }
        acl.insertAce(acl.getEntries().size(), p, sid, true);
        return aclService.updateAcl(acl);
    }
}
