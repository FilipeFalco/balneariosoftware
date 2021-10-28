package br.com.balneariosoftware.controllers;

import br.com.balneariosoftware.exception.ResourceNotFoundException;
import br.com.balneariosoftware.model.Carteirinha;
import br.com.balneariosoftware.repository.CarteirinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/carteirinhas")
public class CarteirinhaController {

    @Autowired
    private CarteirinhaRepository carteirinhaRepository;

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
        return this.carteirinhaRepository.save(carteirinha);
    }

    @GetMapping("/list")
    public List<Carteirinha> list() {
        return this.carteirinhaRepository.findAll();
    }
}
