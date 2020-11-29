package tads.eaj.br.locadora;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarroService {

    private CarroRepository repository;

    @Autowired
    public void setRepository(CarroRepository repository) {
        this.repository = repository;
    }

    public List<Carro> findAll(){
        return  repository.findAll();
    }

    public void save(Carro c){
        repository.save(c);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public Carro getById(Long id){
        return  repository.getOne(id);
    }

}
