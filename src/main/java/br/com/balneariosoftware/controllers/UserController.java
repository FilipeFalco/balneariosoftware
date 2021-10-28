package br.com.balneariosoftware.controllers;

import br.com.balneariosoftware.exception.ResourceNotFoundException;
import br.com.balneariosoftware.model.User;
import br.com.balneariosoftware.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{id}")
    public User user(@PathVariable("id") Long id) {
        Optional<User> userFind = this.userRepository.findById(id);

        if (userFind.isEmpty()) {
            throw new ResourceNotFoundException("Usuário " + id + " não encontrado");
        }

        return userFind.get();
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public User user(@RequestBody User user) {
        return this.userRepository.save(user);
    }

    @GetMapping("/list")
    public List<User> list() {
        return this.userRepository.findAll();
    }

    @GetMapping("/list/{id}")
    public List<User> listMoreThan(@PathVariable("id") Long id) {
        return this.userRepository.findAllMoreThan(id);
    }
}
