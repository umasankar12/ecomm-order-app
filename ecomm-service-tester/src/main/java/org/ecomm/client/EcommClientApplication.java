package org.ecomm.client;

import org.ecomm.foundation.EcommJpaConfiguration;
import org.ecomm.foundation.model.Item;
import org.ecomm.foundation.repo.ItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.inject.Inject;
import java.util.Optional;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"org.ecomm.foundation.repo"})
@EntityScan(basePackages = {"org.ecomm.foundation.model"})
@Import(EcommJpaConfiguration.class)
public class EcommClientApplication implements CommandLineRunner {

    @Inject
    ItemRepository itemRepository;

    @Inject
    ItemService itemService;

    public static void main(String[] args) {
        SpringApplication.run(EcommClientApplication.class);
    }


    @Override
    public void run(String... args) throws Exception {

        Optional<Item> nikeshoe1 = itemRepository.findFirstByCode("1212");
        System.out.printf("shoe = %s", nikeshoe1.get());

        System.out.println(itemRepository.findById(1).get());
        System.out.println(itemService.findItemByItemCode("1212"));
    }
}
