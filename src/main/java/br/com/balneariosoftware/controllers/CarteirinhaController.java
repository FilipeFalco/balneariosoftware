package br.com.balneariosoftware.controllers;

import br.com.balneariosoftware.exception.*;
import br.com.balneariosoftware.model.Carteirinha;
import br.com.balneariosoftware.model.Funcionario;
import br.com.balneariosoftware.model.User;
import br.com.balneariosoftware.repository.CarteirinhaRepository;
import br.com.balneariosoftware.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/carteirinhas")
public class CarteirinhaController {

    @Autowired
    private CarteirinhaRepository carteirinhaRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{id}")
    public Carteirinha carteirinha(@PathVariable("id") Long id) {
        Optional<Carteirinha> carteirinhaFind = this.carteirinhaRepository.findById(id);

        if (carteirinhaFind.isEmpty()) {
            throw new ResourceNotFoundException("Carteirinha com o " + id + " não foi encontrado");
        }

        return carteirinhaFind.get();
    }

    @PostMapping("/")
    public Carteirinha carteirinha(@RequestBody Carteirinha carteirinha) {
        if(carteirinha.getUsuarioId() == null) {
            throw new ResourceRequired("Usuário é obrigatório");
        }

        Optional<User> user = userRepository.findById(carteirinha.getUsuarioId());

        if(user.isEmpty()) {
            throw new ResourceNotFoundException("Usuário não encontrado");
        }

        if(carteirinhaRepository.associadoJaPossuiCarteirinha(carteirinha.getUsuarioId())) {
            throw new ResourceAlreadyExist("Associado já possui uma carteirinha!");
        }

        if(carteirinhaRepository.userEFuncionario(carteirinha.getUsuarioId())) {
            throw new IsFuncionario("Funcionários não podem possuir carteirinha!");
        }

        String idCarteirinha = String.format("%010d", new BigInteger(UUID.randomUUID().toString().replace("-", ""), 16));

        carteirinha.setId_carteirinha(idCarteirinha);
        return this.carteirinhaRepository.save(carteirinha);
    }

    @GetMapping("/list")
    public List<Carteirinha> list() {
        return this.carteirinhaRepository.listAllAtivo();
    }

    @PostMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void removeUser(@PathVariable("id") Long id) {
        Carteirinha remove = carteirinhaRepository.getById(id);

        if(remove.getId() == null) {
            throw new ResourceNotFoundException("Usuário " + id + " não encontrado");
        }

        remove.setAtivo(false);
        carteirinhaRepository.save(remove);
    }
}
