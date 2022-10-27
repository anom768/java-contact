package service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.zaxxer.hikari.HikariDataSource;

import entity.Contact;
import repository.ContactRepository;
import repository.ContactRepositoryImpl;
import util.DatabaseUtil;

public class ContactServiceImplTest {

    private HikariDataSource dataSource;
    private ContactRepository contactRepository;
    private ContactService contactService;
    
    @BeforeEach
    void setUp() {
        dataSource = DatabaseUtil.getDataSource();
        contactRepository = new ContactRepositoryImpl(dataSource);
        contactService = new ContactServiceImpl(contactRepository);

        contactRepository.deleteAll();
    }

    @Test
    void testShowAll() {
        // contactRepository.add(new Contact("anom", "089", ""));
        // contactRepository.add(new Contact("bangkit", "081", ""));
        // contactRepository.add(new Contact("ika", "082", "ika@test.com"));

        contactService.showAll();
    }

    @Test
    void testDetail() {
        contactRepository.add(new Contact("ika", "082", "ika@test.com"));
        contactService.detail(64);
    }

    @AfterEach
    void tearDown() {
        dataSource.close();
    }

}
