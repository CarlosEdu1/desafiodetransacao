package desafio.spring.desafiodetransacao.controller;

import desafio.spring.desafiodetransacao.dto.TransactionRequest;
import desafio.spring.desafiodetransacao.model.Transaction;
import desafio.spring.desafiodetransacao.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;

@RestController
@RequestMapping("/transacao")
public class TransacationController {

    private final TransactionService transactionService;


    public TransacationController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<Void> createTransaction(@Valid @RequestBody TransactionRequest request) {
        if (request.getDataHora().isAfter(OffsetDateTime.now()) || request.getValor()<=0) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_CONTENT).build();
        }
        transactionService.addTransaction(new Transaction(request.getValor(), request.getDataHora()));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> clearTransaction() {

        transactionService.clearTransactions();
        return ResponseEntity.ok().build();
    }
}
