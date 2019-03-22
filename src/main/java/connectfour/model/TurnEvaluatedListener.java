package connectfour.model;

public interface TurnEvaluatedListener {
    void opponentTurnEvaluated(TurnResult turnResult);
    void myTurnEvaluated(TurnResult turnResult);
}
