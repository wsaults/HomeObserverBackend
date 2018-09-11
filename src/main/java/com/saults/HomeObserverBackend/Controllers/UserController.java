package com.saults.HomeObserverBackend.Controllers;

import com.saults.HomeObserverBackend.Entities.User;
import com.saults.HomeObserverBackend.Repositories.UserRepository;
import org.springframework.hateoas.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE, "application/hal+json"})
public class UserController {

    private final UserRepository repository;

    UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/users")
    List<User> all() {
        return repository.findAll();
    }

    @PostMapping("/users")
    User newUser(@RequestBody User newUser) {
        return repository.save(newUser);
    }

    // Old
//    @GetMapping("/users/{id}")
//    User one(@PathVariable Long id) throws UserNotFoundException {
//        return repository.findById(id)
//                .orElseThrow(() -> new UserNotFoundException(id));
//    }

    @GetMapping("/users/{id}")
    Resource<User> one(@PathVariable Long id) throws UserNotFoundException {
        User user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        return new Resource<>(user,
                linkTo(methodOn(UserController.class).one(id)).withSelfRel(),
                linkTo(methodOn(UserController.class).all()).withRel("users"));
    }

    @PutMapping("/users/{id}")
    User replaceUser(@RequestBody User newUser, @PathVariable Long id) {

        return repository.findById(id)
                .map(user -> {
                    user.setName(newUser.getName());
                    user.setRole(newUser.getRole());
                    return repository.save(user);
                })
                .orElseGet(() -> {
                    newUser.setId(id);
                    return repository.save(newUser);
                });
    }

    @DeleteMapping("users/{id}")
    void deleteUser(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
