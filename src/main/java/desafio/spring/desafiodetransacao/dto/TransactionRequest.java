package desafio.spring.desafiodetransacao.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.OffsetDateTime;

public class TransactionRequest {

    @NotNull
    @Min(0)
    private Double valor;

    @NotNull
    private OffsetDateTime dataHora;

   public OffsetDateTime getDataHora(){
       return dataHora;
   }
   public Double getValor(){
       return valor;
   }

}
