package br.com.balneariosoftware.controllers;

import br.com.balneariosoftware.exception.ResourceNotFoundException;
import br.com.balneariosoftware.exception.resourceRequired;
import br.com.balneariosoftware.exception.userRequired;
import br.com.balneariosoftware.model.Reserva;
import br.com.balneariosoftware.repository.AssociadoRepository;
import br.com.balneariosoftware.repository.FuncionarioRepository;
import br.com.balneariosoftware.repository.ReservaRepository;
import br.com.balneariosoftware.repository.UserRepository;
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

    @Autowired
    private AssociadoRepository associadoRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

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

        if(reserva.getLocal().isEmpty() || reserva.getLocal() == null) {
            throw new resourceRequired("O local de reserva é obrigatório!");
        }

        if(reserva.getData_reservada() == null) {
            throw new resourceRequired("Data de reserva é obrigatório!");
        }

        if(reserva.getSolicitanteId() != null) {
            if (!associadoRepository.userJaAssociado(reserva.getSolicitanteId())) {
                throw new ResourceNotFoundException("Associado não encontrado!");
            }

            throw new userRequired("Usuário é obrigatório!");
        }

        if(reserva.getAutorizadorId() != null) {
            if(!funcionarioRepository.funcionarioExistsByUserId(reserva.getAutorizadorId())) {
                throw new ResourceNotFoundException("Funcionário não encontrado!");
            }

            throw new resourceRequired("Funcionário é obrigatório!");
        }

        return this.reservaRepository.save(reserva);
    }

    @GetMapping("/list")
    public List<Reserva> list() {
        return this.reservaRepository.findAll();
    }
}
