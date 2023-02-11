package org.itstep.mvcapp.service;

import org.itstep.mvcapp.model.User;
import org.itstep.mvcapp.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User getUserById(long id) {

       Optional<User> optionalUser = userRepository.findById(id);
       User user = null;
       if (optionalUser.isPresent())
       {
           user = optionalUser.get();
       }
       else {
           System.out.println("User not found with id = " + id);
           throw new RuntimeException("User not found with id = " + id);
       }

       return user;
    }

    @Override
    public void deleteUserById(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Page<User> pagination(int page, int size, String sortByField, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("ASC") ? Sort.by(sortByField).ascending() : Sort.by(sortByField).descending();

        Pageable pageable = PageRequest.of(page - 1, size, sort);
        var ccc = userRepository.findAll(pageable);

        return ccc;

    }
}
