package tads.eaj.br.locadora;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Size(min = 3, max = 30, message = ApiMensagens.TAMANHO_ERRADO)
    @NotBlank(message = ApiMensagens.CAMPO_VAZIO)
    String modelo;

    @NotBlank(message = ApiMensagens.CAMPO_VAZIO)
    String cor;

    @Min(value = 1500, message = "O ano de fabricação deve ser maior que 1500")
    @Max(value = 2020, message = "O ano de fabricação dever ser menor que 2020")
    @NotNull(message = ApiMensagens.CAMPO_VAZIO)
    Integer anoFabricacao;

    @Min(value = 2, message = "O carro de ter no mínimo 2 portas")
    @NotNull(message = ApiMensagens.CAMPO_VAZIO)
    Integer qtdPortas;

    @Min(value = 2, message = "O carro de ter no mínimo 2 lugares para sentar")
    @NotNull(message = ApiMensagens.CAMPO_VAZIO)
    Integer qtdLugares;
}
