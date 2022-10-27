package service;

import entity.Contact;
import repository.ContactRepository;

public class ContactServiceImpl implements ContactService {
    
    private ContactRepository contactRepository;

    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public void add(Contact contact) {
        contactRepository.add(contact);
    }

    @Override
    public void showAll() {
        Contact[] contacts = contactRepository.getAll();
        for (Contact contact : contacts) {
            System.out.println("[" + contact.getId() + "] " + contact.getName());
        }
    }

    @Override
    public void edit(Contact contact) {
        contactRepository.change(contact);
    }

    @Override
    public void detail(Integer id) {
        Contact contact = contactRepository.getContact(id);
        System.out.println("[" + contact.getId() + "]   Name    : " + contact.getName());
        System.out.println("       Phone   : " + contact.getPhone());
        System.out.println("       Email   : " + contact.getEmail());
    }

    @Override
    public void delete(Integer id) {
        contactRepository.deleteById(id);
    }

}
