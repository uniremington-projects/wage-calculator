package co.edu.miremington.programming.repository;

import co.edu.miremington.programming.data.Data;
import co.edu.miremington.programming.entity.WorkingDay;

import java.util.List;
import java.util.Optional;

/**
 * @author devnix
 *
 */
public class WorkingDayRepository implements Data {

    /**
     *
     * @return
     */
    public List<WorkingDay> getAll() {
        return WORKING_DAY_LIST;
    }

    public Optional<WorkingDay> getWorkingDayById(Integer id) {
        return WORKING_DAY_LIST.stream().filter(f -> f.getId() == id).findFirst();
    }

    /**
     *
     * @param workingDay
     * @return
     */
    public boolean add(WorkingDay workingDay) {
        Optional<WorkingDay> optionalWorkingDay = this.getWorkingDayById(workingDay.getId());
        if (optionalWorkingDay.isPresent()){
            return false;
        } else {
            WORKING_DAY_LIST.add(workingDay);
            return true;
        }
    }

    /**
     *
     * @param workingDay
     * @return
     */
    public boolean update(WorkingDay workingDay) {
        Optional<WorkingDay> optionalWorkingDay = this.getWorkingDayById(workingDay.getId());
        if (optionalWorkingDay.isPresent()){
            return false;
        } else {
            WORKING_DAY_LIST.stream().filter(f -> f.getId() == workingDay.getId()).forEach(u -> u = workingDay);
            return true;
        }
    }

    /**
     *
     * @param workingDay
     * @return
     */
    public boolean delete(WorkingDay workingDay) {
        Optional<WorkingDay> optionalWorkingDay = this.getWorkingDayById(workingDay.getId());
        if (optionalWorkingDay.isPresent()){
            WORKING_DAY_LIST.removeIf(f -> f.getId() == workingDay.getId());
            return true;
        } else {
            return false;
        }
    }
}
