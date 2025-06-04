package org.lessons.java.manga.manga.security;

import java.util.Optional;

import org.lessons.java.manga.manga.model.User;
import org.lessons.java.manga.manga.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DatabaseUserDetailsService implements UserDetailsService{
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public DatabaseUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return new DatabaseUserDetails(user);
        } else {
            throw new UsernameNotFoundException("User non trovato");
        }
    }
}
