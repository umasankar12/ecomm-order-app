package org.ecomm.foundation.repo;

import static org.assertj.core.api.Assertions.assertThat;

import org.ecomm.foundation.JpaTestConfiguration;
import org.ecomm.foundation.model.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@Import(JpaTestConfiguration.class)
public class AddressRepositoryTest {

    @Inject
    AddressRepository addressRepository;

    @Test
    public void testRepositoryInjected() {
        assertThat(addressRepository).isNotNull();
    }

    @Test
    public void testAddressCreated() {
        Address address = addressRepository.save(new Address()
          .setLine1("l1")
          .setLine2("l2")
          .setCity("manhattan")
          .setState("NY")
          .setCountry("USA")
          .setZipcode("10101")
          .setNickname("Test")
        );
        assertThat(address.getId()).isEqualTo(1);
    }

}