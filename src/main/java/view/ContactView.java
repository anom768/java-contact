package view;

import entity.Contact;
import service.ContactService;
import util.InputUtil;

public class ContactView {

    private ContactService contactService;

    public ContactView(ContactService contactService) {
        this.contactService = contactService;
    }

    public void showAll() {
        while (true) {
            System.out.println("\n=============== C O N T A C T ===============");
            contactService.showAll();
            
            System.out.println("");
            System.out.println("[a] Add");
            System.out.println("[b] Show Detail");
            System.out.println("[x] Exit");

            String dataIn = InputUtil.input("Choose Menu").toLowerCase();

            if (dataIn.equals("a")) {
                addContact();
            } else if (dataIn.equals("b")) {
                detailContact();
            } else if (dataIn.equals("x")) {
                System.exit(0);
            } else {
                System.out.println("[!] INVALID INPUT");
            }
        }
    }
    
    public void addContact() {
        System.out.println("\n============ A D D C O N T A C T ============");
        
        Contact contact = new Contact();
        contact.setName(InputUtil.input("[+] Input name"));
        contact.setPhone(InputUtil.input("[+] Input phone"));
        contact.setEmail(InputUtil.input("[+] Input email"));
        
        contactService.add(contact);
        
    }
    
    public void detailContact() {
        System.out.println("\n============ D E T A I L I N F O ============");
        
        Integer id = Integer.valueOf(InputUtil.input("[+] Choose number"));
        
        while (true) {
            contactService.detail(id);
            System.out.println("\n[a] Edit");
            System.out.println("[b] Delete");
            System.out.println("[x] Back");

            String dataIn = InputUtil.input("Choose Menu").toLowerCase();

                if (dataIn.equals("a")) {
                    edit(id);
                } else if (dataIn.equals("b")) {
                    delete(id);
                    break;
                } else if (dataIn.equals("x")) {
                    break;
                } else {
                    System.out.println("[!] INVALID INPUT");
                }
            }
    }
    
    public void edit(Integer id) {
        System.out.println("\n=========== E D I T C O N T A C T ===========");
        
        Contact contact = new Contact();
        contact.setId(id);
        contact.setName(InputUtil.input("[+] Input name"));
        contact.setPhone(InputUtil.input("[+] Input phone"));
        contact.setEmail(InputUtil.input("[+] Input email\n"));
        contactService.edit(contact);
    }
    
    public void delete(Integer id) {
        System.out.println("\n========= D E L E T E C O N T A C T =========");

        while (true) {
            String option = InputUtil.input("[?] Are you sure delete this contact ? (y/n)").toLowerCase();

            if (option.equals("y")) {
                contactService.delete(id);
                break;
            } else if (option.equals("n")) {
                break;
            } else {
                System.out.println("[!] INVALID INPUT");
            }
        }

    }

}

