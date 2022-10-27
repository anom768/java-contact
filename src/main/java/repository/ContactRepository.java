package repository;

import entity.Contact;

public interface ContactRepository {

    void add(Contact contact);
    
    Contact[] getAll();

    Contact getContact(Integer id);

    void deleteById(Integer id);

    void change(Contact contact);

    void deleteAll();

}
