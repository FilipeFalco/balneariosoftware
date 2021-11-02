package br.com.balneariosoftware.controllers;

import br.com.balneariosoftware.exception.ResourceNotFoundException;
import br.com.balneariosoftware.model.User;
import br.com.balneariosoftware.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
    public User user(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "dd.MM.yyyy") @RequestBody User user) {
        User userFind = this.userRepository.findCpf(user.getCpf());

        if(userFind != null) {
            userFind.setNome(user.getNome());
            userFind.setEmail(user.getEmail());
            userFind.setData_nascimento(user.getData_nascimento());
            userFind.setUpdate_at(new Date());

            return this.userRepository.save(userFind);
        } else {
            return this.userRepository.save(user);
        }
    }

    @GetMapping("/list")
    public List<User> list() {
        return this.userRepository.findAll();
    }

    @PostMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void removeUser(@PathVariable("id") Long id) {
        Optional<User> remove = userRepository.findById(id);

        if(remove.isEmpty()) {
            throw new ResourceNotFoundException("Usuário " + id + " não encontrado");
        }

        userRepository.delete(remove.get());
    }
}
