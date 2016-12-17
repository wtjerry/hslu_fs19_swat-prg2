package Model;

public interface TurnEvaluatedListener {
    void opponentTurnEvaluated(TurnResult turnResult);
    void myTurnEvaluated(TurnResult turnResult);
}
