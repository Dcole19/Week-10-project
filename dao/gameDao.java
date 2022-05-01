package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Game;

public class gameDao {

  private Connection connection;
  private GenreDao GenreDao;
  private final String GET_GAMES_QUERY = "SELECT * FROM games";
  private final String GET_GAME_BY_ID_QUERY = "SELECT * FROM games WHERE id = ?";
  private final String CREATE_NEW_GAME_QUERY = "INSERT INTO games(name) VALUES(?)";
  private final String DELETE_GAME_BY_ID_QUERY = "DELETE FROM games WHERE id = ?";
  
  public gameDao() {
    connection = DBConnection.getConnection();
    GenreDao = new GenreDao();
  }
  
  public List<Game> getGames() throws SQLException {
    ResultSet rs = connection.prepareStatement(GET_GAMES_QUERY).executeQuery();
    List<Game> games = new ArrayList<Game>();
    
    while (rs.next()) {
      games.add(populateGame(rs.getInt(1), rs.getString(2)));
    }
    
    return games;
  }
  public Game getGameById(int id) throws SQLException {
    PreparedStatement ps = connection.prepareStatement(GET_GAME_BY_ID_QUERY);
    ps.setInt(1,  id);
    ResultSet rs = ps.executeQuery();
    rs.next();
    return populateGame(rs.getInt(1), rs.getString(2));
  }
  
  public void createNewGame(String gameName) throws SQLException {
    PreparedStatement ps = connection.prepareStatement(CREATE_NEW_GAME_QUERY);
    ps.setString(1, gameName);
    ps.executeUpdate();
  }
  
  public void deleteGameById(int game_id) throws SQLException {
    PreparedStatement ps = connection.prepareStatement(DELETE_GAME_BY_ID_QUERY);
    ps.setInt(1, game_id);
    ps.executeUpdate();
  }
  private Game populateGame(int id, String name) throws SQLException {
    return new Game(id, name, GenreDao.getGenreByGameId(id));
  }
  
  
  
}
