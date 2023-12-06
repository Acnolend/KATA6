package tests;

import org.junit.Test;
import software.ulpgc.kata6.Shepherd;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.IntStream;



import static org.assertj.core.api.Assertions.assertThat;

public class ShepherdTest {

    @Test
    public void should_return_empty_state_given_empty_state() {
        ArrayList<Integer> arr = new ArrayList<>();
        Shepherd shepherd = new Shepherd(arr);
        shepherd.move(1);
        assertThat(shepherd.getState()).isEqualTo(arr);
    }

    @Test
    public void should_return_badexitcode_given_move1_and_move2() {
        ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(0, 0, 0));
        Shepherd shepherd = new Shepherd(arr);
        shepherd.move(1);
        shepherd.move(2);
        assertThat(shepherd.getExitCode()).isEqualTo(-1);
    }

    @Test
    public void should_return_goodexitcode_given_move1_move0_move2_and_move1() {
        ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(0, 0, 0));
        Shepherd shepherd = new Shepherd(arr);
        shepherd.move(1);
        shepherd.move(0);
        shepherd.move(2);
        shepherd.move(1);
        assertThat(shepherd.getExitCode()).isEqualTo(1);
    }

}
