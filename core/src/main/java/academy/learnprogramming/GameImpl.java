package academy.learnprogramming;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GameImpl implements Game {

    private static final Logger log = LoggerFactory.getLogger(GameImpl.class);

    private NumberGenerator numberGenerator;
    private int guessCount = 10;
    private int number; //numer do odgadnięcia
    private int guess; //liczba podana przez gracza
    private int smallest; //dolny zakres z którego generowana będzie liczba do odgadnięcia
    private int biggest; //górny zakres z którego generowana będzie liczba do odgadnięcia
    private int remainingGuesses; //ile jeszcze prób pozostało
    private boolean validNumberRange = true;

//    public GameImpl(NumberGenerator numberGenerator) {
//        this.numberGenerator = numberGenerator; //tutaj zostanie wstrzyknięta zależność zdefiniowana w beans.xml w tagu <constructor-arg> (constructor based dependency injection)
//    }

    //setter dla numberGeneratora
    public void setNumberGenerator(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator; //tutaj zostanie wstrzyknięta zależność zdefiniowana w beans.xml w tagu <constructor-arg> (setter based dependency injection)
    }

    @Override
    public void reset() { //resetowanie gry, ustawianie wartości początkowych na wszystkich polach
        smallest = 0;
        guess = 0;
        remainingGuesses = guessCount;
        biggest = numberGenerator.getMaxNumber();
        number = numberGenerator.next();

        log.debug("The number to guess is {}", number);
    }

    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public int getGuess() {
        return guess;
    }

    @Override
    public void setGuess(int guess) {
        this.guess = guess;
    }

    @Override
    public int getSmallest() {
        return smallest;
    }

    @Override
    public int getBiggest() {
        return biggest;
    }

    @Override
    public int getRemainingGuesses() {
        return remainingGuesses;
    }

    @Override
    public void check() {

        checkValidNumberRange();

        if (validNumberRange) {
            if (guess > number) {
                biggest = guess - 1;
            }
            if (guess < number) {
                smallest = guess + 1;
            }
        }

        remainingGuesses--;
    }

    @Override
    public boolean isValidNumberRange() {
        return validNumberRange;
    }

    @Override
    public boolean isGameWon() {
        return guess == number;
    }

    @Override
    public boolean isGameLost() {
        return !isGameWon() && remainingGuesses <= 0;
    }

    private void checkValidNumberRange() {
        validNumberRange = guess >= smallest && guess <= biggest;
    }
}
