package br.com.balneariosoftware.controllers;

import br.com.balneariosoftware.exception.ResourceNotFoundException;
import br.com.balneariosoftware.model.Reserva;
import br.com.balneariosoftware.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reservas")
public class ReservaController {
    @Autowired
    private ReservaRepository reservaRepository;

    @GetMapping("/{id}")
    public Reserva reserva(@PathVariable("id") Long id) {
        Optional<Reserva> reservaFind = this.reservaRepository.findById(id);

        if(reservaFind.isEmpty()) {
            throw new ResourceNotFoundException("Reserva com o " + id + " não foi encontrado");
        }

        return reservaFind.get();
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Reserva reserva(@RequestBody Reserva reserva) {
        return this.reservaRepository.save(reserva);
    }

    @GetMapping("/list")
    public List<Reserva> list() {
        return this.reservaRepository.findAll();
    }
}
