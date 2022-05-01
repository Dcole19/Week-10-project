package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Genre;

public class GenreDao {

  private Connection connection;
  private final String GET_GENRES_BY_GAME_ID_QUERY = "Select * FROM genres WHERE game_id = ?";
  private final String CREATE_NEW_GENRE_QUERY = "INSERT INTO genre(genre_name) values(?)"; 
  private final String DELETE_GENRE_BY_ID_QUERY = "DELETE FROM genre WHERE id = ?";
  
  public GenreDao() {
    connection = DBConnection.getConnection();
  }
  
  public List<Genre> getGenreByGameId(int gameid) throws SQLException {
    PreparedStatement ps = connection.prepareStatement(GET_GENRES_BY_GAME_ID_QUERY);
      ps.setInt(1, gameid);
      ResultSet rs = ps.executeQuery();
      List<Genre> genre = new ArrayList<Genre>();
      
      while (rs.next()) {
        genre.add(new Genre(rs.getInt(1), rs.getString(2)));
      }
    return genre;
    }
  
  public void createNewGenre(String genre_name) throws SQLException {
    PreparedStatement ps = connection.prepareStatement(CREATE_NEW_GENRE_QUERY);
    ps.setString(1, genre_name);
    ps.executeUpdate();
  }
  
  public void deleteGenreById(int genre_id) throws SQLException {
    PreparedStatement ps = connection.prepareStatement(DELETE_GENRE_BY_ID_QUERY);
    ps.setInt(1, genre_id);
    ps.executeUpdate();
  }

    
  }

  
  


