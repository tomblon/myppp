package pl.myprivatepocket.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.myprivatepocket.models.converters.UserConverter;
import pl.myprivatepocket.models.dto.AuthenticationDto;
import pl.myprivatepocket.models.dto.AuthenticationResponseDto;
import pl.myprivatepocket.models.dto.UserDto;
import pl.myprivatepocket.models.entity.UserEntity;
import pl.myprivatepocket.repositories.UserDataRepository;
import pl.myprivatepocket.utils.TokenUtils;

import javax.validation.Valid;

@RestController()
@RequestMapping(path = "${jwt.route.authentication}")
public class AuthenticationController {

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDataRepository userDataRepository;

    @Autowired
    private UserConverter userConverter;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> login(@Valid @RequestBody AuthenticationDto authenticationDto) throws AuthenticationException {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationDto.getMail(),
                        authenticationDto.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenUtils.generateToken(authenticationDto.getMail());

        return ResponseEntity.ok(new AuthenticationResponseDto(token));
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> register(@Valid @RequestBody UserDto userDto) {
        UserEntity userEntity = userConverter.convertFromDto(userDto);
        userDataRepository.save(userEntity);
        return ResponseEntity.noContent().build();
    }


}
