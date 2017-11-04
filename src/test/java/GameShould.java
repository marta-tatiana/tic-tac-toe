import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class GameShould {

    @Test public void
    startWithX() {
        Game result = new Game().play(new Position(1,2));
        assertThat(result.lastMove(), equalTo(Move.X));
    }

    @Test public void
    alternateXAndOMoves() {
        Game game = new Game();
        Game firstGame = game.play(new Position(1,2));
        assertThat(firstGame.lastMove(), equalTo(Move.X));
        Game secondResult = game.play(new Position(2,2));
        assertThat(secondResult.lastMove(), equalTo(Move.O));
        Game thirdResult = game.play(new Position(0,0));
        assertThat(thirdResult.lastMove(), equalTo(Move.X));
    }

    @Test public void
    notAllowPlayingOnTakenPosition() {
        Game game = new Game();
        Game firstMove = game.play(new Position (0,0));
        assertThat(firstMove.invalidMove(), is(false));
        Game afterSecondMove = game.play(new Position(0,0));
        assertThat(afterSecondMove.invalidMove(), is(true));
        assertThat(afterSecondMove.lastMove(), equalTo(Move.X));
    }

    @Test public void
    endWithDrawAfterNineMovesWithNoWinner() {
        Game game = new Game().play(new Position(0,0))
                .play(new Position(0,1))
                .play(new Position(0,2))
                .play(new Position(1,1))
                .play(new Position(1,0))
                .play(new Position(2,0))
                .play(new Position(1,2))
                .play(new Position(2,2))
                .play(new Position(2,1));
        assertThat(game.isDraw(), is(true));
    }


    @Test public void
    endWithXWinAfter5Moves() {
        Game game = new Game().play(new Position(0,0))
                .play(new Position(1,1))
                .play(new Position(0,2))
                .play(new Position(1,2))
                .play(new Position(0,1));
        assertThat(game.xWon(), is(true));
        assertThat(game.isDraw(), is(false));
    }
}
