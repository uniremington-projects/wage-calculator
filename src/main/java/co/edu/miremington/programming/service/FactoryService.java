package co.edu.miremington.programming.service;

import co.edu.miremington.programming.entity.Factory;
import co.edu.miremington.programming.repository.FactoryRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author devnix
 *
 */
public class FactoryService {

    protected FactoryRepository factoryRepository = new FactoryRepository();

    public List<Factory> getAll() { return  this.factoryRepository.getAll(); }

    public Optional<Factory> getFactoryById(Integer id) {
        return this.factoryRepository.getFactoryById(id);
    }

    public boolean add(Factory factory) { return this.factoryRepository.add(factory); }

    public boolean update(Factory factory) {return this.factoryRepository.update(factory); }

    public boolean delete(Factory factory) { return this.factoryRepository.delete(factory); }

}
