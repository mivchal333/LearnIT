package pb.edu.pl.krysiukm.learnit.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pb.edu.pl.krysiukm.learnit.controller.exception.UserAlreadyExistException;
import pb.edu.pl.krysiukm.learnit.dto.UserDto;
import pb.edu.pl.krysiukm.learnit.entity.User;
import pb.edu.pl.krysiukm.learnit.service.IUserService;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RestController
public class UserController {

    private final IUserService userService;

    @PostMapping("/user/registration")
    public ResponseEntity registerUserAccount(@Valid @RequestBody UserDto userDto) {
        try {
            User registered = userService.registerNewUserAccount(userDto);
        } catch (UserAlreadyExistException uaeEx) {
            return ResponseEntity.badRequest().body("An account for that username/email already exists.");
        }
        return ResponseEntity.ok().build();
    }
}
