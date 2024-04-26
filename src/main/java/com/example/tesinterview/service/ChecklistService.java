package com.example.tesinterview.service;

import com.example.tesinterview.entity.Checklist;
import com.example.tesinterview.entity.ChecklistItem;
import com.example.tesinterview.entity.User;
import com.example.tesinterview.payload.ChecklistCreateItemRequest;
import com.example.tesinterview.payload.ChecklistCreateRequest;
import com.example.tesinterview.repository.ChecklistItemRepository;
import com.example.tesinterview.repository.ChecklistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ChecklistService {
    @Autowired
    private ChecklistRepository checklistRepository;

    @Autowired
    private ChecklistItemRepository checklistItemRepository;

    public List<Checklist> getChecklist(User user) {
        Checklist checklist = new Checklist();
        return checklistRepository.findByUser(user);
    }

    public Checklist createChecklist(ChecklistCreateRequest request, User user) {
        Checklist checklist = new Checklist();
        checklist.setName(request.getName());
        checklist.setUser(user);
        return checklistRepository.save(checklist);
    }

    public void deleteChecklist(Long id, User user) {
        Checklist checklist = checklistRepository.findByIdAndUser(id, user).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Checklist not found"));
        checklistItemRepository.deleteByChecklist(checklist);
        checklistRepository.delete(checklist);
    }

    public void addCheckListItem(Long id, User user, ChecklistCreateItemRequest request) {
        Checklist checklist = checklistRepository.findByIdAndUser(id, user).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Checklist not found"));
        ChecklistItem checklistItem = new ChecklistItem();
        checklistItem.setName(request.getName());
        checklistItemRepository.save(checklistItem);
    }

    public void deleteCheckListItem(Long id, User user) {
        ChecklistItem checklistItem = checklistItemRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Checklist item not found"));
        checklistItemRepository.delete(checklistItem);
    }

    public ChecklistItem getChecklistItem(User user, Long id) {
        return checklistItemRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Checklist item not found"));
    }

    public List<ChecklistItem> getChecklistItemByChecklistId(User user, Long id) {
        return checklistItemRepository.findByChecklistId(id);
    }

    public void updateChecked(User user, Long id) {
        ChecklistItem checklistItem = checklistItemRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Checklist item not found"));
        checklistItem.setChecked(true);
        checklistItemRepository.save(checklistItem);
    }

//    public void renameChecklistItem(User user, Long id, String name) {
//        ChecklistItem checklistItem = checklistItemRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Checklist item not found"));
//        checklistItem.setName(name);
//        checklistItemRepository.save(checklistItem);
//    }
}
