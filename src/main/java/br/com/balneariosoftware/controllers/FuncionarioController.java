package br.com.balneariosoftware.controllers;

import br.com.balneariosoftware.exception.LengthPassword;
import br.com.balneariosoftware.exception.ResourceAlreadyExist;
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

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/{id}")
    public Funcionario funcionario(@PathVariable("id") Long id) {
        Optional<Funcionario> funcionarioFind = this.funcionarioRepository.findById(id);

        if (funcionarioFind.isPresent()) {
            throw new ResourceNotFoundException("Funcionário com o " + id + " não foi encontrado");
        }

        return funcionarioFind.get();
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Funcionario funcionario(@RequestBody Funcionario funcionario) {

        Funcionario funcionarioEditar = funcionarioRepository.searchFunciorioByUserId(funcionario.getUsuarioId());
        if(funcionarioEditar != null) {
            funcionarioEditar.setPassword(funcionario.getPassword());
            funcionarioEditar.setLiberador_acesso(funcionario.getLiberador_acesso());

            return this.funcionarioRepository.save(funcionarioEditar);
        }

        if (!userRepository.existsById(funcionario.getUsuarioId())) {
            throw new ValueOnInsertNotFound("Usuário não " + funcionario.getUsuarioId() + " foi encontrado");
        }


        if (funcionario.getPassword().length() < 4) {
            throw new LengthPassword("Senha deve conter no mínimo 4 caracteres");
        }

        return this.funcionarioRepository.save(funcionario);
    }

    @GetMapping("/list")
    public List<Funcionario> list() {
        return this.funcionarioRepository.findAll();
    }

    @PostMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void removeUser(@PathVariable("id") Long id) {
        Optional<Funcionario> remove = funcionarioRepository.findById(id);

        if(remove.isEmpty()) {
            throw new ResourceNotFoundException("Usuário " + id + " não encontrado");
        }

        Optional<User> removeUser = userRepository.findById(remove.get().getUsuarioId());

        funcionarioRepository.delete(remove.get());
        userRepository.delete(removeUser.get());
    }
}
