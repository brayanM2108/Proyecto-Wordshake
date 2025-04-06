package co.edu.poli.WordShake.model;

public enum DifficultyMode {
    EASY(300, true, false),     // 5 min, categorías simples
    MEDIUM(180, true, true),    // 3 min, categorías combinadas
    HARD(120, false, true);      // 2 min, categorías avanzadas

    private final int timeLimitInSeconds;
    private final boolean allowGeneralCategories;
    private final boolean allowAdvancedCategories;

    DifficultyMode(int timeLimitInSeconds, boolean allowGeneralCategories, boolean allowAdvancedCategories) {
        this.timeLimitInSeconds = timeLimitInSeconds;
        this.allowGeneralCategories = allowGeneralCategories;
        this.allowAdvancedCategories = allowAdvancedCategories;
    }

    public int getTimeLimitInSeconds() {
        return timeLimitInSeconds;
    }

    public boolean allowsGeneralCategories() {
        return allowGeneralCategories;
    }

    public boolean allowsAdvancedCategories() {
        return allowAdvancedCategories;
    }
}
