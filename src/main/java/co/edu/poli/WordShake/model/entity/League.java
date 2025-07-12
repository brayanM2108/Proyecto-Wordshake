package co.edu.poli.WordShake.model.entity;

    /**
     * Represents a league entity in the system.
     * Contains basic league information such as ID and name.
     */
    public class League {
        /** Unique identifier for the league */
        private Integer id;

        /** Name of the league */
        private String name;

        /**
         * Default constructor for League.
         */
        public League() {
        }

        /**
         * Creates a new League with specified ID and name.
         *
         * @param id Unique identifier for the league
         * @param name Name of the league
         */
        public League(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        /**
         * Gets the league's ID.
         *
         * @return The league's unique identifier
         */
        public Integer getId() {
            return id;
        }

        /**
         * Gets the league's name.
         *
         * @return The league's name
         */
        public String getName() {
            return name;
        }

        /**
         * Sets the league's ID.
         *
         * @param id The unique identifier to set
         */
        public void setId(Integer id) {
            this.id = id;
        }

        /**
         * Sets the league's name.
         *
         * @param name The name to set
         */
        public void setName(String name) {
            this.name = name;
        }
    }