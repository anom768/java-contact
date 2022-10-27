package repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.zaxxer.hikari.HikariDataSource;

import entity.Contact;
import util.DatabaseUtil;

public class ContactRepositoryImplTest {
    
    private HikariDataSource dataSource;
    private ContactRepository contactRepository;

    @BeforeEach
    void setUp() {
        dataSource = DatabaseUtil.getDataSource();
        contactRepository = new ContactRepositoryImpl(dataSource);

        contactRepository.deleteAll();
    }

    @Test
    void testAdd() {
        Contact contact = new Contact();
        contact.setEmail("");
        contact.setName("anom");
        contact.setPhone("089");

        contactRepository.add(contact);
    }

    @Test
    void testGetAll() {
        contactRepository.add(new Contact("anom", "089", ""));
        contactRepository.add(new Contact("bangkit", "081", ""));
        contactRepository.add(new Contact("ika", "082", "ika@test.com"));

        var contacts = contactRepository.getAll();
        for (var contact : contacts) {
            System.out.println("[" + contact.getId() + "] " + contact.getName());
        }
    }

    @Test
    void testGetContact() {
        contactRepository.add(new Contact("anom", "089", ""));
        Contact contact = contactRepository.getContact(46);
        Assertions.assertNull(contact);
    }

    @Test
    void testDeleteById() {
        contactRepository.add(new Contact("anom", "089", ""));
        contactRepository.add(new Contact("bangkit", "081", ""));
        contactRepository.add(new Contact("ika", "082", "ika@test.com"));

        contactRepository.deleteById(55);
    }

    @Test
    void testChange() {
        contactRepository.add(new Contact("anom", "089", ""));
        contactRepository.change(new Contact(58, "bangkit", "0123", "bangkit@test.com"));
    }

    @AfterEach
    void tearDown() {
        dataSource.close();
    }

}
