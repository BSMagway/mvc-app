package org.itstep.mvcapp.service;

import org.itstep.mvcapp.model.User;
import org.springframework.data.domain.Page;

import java.util.List;


public interface UserService {

    List<User> getAllUsers();
    void saveUser(User user);
    User getUserById(long id);
    void deleteUserById(long id);

    Page<User> pagination(int page, int size, String sortByField, String sortDir);



}
