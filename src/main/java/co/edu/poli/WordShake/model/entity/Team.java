package co.edu.poli.WordShake.model.entity;

/**
     * Represents a team entity in the system.
     * Contains basic team information such as ID, name and associated league.
     */

    public class Team {
        /** Unique identifier for the team */
        private Integer id;

        /** Name of the team */
        private String name;

        /** League to which this team belongs */
        private League league;

        /**
         * Default constructor for Team.
         */
        public Team() {
        }

        /**
         * Creates a new Team with specified ID, name and league.
         *
         * @param id Unique identifier for the team
         * @param name Name of the team
         * @param league League the team belongs to
         */
        public Team(Integer id, String name, League league) {
            this.id = id;
            this.name = name;
        }

        /**
         * Gets the team's ID.
         *
         * @return The team's unique identifier
         */
        public Integer getId() {
            return id;
        }

        /**
         * Gets the team's name.
         *
         * @return The team's name
         */
        public String getName() {
            return name;
        }

        /**
         * Gets the league this team belongs to.
         *
         * @return The team's league
         */
        public League getLeague() {
            return league;
        }

        /**
         * Sets the team's ID.
         *
         * @param id The unique identifier to set
         */
        public void setId(Integer id) {
            this.id = id;
        }

        /**
         * Sets the team's name.
         *
         * @param name The name to set
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * Sets the team's league.
         *
         * @param league The league to set
         */
        public void setLeague(League league) {
            this.league = league;
        }

        /**
         * Returns a string representation of the team.
         *
         * @return The team's name
         */
        @Override
        public String toString() {
            return name;
        }
    }