package example.cqrs.demo.write.projector;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import example.cqrs.demo.read.domain.UserAddress;
import example.cqrs.demo.read.domain.UserContact;
import example.cqrs.demo.read.repository.ReadRepository;
import example.cqrs.demo.write.domain.Address;
import example.cqrs.demo.write.domain.Contact;
import example.cqrs.demo.write.domain.User;

@Component
public class UserProjector {

    @Autowired
    private ReadRepository readRepository;

    public void project(User user) {
        UserContact userContact = Optional.ofNullable(
            readRepository.getUserContact(user.getUserid()))
            .orElse(new UserContact());
        Map<String, Set<Contact>> contactByType = new HashMap<>();
        for (Contact contact : user.getContacts()) {
            Set<Contact> contacts = Optional.ofNullable(
                contactByType.get(contact.getType()))
                .orElse(new HashSet<>());
            contacts.add(contact);
            contactByType.put(contact.getType(), contacts);
        }
        userContact.setContactByType(contactByType);
        readRepository.addUserContact(user.getUserid(), userContact);

        UserAddress userAddress = Optional.ofNullable(
            readRepository.getUserAddress(user.getUserid()))
            .orElse(new UserAddress());
        Map<String, Set<Address>> addressByRegion = new HashMap<>();
        for (Address address : user.getAddresses()) {
            Set<Address> addresses = Optional.ofNullable(
                addressByRegion.get(address.getState()))
                .orElse(new HashSet<>());
            addresses.add(address);
            addressByRegion.put(address.getState(), addresses);
        }
        userAddress.setAddressByRegion(addressByRegion);
        readRepository.addUserAddress(user.getUserid(), userAddress);
    }

}
