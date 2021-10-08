package example.cqrs.demo.write.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import example.cqrs.demo.write.domain.User;

@Component
public class WriteRepository {

    private final Map<String, User> inMemoryRepository = new HashMap<>();

    public void addUser(String userid, User user) {
        synchronized (inMemoryRepository) {
            inMemoryRepository.put(userid, user);
        }
    }

    public User getUser(String userId) {
        synchronized (inMemoryRepository) {
            return inMemoryRepository.get(userId);
        }
    }
}
