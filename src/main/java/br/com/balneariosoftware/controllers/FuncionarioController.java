package br.com.balneariosoftware.controllers;

import br.com.balneariosoftware.exception.ResourceNotFoundException;
import br.com.balneariosoftware.exception.ValueOnInsertNotFound;
import br.com.balneariosoftware.model.Funcionario;
import br.com.balneariosoftware.model.User;
import br.com.balneariosoftware.repository.FuncionarioRepository;
import br.com.balneariosoftware.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    private UserRepository userRepository;


    @GetMapping("/{id}")
    public Funcionario funcionario(@PathVariable("id") Long id) {
        Optional<Funcionario> funcionarioFind = this.funcionarioRepository.findById(id);

        if(funcionarioFind.isPresent()) {
            throw new ResourceNotFoundException("Funcionário com o " + id + " não foi encontrado");
        }

        return funcionarioFind.get();
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Funcionario funcionario(@RequestBody Funcionario funcionario) {
        funcionario.setCreated_at(new Date());

        try {
            Optional<User> verificaExiste = userRepository.findById(funcionario.getUsuarioId());

        } catch (Exception e) {
            throw new ValueOnInsertNotFound("Usuário não foi encontrado");
        }

        return this.funcionarioRepository.save(funcionario);
    }

    public List<Funcionario> list() {
        return this.funcionarioRepository.findAll();
    }
}
