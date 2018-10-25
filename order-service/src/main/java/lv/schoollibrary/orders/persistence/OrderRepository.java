package lv.schoollibrary.orders.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

import lv.schoollibrary.orders.domain.Order;

public interface OrderRepository extends MongoRepository<Order, String> {

    List<Order> findBySchoolKidId(String schoolKidId);

}
