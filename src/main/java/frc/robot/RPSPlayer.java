package frc.robot;

import java.util.ArrayList;
import java.lang.Character;

public class RPSPlayer {
    private ArrayList<Character> moveHistory;
    private int score;

    public RPSPlayer() {
        moveHistory = new ArrayList<>();
        score = 0;
    }

    public char nextMove(RPSPlayer op) {
        int roundIndex = this.moveHistory.size();
        if(roundIndex == 0) {
            return 'r';
        } else if(roundIndex == 1) {
            return 's';
        } else if(op.getTriple() != 'n') {
            if(op.getTriple() == 'r') {
                return 'p';
            } else if(op.getTriple() == 's') {
                return 'r';
            } else if(op.getTriple() == 'p') {
                return 's';
            }
        }

        return op.moveHistory.get(moveHistory.size()-1).charValue();
        
    }

    public void addScore() {
        score += 1;
    }

    public ArrayList<Character> getMoves() {
        return this.moveHistory;
    }

    public char getTriple() {
        char lastChar = moveHistory.get(moveHistory.size()-1);
        boolean b = true;
        for(int i = moveHistory.size()-1; i > moveHistory.size() - 4; i--) {
           char here = moveHistory.get(i); 
           if(here != lastChar) {
                b = false;
           } 
           lastChar = here;
        }
        return b ? lastChar : 'n'; // if b is false, return n for 'no move'
    }

    public int getScore() {
        return this.score;
    }

    public void reset() {
        this.moveHistory = new ArrayList<Character>();
        this.score = 0;
    }

    public void negateLastRound() {
        if(moveHistory.size() > 0) {
            moveHistory.remove(moveHistory.size()-1);
        }
    }
}