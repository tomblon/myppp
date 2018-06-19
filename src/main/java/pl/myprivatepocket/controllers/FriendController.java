package pl.myprivatepocket.controllers;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import pl.myprivatepocket.models.converters.FriendConverter;
import pl.myprivatepocket.models.dto.FriendDto;
import pl.myprivatepocket.models.entity.FriendEntity;
import pl.myprivatepocket.models.entity.UserEntity;
import pl.myprivatepocket.repositories.FriendRepository;
import pl.myprivatepocket.repositories.UserDataRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/friends")
@Transactional
public class FriendController {

    @Autowired
    private FriendRepository friendRepository;

    @Autowired
    private UserDataRepository userDataRepository;

    @Autowired
    private FriendConverter friendConverter;

    @GetMapping
    @Transactional
    public ResponseEntity<?> getFriends() {
        return ResponseEntity.ok(friendConverter
                .convertFromEntities(Lists.newArrayList(
                        friendRepository.findAllByUserEntity(getUserEntity())
                        )
                ));
    }

    @GetMapping(path = "/{name}")
    public ResponseEntity<?> getFriendsByName(@PathVariable String name) throws NotFoundException {
        return ResponseEntity.ok(friendConverter
                .convertFromEntities(Lists.newArrayList(
                        friendRepository.findByNameAndUserEntity(name, getUserEntity())
                        )
                ));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> addFriend(@RequestBody FriendDto friendDto) throws IOException {
        Optional<FriendEntity> friendEntity = friendRepository.findByEmailAndUserEntity(friendDto.getEmail(), getUserEntity());
        if (friendEntity.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Friend already Added");
        }
        UserEntity userEntity = userDataRepository.findFirstByUserMail(friendDto.getEmail());
        FriendEntity newFriendEntity = friendConverter.convertFromDto(friendDto);
        newFriendEntity.setUserEntity(getUserEntity());
        friendRepository.save(newFriendEntity);
        return ResponseEntity.ok(friendConverter.convertFromEntity(newFriendEntity));
    }

    @DeleteMapping(path = "/{id}")
    @Transactional
    public ResponseEntity<?> deleteFriend(@PathVariable long id) {
//        Optional<FriendEntity> friendEntity = friendRepository.findById(id);
//        if(friendEntity.isPresent()){
//            UserEntity userEntity = friendEntity.get().getUserEntity();
//            if(userEntity.getId() == getUserEntity().getId()){
//                friendRepository.deleteById(id);
//                return ResponseEntity.ok("friend deleted");
//            }
//            else{
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("not your friend to manage");
//            }
//        }
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("no friend of given id");
        friendRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public UserEntity getUserEntity() {
        String userMail = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDataRepository.findFirstByUserMail(userMail);
    }

}
