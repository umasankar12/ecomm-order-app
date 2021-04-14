package org.ecomm.posclient;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.ecomm.foundation.model.Order;

import javax.inject.Named;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Named
public class BulkOrderReader {

    private List<Order> fetchOrderFromFileContent(String content) {
        try {
            return new ObjectMapper()
                    .readValue(content, new TypeReference<List<Order>>() {
                    });

        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public Optional<List<Order>> readOrders(final String filePath) throws Exception {
        return Files.lines(Path.of(filePath))
                .reduce(String::concat)
                .map(this::fetchOrderFromFileContent)
                .filter(Predicate.not(List::isEmpty));
    }
}
