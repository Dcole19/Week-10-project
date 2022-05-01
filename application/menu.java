package application;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import dao.GenreDao;
import dao.gameDao;
import entity.Game;
import entity.Genre;

public class menu {

    private gameDao gamedao = new gameDao();
    private GenreDao genredao = new GenreDao();
    private Scanner scanner = new Scanner(System.in);
    private List<String> options = Arrays.asList(
        "Display games", 
        "Display a game", 
        "Create a game", 
        "Delete a game",
        "Create a genre",
        "Delete a genre");
  
    public void start() {
      String selection = "";
      
      do {
        printMenu();
        selection = scanner.nextLine();
        
        try{
          if (selection.equals("1")) {
            displayGames();
          } else if (selection.equals("2")) {
            displayGame();
          } else if (selection.equals("3")) {
            createGame();
          } else if (selection.equals("4")) {
            deleteGame();
          } else if (selection.equals("4")) {
             createGenre();
          }else if (selection.equals("4")) {
             deleteGenre();
          }
        }catch (SQLException e) {
          e.printStackTrace();
        }
                
        System.out.println("Press enter to continue.");
        scanner.nextLine();
      }while (!selection.equals("-1"));
    }
    
    private void printMenu() {
      System.out.println("Make a selection: \n --------------");
      for (int i = 0; i < options.size(); i++) {
        System.out.println(i + 1 + ") " + options.get(i));
      }
    }
    
    
    private void displayGames() throws SQLException {
      List<Game> games = gamedao.getGames();
      for (Game game : games) {
        System.out.println(game.getGame_id() + ": " + game.getGame_name());
      }
    }
    
    private void displayGame() throws SQLException {
      System.out.print("Enter game id: ");
      int game_id = Integer.parseInt(scanner.nextLine());
      Game game = new Game(0, null, null);
      System.out.println(game.getGame_id() + ": " + game.getGame_id());
      for (Genre genre : game.getGenre()) {
        System.out.println("\tGenreId: " + genre.getGenre_id() + " - Name: " + genre.getGenre_name());
      }
      
    }
    private void createGame() throws SQLException {
      System.out.print("Enter new game name: ");
      String gameName = scanner.nextLine();
      gamedao.createNewGame(gameName);
    }
    
    private void deleteGame() throws SQLException {
      System.out.println("Enter team id to delete: ");
      int game_id = Integer.parseInt(scanner.nextLine());
      gamedao.deleteGameById(game_id);
      
    }
    
    private void createGenre() throws SQLException {
      System.out.println("Enter Genre name: ");
      String genre_name = scanner.nextLine();
      genredao.createNewGenre(genre_name);
    }
    
    private void deleteGenre() throws SQLException {
      System.out.println("Enter genre id to delete: ");
      int genre_id = Integer.parseInt(scanner.nextLine());
      genredao.deleteGenreById(genre_id);
    }
}
