package co.edu.poli.WordShake.model;

public enum DifficultyMode {
    EASY(300),     // 5 minutos
    MEDIUM(180 ),    // 3 minutos
    HARD(20), //2 minutos
    TRAINING(0);// tiempo ilimitado

    private final int timeLimitInSeconds;

    DifficultyMode(int timeLimitInSeconds) {
        this.timeLimitInSeconds = timeLimitInSeconds;
    }

    public int getTimeLimitInSeconds() {
        return timeLimitInSeconds;
    }

}
