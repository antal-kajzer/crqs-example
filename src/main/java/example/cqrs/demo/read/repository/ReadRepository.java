package example.cqrs.demo.read.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import example.cqrs.demo.read.domain.UserAddress;
import example.cqrs.demo.read.domain.UserContact;

@Component
public class ReadRepository {
    private Map<String, UserAddress> userAddress = new HashMap<>();
    private Map<String, UserContact> userContact = new HashMap<>();

    public UserContact getUserContact(String userId) {
        synchronized (userContact) {
            return userContact.get(userId);
        }
    }

    public UserAddress getUserAddress(String userId) {
        synchronized (userAddress) {
            return userAddress.get(userId);
        }
    }

    public void addUserContact(String userid, UserContact userContact) {
        synchronized (this.userContact) {
            this.userContact.put(userid, userContact);
        }
    }

    public void addUserAddress(String userid, UserAddress userAddress) {
        synchronized (this.userAddress) {
            this.userAddress.put(userid, userAddress);
        }
    }
}
