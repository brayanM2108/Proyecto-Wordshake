package co.edu.poli.WordShake.model;

public class League {
    private Integer id;
    private String name;

    public League() {
    }

    public League(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
