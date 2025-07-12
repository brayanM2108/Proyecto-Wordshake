package co.edu.poli.WordShake.model;

/**
 * Represents categories of leagues.
 * Contains predefined major European leagues with their IDs and display names.
 */
public enum LeagueCategory {
        /** English Premier League */
        PREMIER_LEAGUE(1, "Premier League"),
        /** Spanish La Liga */
        LA_LIGA(2, "La Liga"),
        /** German Bundesliga */
        BUNDESLIGA(3, "Bundesliga"),
        /** Italian Serie A */
        SERIE_A(4, "Serie A"),
        /** French Ligue 1 */
        LIGUE_1(5, "Ligue 1");

        /** Unique identifier for the league category */
        private final int id;
        /** Display name of the league */
        private final String displayName;

        /**
         * Constructs a LeagueCategory with the specified ID and display name.
         *
         * @param id Unique identifier for the league category
         * @param displayName Display name of the league
         */
        LeagueCategory(int id, String displayName) {
                this.id = id;
                this.displayName = displayName;
        }

        /**
         * Gets the league category ID.
         *
         * @return The unique identifier
         */
        public int getId() {
                return id;
        }

        /**
         * Gets the league display name.
         *
         * @return The display name
         */
        public String getDisplayName() {
                return displayName;
        }

        /**
         * Finds a LeagueCategory by its ID.
         *
         * @param id The ID to search for
         * @return The matching LeagueCategory
         * @throws IllegalArgumentException if no category matches the given ID
         */
        public static LeagueCategory fromId(int id) {
                for (LeagueCategory category : values()) {
                        if (category.id == id) return category;
                }
                throw new IllegalArgumentException("Invalid LeagueCategory ID: " + id);
        }
}