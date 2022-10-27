package service;

import entity.Contact;

public interface ContactService {

    void add(Contact contact);
    
    void showAll();

    void detail(Integer id);

    void edit(Contact contact);

    void delete(Integer id);

}
