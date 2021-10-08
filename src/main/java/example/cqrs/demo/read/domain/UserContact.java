package example.cqrs.demo.read.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import example.cqrs.demo.write.domain.Contact;

public class UserContact {
    private Map<String, Set<Contact>> contactByType = new HashMap<>();

    public Map<String, Set<Contact>> getContactByType() {
        return contactByType;
    }

    public void setContactByType(Map<String, Set<Contact>> contactByType) {
        this.contactByType = contactByType;
    }
}
