package co.edu.miremington.programming.Enum;

/**
 * List of the type of documents available
 * @author devnix
 */
public enum Documents {

    IDENTIFICATION_CARD("Identification card", 1), FORENG_IDENTIFICATION_CARD("Foreing identification card", 2), NIT("Nit", 3);

    private String name;
    private Integer id;

    Documents(String name, Integer id) {
        this.name = name;
        this.id = id;
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
}
