package br.com.balneariosoftware.controllers;

import br.com.balneariosoftware.exception.ResourceNotFoundException;
import br.com.balneariosoftware.model.Associado;
import br.com.balneariosoftware.repository.AssociadoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/associados")
public class AssociadoController {

    private AssociadoRepository associadoRepository;

    @GetMapping("/{id}")
    public Associado associado(@PathVariable("id") Long id) {
        Optional<Associado> associadoFind = this.associadoRepository.findById(id);

        if(associadoFind.isEmpty()) {
            throw new ResourceNotFoundException("Associado com o " + id + " não foi encontrado");
        }

        return associadoFind.get();
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Associado associado(@RequestBody Associado associado) {
        return this.associadoRepository.save(associado);
    }

    @GetMapping("/list")
    public List<Associado> list() {
        return this.associadoRepository.findAll();
    }
}
