package co.edu.poli.WordShake.model.entity;

/**
 * Represents a player entity in the system.
 * Contains basic player information such as ID, name, position and associated team.
 */
public class Player {
    /** Unique identifier for the player */
    private Integer id;

    /** Name of the player */
    private String name;

    /** Position the player plays in */
    private String position;

    /** Team the player belongs to */
    private Team team;

    /**
     * Default constructor for Player.
     */
    public Player() {
    }

    /**
     * Creates a new Player with specified ID, name, position and team.
     *
     * @param id Unique identifier for the player
     * @param name Name of the player
     * @param position Position the player plays in
     * @param team Team the player belongs to
     */
    public Player(Integer id, String name, String position, Team team) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.team = team;
    }

    /**
     * Gets the player's ID.
     *
     * @return The player's unique identifier
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the player's ID.
     *
     * @param id The unique identifier to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets the player's name.
     *
     * @return The player's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the player's name.
     *
     * @param name The name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the player's position.
     *
     * @return The player's position
     */
    public String getPosition() {
        return position;
    }

    /**
     * Sets the player's position.
     *
     * @param position The position to set
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * Gets the team this player belongs to.
     *
     * @return The player's team
     */
    public Team getTeam() {
        return team;
    }

    /**
     * Sets the player's team.
     *
     * @param team The team to set
     */
    public void setTeam(Team team) {
        this.team = team;
    }

    /**
     * Returns a formatted string representation of the player.
     *
     * @return A string containing all player details in a formatted manner
     */
    @Override
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