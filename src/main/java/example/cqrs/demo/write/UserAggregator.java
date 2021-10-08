package example.cqrs.demo.write;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import example.cqrs.demo.write.domain.User;
import example.cqrs.demo.write.command.CreateUserCommand;
import example.cqrs.demo.write.command.UpdateUserCommand;
import example.cqrs.demo.write.repository.WriteRepository;

@Component
public class UserAggregator {

    @Autowired
    private WriteRepository writeRepository;

    public User aggregate(CreateUserCommand command) {
        User user = new User(command.getUserId(), command.getFirstName(), command.getLastName());
        user.setAddresses(Set.of());
        user.setContacts(Set.of());
        writeRepository.addUser(user.getUserid(), user);
        return user;
    }

    public User aggregate(UpdateUserCommand command) {
        User user = writeRepository.getUser(command.getUserId());
        user.setAddresses(command.getAddresses());
        user.setContacts(command.getContacts());
        writeRepository.addUser(user.getUserid(), user);
        return user;
    }
}
