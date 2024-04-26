package com.example.tesinterview.repository;

import com.example.tesinterview.entity.Checklist;
import com.example.tesinterview.entity.ChecklistItem;
import com.example.tesinterview.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChecklistItemRepository extends JpaRepository<ChecklistItem, Long> {
    void deleteByChecklistId(Long id);
    void deleteByChecklist(Checklist checklist);
    void deleteByIdAndChecklistId(Long id, Long checklistId);
    List<ChecklistItem> findByChecklistId(Long id);
}
