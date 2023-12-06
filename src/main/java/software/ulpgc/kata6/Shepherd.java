package software.ulpgc.kata6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.IntStream;

public class Shepherd {
    private ArrayList<Integer> state;
    private int exitCode = 0;

    public Shepherd(ArrayList<Integer> state) {
        this.state = state;
    }

    public void move(int item){
        if(state.size() == 3){
            ArrayList<Integer> actualState = (ArrayList<Integer>) state.clone();
            state.set(item, 1);
            if(isThatMatch(actualState)){
                System.out.println("La combinacion es prohibida!");
            }
        }
    }

    private boolean isWin() {
        return new HashSet<>(state).size() <= 1;
    }

    private interface Check {
        boolean check();
    }

    private List<Check> checksOf(ArrayList<Integer> actualState){
        ArrayList<Integer> posibleActualState1 = new ArrayList<>(Arrays.asList(0, 1, 0));
        ArrayList<Integer> posibleActualState2 = new ArrayList<>(Arrays.asList(1, 0, 0));
        ArrayList<Integer> posibleState1 = new ArrayList<>(Arrays.asList(1, 1, 0));
        ArrayList<Integer> posibleState2 = new ArrayList<>(Arrays.asList(1, 0, 1));
        return List.of(
                () -> actualState.equals(posibleActualState1) && state.equals(posibleState1),
                () -> actualState.equals(posibleActualState2) && state.equals(posibleState2),
                () -> isWin(),
                () -> state.get(1).equals(state.get(0)) || state.get(1).equals(state.get(2))
        );
    }

    private int anyCheckFor(ArrayList<Integer> actualState) {
        List<Check> checks = checksOf(actualState);
        return IntStream.range(0, checks.size())
                .filter(i -> checks.get(i).check())
                .findFirst().orElse(-1);
    }

    private boolean isThatMatch(ArrayList<Integer> actualState) {
        int check = anyCheckFor(actualState);
        if (check == 0 || check == 1) {
            state = new ArrayList<>(Arrays.asList(1, 0, check));
        } else if (check == 2) {
            exitCode = 1;
        } else if (check == 3) {
            exitCode = -1;
            return true;
        }
        return false;
    }

    public ArrayList<Integer> getState() {
        return state;
    }

    public int getExitCode() {
        return exitCode;
    }
}
