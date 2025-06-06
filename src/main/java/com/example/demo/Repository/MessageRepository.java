package com.example.demo.Repository;

import com.example.demo.Model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByUserId(Long id);
    Message findBySlug(String slug);

}
