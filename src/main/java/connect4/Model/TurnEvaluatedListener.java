package connect4.Model;

public interface TurnEvaluatedListener {
    void opponentTurnEvaluated(TurnResult turnResult);
    void myTurnEvaluated(TurnResult turnResult);
}
