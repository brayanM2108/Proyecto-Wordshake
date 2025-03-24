package co.edu.poli.WordShake.model;

public enum LeagueCategory {

        PREMIER_LEAGUE(1),
        LALIGA(2),
        BUNDESLIGA(3),
        SERIE_A(4),
        LIGUE_1(5);

        private final int leagueId;

        LeagueCategory(int leagueId) {
            this.leagueId = leagueId;
        }

        public int getLeagueId() {
            return leagueId;
        }

}
