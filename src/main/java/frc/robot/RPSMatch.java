package frc.robot;

public class RPSMatch {

    private RPSPlayer robot,
                      human;

    public RPSMatch() {
        robot = new RPSPlayer();
        human = new RPSPlayer();
    }

    public char humanShoots(char humanMove) {
        human.getMoves().add(humanMove); // adds move to human's move history
        return robot.nextMove(human); // returns the robot's reaction
    }

    public void resetAll() {
        human.reset();
        robot.reset();
    }


}