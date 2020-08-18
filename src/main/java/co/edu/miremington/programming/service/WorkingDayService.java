package co.edu.miremington.programming.service;

import co.edu.miremington.programming.entity.WorkingDay;
import co.edu.miremington.programming.repository.WorkingDayRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author devnix
 *
 */
public class WorkingDayService {

    protected WorkingDayRepository workingDayRepository = new WorkingDayRepository();

    public List<WorkingDay> getAll() { return  this.workingDayRepository.getAll(); }

    public Optional<WorkingDay> getWorkingDayById(Integer id) {
        return this.workingDayRepository.getWorkingDayById(id);
    }

    public boolean add(WorkingDay workingDay) { return this.workingDayRepository.add(workingDay); }

    public boolean update(WorkingDay workingDay) {return this.workingDayRepository.update(workingDay); }

    public boolean delete(WorkingDay workingDay) { return this.workingDayRepository.delete(workingDay); }

}
