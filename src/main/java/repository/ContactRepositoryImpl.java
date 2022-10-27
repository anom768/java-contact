package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import entity.Contact;

public class ContactRepositoryImpl implements ContactRepository {

    private DataSource dataSource;

    public ContactRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(Contact contact) {
        String sql = "INSERT INTO contacts(name, phone, email) VALUES(?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, contact.getName());
                preparedStatement.setString(2, contact.getPhone());
                preparedStatement.setString(3, contact.getEmail());
                preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public Contact getContact(Integer id) {
        String sql = "SELECT * FROM contacts WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, id);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return new Contact(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("phone"),
                            resultSet.getString("email")
                        );
                    } else {
                        return null;
                    }
                }
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }
    
    @Override
    public Contact[] getAll() {
        String sql = "SELECT * FROM contacts";
        try (Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)) {
                List<Contact> list = new ArrayList<>();
                while (resultSet.next()) {
                    list.add(new Contact(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("phone"),
                        resultSet.getString("email")
                    ));
                }
                return list.toArray(new Contact[]{});
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void deleteById(Integer id) {
        String delete = "DELETE FROM contacts WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(delete)) {
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void change(Contact contact) {
        String sql = "UPDATE contacts SET name = ?, phone = ?, email = ? WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, contact.getName());
                preparedStatement.setString(2, contact.getPhone());
                preparedStatement.setString(3, contact.getEmail());
                preparedStatement.setInt(4, contact.getId());
                preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void deleteAll() {
        String sql = "DELETE FROM contacts";

        try (Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement()) {
                statement.executeUpdate(sql);
        } catch (SQLException exception) {
            throw new RuntimeException(exception); 
        }
    }

}
