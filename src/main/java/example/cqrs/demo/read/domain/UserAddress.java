package example.cqrs.demo.read.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import example.cqrs.demo.write.domain.Address;

public class UserAddress {
    private Map<String, Set<Address>> addressByRegion = new HashMap<>();

    public Map<String, Set<Address>> getAddressByRegion() {
        return addressByRegion;
    }

    public void setAddressByRegion(Map<String, Set<Address>> addressByRegion) {
        this.addressByRegion = addressByRegion;
    }
}
