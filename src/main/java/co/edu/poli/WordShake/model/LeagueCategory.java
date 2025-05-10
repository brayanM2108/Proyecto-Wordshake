package co.edu.poli.WordShake.model;

public enum LeagueCategory {
        PREMIER_LEAGUE(1, "Premier League"),
        LA_LIGA(2, "La Liga"),
        BUNDESLIGA(3, "Bundesliga"),
        SERIE_A(4, "Serie A"),
        LIGUE_1(5, "Ligue 1");

        private final int id;
        private final String displayName;

        LeagueCategory(int id, String displayName) {
                this.id = id;
                this.displayName = displayName;
        }

        public int getId() {
                return id;
        }

        public String getDisplayName() {
                return displayName;
        }

        public static LeagueCategory fromId(int id) {
                for (LeagueCategory category : values()) {
                        if (category.id == id) return category;
                }
                throw new IllegalArgumentException("Invalid LeagueCategory ID: " + id);
        }
}
