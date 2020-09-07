package co.edu.miremington.programming.Enum;

/**
 * List of the type of work days available
 * @author devnix
 */
public enum Shift {

    DAY_SHIFT("Day shift", 1, 15000),
    NIGHT_SHIFT("Night shift", 2, 20000),
    DOMINICAL_DAY_SHIFT("Dominical day shift", 3, 15500),
    DOMINICAL_NIGHT_SHIFT("Dominical night shift", 4, 20750);

    private String name;
    private Integer id;
    private float hourlyRate;

    Shift(String name, Integer id, float hourlyRate) {
        this.name = name;
        this.id = id;
        this.hourlyRate = hourlyRate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public float getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(float hourlyRate) {
        this.hourlyRate = hourlyRate;
    }
}
