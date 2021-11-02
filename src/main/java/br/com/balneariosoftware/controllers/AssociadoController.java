package br.com.balneariosoftware.controllers;

import br.com.balneariosoftware.exception.IsFuncionario;
import br.com.balneariosoftware.exception.ResourceAlreadyExist;
import br.com.balneariosoftware.exception.ResourceNotFoundException;
import br.com.balneariosoftware.exception.ValueOnInsertNotFound;
import br.com.balneariosoftware.model.*;
import br.com.balneariosoftware.repository.AssociadoRepository;
import br.com.balneariosoftware.repository.CarteirinhaRepository;
import br.com.balneariosoftware.repository.ReservaRepository;
import br.com.balneariosoftware.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/associados")
public class AssociadoController {

    @Autowired
    private AssociadoRepository associadoRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarteirinhaRepository carteirinhaRepository;

    @Autowired
    private ReservaRepository reservaRepository;

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
        if(!userRepository.existsById(associado.getUsuarioId())) {
            throw new ValueOnInsertNotFound("Usuário não foi encontrado");
        }

        if(associadoRepository.userJaAssociado(associado.getUsuarioId())) {
            throw new ResourceAlreadyExist("Usuário já é um associado");
        }

        if(associadoRepository.associadoEFuncionario(associado.getUsuarioId())) {
            throw new IsFuncionario("Este usuário não poder ser um associado, pois é um funcionário!");
        }

        return this.associadoRepository.save(associado);
    }

    @GetMapping("/list")
    public List<Associado> list() {
        return this.associadoRepository.findAll();
    }

    @PostMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void removeUser(@PathVariable("id") Long id) {
        Optional<Associado> remove = associadoRepository.findById(id);

        if(remove.isEmpty()) {
            throw new ResourceNotFoundException("Usuário " + id + " não encontrado");
        }

        Carteirinha carteirinha = carteirinhaRepository.searchCarteirinhaByUserId(id);
        List<Reserva> reservas = reservaRepository.findAll();

        if(carteirinha != null) {
            carteirinhaRepository.delete(carteirinha);
        }

        if(!reservas.isEmpty()) {
            for (Reserva reserva : reservas) {
                if(Objects.equals(reserva.getSolicitanteId(), id)) {
                    reserva.setAtivo(false);
                    reservaRepository.save(reserva);
                }
            }
        }

        Optional<User> removeUser = userRepository.findById(remove.get().getUsuarioId());

        associadoRepository.delete(remove.get());
        userRepository.delete(removeUser.get());
    }
}
