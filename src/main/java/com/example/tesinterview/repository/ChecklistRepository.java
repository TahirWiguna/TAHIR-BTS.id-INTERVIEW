package com.example.tesinterview.repository;

import com.example.tesinterview.entity.Checklist;
import com.example.tesinterview.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChecklistRepository extends JpaRepository<Checklist, Long>{
    List<Checklist> findByUser(User user);
    Optional<Checklist> findByIdAndUser(Long id, User user);

}
