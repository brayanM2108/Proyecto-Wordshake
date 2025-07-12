package co.edu.poli.WordShake.model;

/**
 * Represents the different game modes available in the WordShake game.
 */
public enum GameMode {
    /** Mode where all leagues are included in the game */
    ALL_LEAGUES,

    /** Mode where only players from a specific league are included */
    BY_LEAGUE,

    /** Mode where players from three selected leagues are included */
    BY_THREE_LEAGUES,

    /** Mode where players are filtered by their playing position */
    BY_POSITION,

    /** Training mode for practice purposes */
    TRAINING,
}