package co.edu.poli.WordShake.model;

public class Team {
    private Integer id;
    private String name;
    private League league;

    public Team() {
    }

    public Team(Integer id, String name, League league) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public League getLeague() {
        return league;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    @Override
    public String toString() {
        return name;
    }
}
