package com.example.techtask.repository;

import com.example.techtask.model.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface OrderRepository extends JpaRepository<Order,Integer>{

    @Query(value = """

 select * from orders o
            where o.quantity>1
            ORDER BY o.created_at DESC
            LIMIT 1;

""",nativeQuery = true)
    Order findByQuantityAndDate();

    @Query(value = """
            select o.* from orders o
            join users u on u.id = o.user_id
            where u.user_status = 'ACTIVE'
            ORDER BY o.created_at;
""", nativeQuery = true)
    List<Order> findAllByStatusAndDate();

}
