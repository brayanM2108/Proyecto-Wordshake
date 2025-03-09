package co.edu.poli.WordShake.model;

public class Player {

    private Integer id;
    private String name;
    private String position;
    private Team team;

    public Player() {

    }
    public Player(Integer id, String name, String position, Team team) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.team = team;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public String toString() {
        return
                "\n-------------------------------------" +
                        "\nJugadores" +
                        "\nid: " + id +
                        "\nnombre: " + name +
                        "\nposicion: " + position +
                        "\nequipo: " + team +
                        "\n-------------------------------------"
                ;
    }

}
