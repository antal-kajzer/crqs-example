package example.cqrs.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import example.cqrs.demo.read.domain.UserAddress;
import example.cqrs.demo.read.domain.UserContact;
import example.cqrs.demo.read.repository.ReadRepository;
import example.cqrs.demo.write.UserAggregator;
import example.cqrs.demo.write.command.CreateUserCommand;
import example.cqrs.demo.write.command.UpdateUserCommand;
import example.cqrs.demo.write.domain.User;
import example.cqrs.demo.write.projector.UserProjector;

@RestController
public class Controller {

    @Autowired
    private ReadRepository readRepository;
    @Autowired
    private UserProjector userProjector;
    @Autowired
    private UserAggregator userAggregator;

    @GetMapping("/user/{userId}/contacts")
    public UserContact userContacts(@PathVariable String userId) {
        return readRepository.getUserContact(userId);
    }

    @GetMapping("/user/{userId}/addresses")
    public UserAddress userAddresses(@PathVariable String userId) {
        return readRepository.getUserAddress(userId);
    }

    @PostMapping("/create")
    public User createUser(@RequestBody CreateUserCommand createUserCommand) {
        User aggregate = userAggregator.aggregate(createUserCommand);
        userProjector.project(aggregate);

        return aggregate;
    }

    @PostMapping("/update")
    public User updateUser(@RequestBody UpdateUserCommand updateUserCommand) {
        User aggregate = userAggregator.aggregate(updateUserCommand);
        userProjector.project(aggregate);

        return aggregate;
    }
}
