package br.com.balneariosoftware.controllers;

import br.com.balneariosoftware.exception.*;
import br.com.balneariosoftware.model.Reserva;
import br.com.balneariosoftware.repository.AssociadoRepository;
import br.com.balneariosoftware.repository.FuncionarioRepository;
import br.com.balneariosoftware.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    public Reserva reserva(@RequestBody Reserva reserva) throws ParseException {

        if(reserva.getLocal().isEmpty() || reserva.getLocal() == null) {
            throw new ResourceRequired("O local de reserva é obrigatório!");
        }

        if(reservaRepository.localReservado(reserva.getLocal(), reserva.getData_reservada())) {
            throw new DateAlreadyReserved("Este loca, nesta data, já esta reservada!");
        }

        if(reserva.getData_reservada() == null) {
            throw new ResourceRequired("Data de reserva é obrigatório!");
        }

        if(!comparaDatas(reserva.getData_reservada())) {
            throw  new DateIsBefore("Datas antes da data atual não são permitidas");
        }

        if(reserva.getSolicitanteId() == null) {
            throw new ResourceRequired("Usuário é obrigatório!");
        } else {
            if (associadoRepository.userJaAssociado(reserva.getSolicitanteId())) {
                throw new ResourceNotFoundException("Associado não encontrado!");
            }
        }

        if(reserva.getAutorizadorId() == null) {
            throw new ResourceRequired("Funcionário é obrigatório!");
        } else {
            if(!funcionarioRepository.funcionarioExistsByUserId(reserva.getAutorizadorId())) {
                throw new ResourceNotFoundException("Funcionário não encontrado!");
            }
        }

        return this.reservaRepository.save(reserva);
    }

    @GetMapping("/list")
    public List<Reserva> list() {
        return this.reservaRepository.listAllAtivo();
    }

    @PostMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void removerReserva(@PathVariable("id") Long id) {
        Reserva remove = reservaRepository.getById(id);

        if(remove.getId() == null) {
            throw new ResourceNotFoundException("Id não encontrado");
        }

        remove.setAtivo(false);
        reservaRepository.save(remove);
    }

    public boolean comparaDatas(Date dataInformada) throws ParseException {
        SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dateAtual = dataFormat.format(new Date());
        String dataInform = dataFormat.format(dataInformada);

        Date data1 = dataFormat.parse(dateAtual);
        Date data2 = dataFormat.parse(dataInform);

        if(data2.before(data1)) {
            return false;
        } else {
            return true;
        }
    }
}
