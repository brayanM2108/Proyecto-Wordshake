package co.edu.poli.WordShake.model;


public class GameSettings {

    private static GameMode selectedGameMode;
    private static DifficultyMode selectedDifficulty;

    // Getters y setters estáticos
    public static GameMode getSelectedGameMode() {
        return selectedGameMode;
    }

    public static void setSelectedGameMode(GameMode gameMode) {
        selectedGameMode = gameMode;
    }

    public static DifficultyMode getSelectedDifficulty() {
        return selectedDifficulty;
    }

    public static void setSelectedDifficulty(DifficultyMode difficulty) {
        selectedDifficulty = difficulty;
    }
}

