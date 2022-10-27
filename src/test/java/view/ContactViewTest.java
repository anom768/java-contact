package view;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.zaxxer.hikari.HikariDataSource;

import repository.ContactRepository;
import repository.ContactRepositoryImpl;
import service.ContactService;
import service.ContactServiceImpl;
import util.DatabaseUtil;

public class ContactViewTest {

    private static HikariDataSource dataSource;
    private static ContactRepository contactRepository;
    private static ContactService contactService;
    private static ContactView contactView;

    public static void main(String[] args) {
        testShowAll();
    }
    
    @BeforeEach
    void setUp () {
        dataSource = DatabaseUtil.getDataSource();
        contactRepository = new ContactRepositoryImpl(dataSource);
        contactService = new ContactServiceImpl(contactRepository);
        contactView = new ContactView(contactService);

        contactRepository.deleteAll();
    }

    @Test
    static void testShowAll() {
        contactView.showAll();
    }

    @AfterEach
    void tearDown() {
        dataSource.close();
    }

}
