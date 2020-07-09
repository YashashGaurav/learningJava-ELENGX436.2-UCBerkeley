package ModuleOneToSix;

import java.util.*;
import java.text.SimpleDateFormat;
import java.util.concurrent.ThreadLocalRandom;

public class BowlingCollection {

    public static void main(String[] args) {
        Bowler bowler = new Bowler("John Doe");
        // Add 20 random games
        for (int i = 0; i < 20; i++) {
            bowler.AddGame(getRandomDate(), ThreadLocalRandom.current().nextInt(300));
        }

        // Show the details:
        System.out.println("Details of the bowler are:");
        System.out.println("");
        System.out.println("Bowler's name: " + bowler.GetName());
        System.out.println("Number of Bowler's game: " + bowler.GetTotalGamesPlayed());
        System.out.println("Average score: " + bowler.GetAverageScore());
        System.out.println("Bowler's last game Date: " + bowler.GetLastGameDate());
        System.out.println("Bowler's last game score: " + bowler.GetLastGameScore());

    }

    private static Date getRandomDate() {
        Calendar cal = new GregorianCalendar(1970 + ThreadLocalRandom.current().nextInt(30),
                ThreadLocalRandom.current().nextInt(13), ThreadLocalRandom.current().nextInt(29));
        return cal.getTime();
    }

}

class Bowler {

    private String name;
    private List<Game> games;

    Bowler(String name) {
        this.name = name;
        this.games = new LinkedList<Game>();
    }

    Bowler(String name, List<Game> games) {
        this.name = name;
        this.games = games;
    }

    public String GetName() {
        return this.name;
    }

    public void AddGame(Date gameDate, int score) {
        this.games.add(new Game(gameDate, score));
    }

    public int GetTotalGamesPlayed() {
        return games.size();
    }

    public float GetAverageScore() {
        int allGameScoreSum = 0;
        for (Game game : this.games) {
            allGameScoreSum += game.getScore();
        }
        return allGameScoreSum / GetTotalGamesPlayed();
    }

    public int GetLastGameScore() {
        return this.games.get(this.games.size() - 1).getScore();
    }

    public String GetLastGameDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
        return sdf.format(this.games.get(this.games.size() - 1).getDate());
    }

}

class Game {
    private Date gameDate;
    private int score;

    Game(Date gameDate, int gameScore) {
        this.setDate(gameDate);
        this.setScore(gameScore);
    }

    public Date getDate() {
        return this.gameDate;
    }

    public void setDate(Date date) {
        this.gameDate = date;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

}
