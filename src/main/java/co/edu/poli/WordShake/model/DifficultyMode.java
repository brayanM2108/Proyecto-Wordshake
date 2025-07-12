package co.edu.poli.WordShake.model;

/**
 * Represents the difficulty modes available in the WordShake game.
 * Each mode has an associated time limit in seconds.
 */
public enum DifficultyMode {
    /** Easy mode - 5 minutes (300 seconds) time limit */
    EASY(300),
    /** Medium mode - 3 minutes (180 seconds) time limit */
    MEDIUM(180),
    /** Hard mode - 2 minutes (120 seconds) time limit */
    HARD(120),
    /** Training mode - unlimited time (0 seconds) */
    TRAINING(0);

    /** Time limit in seconds for the difficulty mode */
    private final int timeLimitInSeconds;

    /**
     * Constructs a DifficultyMode with the specified time limit.
     *
     * @param timeLimitInSeconds The time limit in seconds for this difficulty mode
     */
    DifficultyMode(int timeLimitInSeconds) {
        this.timeLimitInSeconds = timeLimitInSeconds;
    }

    /**
     * Gets the time limit for this difficulty mode.
     *
     * @return The time limit in seconds
     */
    public int getTimeLimitInSeconds() {
        return timeLimitInSeconds;
    }
}