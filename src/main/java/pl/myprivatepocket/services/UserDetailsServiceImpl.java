package pl.myprivatepocket.services;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.myprivatepocket.models.entity.UserEntity;
import pl.myprivatepocket.repositories.UserDataRepository;

import static java.util.Collections.emptyList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserDataRepository userDataRepository;

    public UserDetailsServiceImpl(UserDataRepository userDataRepository) {
        this.userDataRepository = userDataRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userMail) throws UsernameNotFoundException {
        UserEntity applicationUser = userDataRepository.findFirstByUserMail(userMail);
        if (applicationUser == null) {
            throw new UsernameNotFoundException(userMail);
        }
        return new User(applicationUser.getUserMail(), applicationUser.getPassword(), emptyList());
    }
}
