package co.edu.poli.WordShake.model;

public enum PositionCategory {


    GOALKEEPER(1),
    DEFENDER(2),
    MIDFIELDER(3),
    FORWARD(4),
    ;
    private final int positionId;
    PositionCategory(int positionId) {
        this.positionId = positionId;

    }
    public int getPositionId() {
        return positionId;
    }
}
