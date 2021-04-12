package org.ecomm.foundation.repo;

import org.ecomm.foundation.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    public List<Order> findOrdersByCustomer_Id(Integer id);

}
