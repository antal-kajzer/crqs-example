package example.cqrs.demo.read.projection;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import example.cqrs.demo.read.domain.UserAddress;
import example.cqrs.demo.read.domain.UserContact;
import example.cqrs.demo.read.query.AddressByRegionQuery;
import example.cqrs.demo.read.query.ContactByTypeQuery;
import example.cqrs.demo.read.repository.ReadRepository;
import example.cqrs.demo.write.domain.Address;
import example.cqrs.demo.write.domain.Contact;

@Component
public class UserProjection {

    @Autowired
    private ReadRepository readRepository;

    public Set<Contact> handle(ContactByTypeQuery query) {
        UserContact userContact = readRepository.getUserContact(query.getUserId());
        return userContact.getContactByType()
            .get(query.getContactType());
    }

    public Set<Address> handle(AddressByRegionQuery query) {
        UserAddress userAddress = readRepository.getUserAddress(query.getUserId());
        return userAddress.getAddressByRegion()
            .get(query.getState());
    }

}
