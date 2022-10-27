import javax.sql.DataSource;

import repository.ContactRepository;
import repository.ContactRepositoryImpl;
import service.ContactService;
import service.ContactServiceImpl;
import util.DatabaseUtil;
import view.ContactView;

public class Application {
    
    public static void main(String[] args) {
        
        DataSource dataSource = DatabaseUtil.getDataSource();
        ContactRepository contactRepository = new ContactRepositoryImpl(dataSource);
        ContactService contactService = new ContactServiceImpl(contactRepository);
        ContactView contactView = new ContactView(contactService);

        contactView.showAll();
    }

}
