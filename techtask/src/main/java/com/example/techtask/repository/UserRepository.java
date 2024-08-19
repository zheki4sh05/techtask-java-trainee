package com.example.techtask.repository;

import com.example.techtask.model.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = """

 SELECT id, email, user_status
            FROM users u JOIN (select user_id, SUM(price) AS total_amount
                FROM orders o
                where DATE_PART('year', o.created_at) = 2003
                GROUP BY user_id
            ) o ON u.id = o.user_id
            ORDER BY o.total_amount DESC
            LIMIT 1;

""",nativeQuery = true)
    User findUserByOrderYearAndTotalPrice();

    @Query(value = """
select u.* from users u
            join orders o on u.id = o.user_id
            where DATE_PART('year', o.created_at) = 2010 and o.order_status='PAID';

""",nativeQuery = true)
    List<User> findUsersByOrderYearAndStatus();

}
