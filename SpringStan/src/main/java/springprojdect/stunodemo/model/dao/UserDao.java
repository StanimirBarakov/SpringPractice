package springprojdect.stunodemo.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.quartz.QuartzProperties;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import springprojdect.stunodemo.model.pojos.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class UserDao extends BaseDao{

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<User> getAllUsers(){
        List<User> users = jdbcTemplate.query("SELECT id,name,age,email,password FROM users",(resultSet, i) ->  {return toUser(resultSet);});
        return users;
    }
    private User toUser(ResultSet resultSet) throws SQLException {
        User u = new User(resultSet.getString("name"),
                resultSet.getInt("age"),
                resultSet.getString("email"),
                resultSet.getString("password"));
        u.setId(resultSet.getInt("id"));
        return u;
    }
    @ExceptionHandler({BadSqlGrammarException.class})
    @ResponseStatus(value = HttpStatus.I_AM_A_TEAPOT)
    public String handleSQLError(){
        return "SORI MOTORI SQLA PADNA!";
    }
}
