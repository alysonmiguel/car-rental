package tads.eaj.br.locadora;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarroRepository extends JpaRepository<Carro, Long> {
    List<Carro> findByAnoFabricacao(Integer ano);
}
