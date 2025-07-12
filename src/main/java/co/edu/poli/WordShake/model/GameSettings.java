package co.edu.poli.WordShake.model;



/**
 * Manages the game configuration settings including difficulty, game mode and league selections.
 * Handles both single league and three league game modes.
 */
public class GameSettings {
    /** The selected difficulty level */
    private DifficultyMode difficulty;

    /** The selected game mode */
    private GameMode gameMode;

    /** The selected league for single league mode */
    private LeagueCategory selectedLeague;

    /** The selected player position */
    private String selectedPosition;

    /** First selected league for three league mode */
    private LeagueCategory selectedThreeLeague1;

    /** Second selected league for three league mode */
    private LeagueCategory selectedThreeLeague2;

    /** Third selected league for three league mode */
    private LeagueCategory selectedThreeLeague3;

    /** Counter for number of selected leagues in three league mode */
    private int selectedCount = 0;

    /**
     * Creates new game settings with specified difficulty and mode.
     *
     * @param difficulty The difficulty level
     * @param gameMode The game mode
     */
    public GameSettings(DifficultyMode difficulty, GameMode gameMode) {
        this.difficulty = difficulty;
        this.gameMode = gameMode;
    }

    /**
     * Gets the current difficulty level.
     *
     * @return The difficulty mode
     */
    public DifficultyMode getDifficulty() {
        return difficulty;
    }

    /**
     * Gets the current game mode.
     *
     * @return The game mode
     */
    public GameMode getGameMode() {
        return gameMode;
    }

    /**
     * Gets the selected player position.
     *
     * @return The selected position
     */
    public String getSelectedPosition() {
        return selectedPosition;
    }

    /**
     * Gets the selected league for single league mode.
     *
     * @return The selected league
     */
    public LeagueCategory getSelectedLeague() {
        return selectedLeague;
    }

    /**
     * Gets the IDs of the three selected leagues.
     *
     * @return Array of league IDs or null if not all leagues are selected
     */
    public int[] getThreeLeagueIds() {
        if (selectedThreeLeague1 == null || selectedThreeLeague2 == null || selectedThreeLeague3 == null) {
            return null;
        }
        return new int[] {
                selectedThreeLeague1.getId(),
                selectedThreeLeague2.getId(),
                selectedThreeLeague3.getId()
        };
    }

    /**
     * Sets the difficulty level.
     *
     * @param difficulty The difficulty to set
     */
    public void setDifficulty(DifficultyMode difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * Sets the game mode.
     *
     * @param gameMode The game mode to set
     */
    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
    }

    /**
     * Sets the selected league for single league mode.
     *
     * @param selectedLeague The league to set
     */
    public void setSelectedLeague(LeagueCategory selectedLeague) {
        this.selectedLeague = selectedLeague;
    }

    /**
     * Sets the selected player position.
     *
     * @param selectedPosition The position to set
     */
    public void setSelectedPosition(String selectedPosition) {
        this.selectedPosition = selectedPosition;
    }

    /**
     * Toggles selection of a league in three league mode.
     * Adds the league if not selected and count < 3, removes if already selected.
     *
     * @param league The league to toggle
     */
    public void setThreeLeagues(LeagueCategory league) {
        if (isLeagueSelected(league)) {
            removeLeague(league);
        } else if (selectedCount < 3) {
            addLeague(league);
        }
    }

    /**
     * Checks if basic game settings are valid.
     *
     * @return true if difficulty and game mode are set
     */
    public boolean isValid() {
        return difficulty != null && gameMode != null;
    }

    /**
     * Checks if a league is already selected in three league mode.
     *
     * @param league The league to check
     * @return true if the league is already selected
     */
    private boolean isLeagueSelected(LeagueCategory league) {
        return league == selectedThreeLeague1 ||
                league == selectedThreeLeague2 ||
                league == selectedThreeLeague3;
    }

    /**
     * Adds a league to the next available slot in three league mode.
     *
     * @param league The league to add
     */
    private void addLeague(LeagueCategory league) {
        if (selectedThreeLeague1 == null) {
            selectedThreeLeague1 = league;
        } else if (selectedThreeLeague2 == null) {
            selectedThreeLeague2 = league;
        } else {
            selectedThreeLeague3 = league;
        }
        selectedCount++;
    }

    /**
     * Removes a league from three league mode and shifts remaining leagues.
     *
     * @param league The league to remove
     */
    private void removeLeague(LeagueCategory league) {
        if (league == selectedThreeLeague1) {
            selectedThreeLeague1 = selectedThreeLeague2;
            selectedThreeLeague2 = selectedThreeLeague3;
            selectedThreeLeague3 = null;
        } else if (league == selectedThreeLeague2) {
            selectedThreeLeague2 = selectedThreeLeague3;
            selectedThreeLeague3 = null;
        } else if (league == selectedThreeLeague3) {
            selectedThreeLeague3 = null;
        }
        selectedCount--;
    }

    /**
     * Checks if all three leagues are selected.
     *
     * @return true if all three league slots are filled
     */
    public boolean hasThreeLeagues() {
        return selectedThreeLeague1 != null &&
                selectedThreeLeague2 != null &&
                selectedThreeLeague3 != null;
    }
}