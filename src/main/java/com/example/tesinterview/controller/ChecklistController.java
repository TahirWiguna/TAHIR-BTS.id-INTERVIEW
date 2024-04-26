package com.example.tesinterview.controller;

import com.example.tesinterview.Response;
import com.example.tesinterview.ValidationHelper;
import com.example.tesinterview.entity.Checklist;
import com.example.tesinterview.entity.User;
import com.example.tesinterview.payload.ChecklistCreateItemRequest;
import com.example.tesinterview.payload.ChecklistCreateRequest;
import com.example.tesinterview.service.ChecklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class ChecklistController {

    @Autowired
    private ChecklistService checklistService;

    @Autowired
    private ValidationHelper validator;

    @GetMapping("/checklist")
    public ResponseEntity<Response> getChecklist(User user) {
        List<Checklist> res =  checklistService.getChecklist(user);
        return ResponseEntity.ok().body(Response.<List<Checklist>>builder().message(res).build());
    }

    @PostMapping("/checklist")
    public ResponseEntity<Response> createChecklist(@RequestBody ChecklistCreateRequest request, User user) {
        validator.validate(request);

        Checklist res = checklistService.createChecklist(request, user);
        return ResponseEntity.ok().body(Response.<Checklist>builder().message(res).build());
    }

    @DeleteMapping("/checklist/{id}")
    public ResponseEntity<Response> deleteChecklist(@PathVariable Long id, User user) {
        checklistService.deleteChecklist(id, user);
        return ResponseEntity.ok().body(Response.<String>builder().message("Deleted").build());
    }





    @GetMapping("/checklist/{id}/item")
    public ResponseEntity<Response> getChecklistItem(User user, @PathVariable Long id) {
        List<Checklist> res =  checklistService.getChecklist(user);
        return ResponseEntity.ok().body(Response.<List<Checklist>>builder().message(res).build());
    }

    @PostMapping("/checklist/{id}/item")
    public ResponseEntity<Response> addCheckListItem(@PathVariable Long id, @RequestBody ChecklistCreateItemRequest request, User user) {
        validator.validate(request);

        checklistService.addCheckListItem(id, user, request);
        return ResponseEntity.ok().body(Response.<String>builder().message("Success").build());
    }

    @GetMapping("/checklist/{id}/item/{itemId}")
    public ResponseEntity<Response> getChecklistItemByChecklistId(User user, @PathVariable Long id) {
        List<Checklist> res =  checklistService.getChecklist(user);
        return ResponseEntity.ok().body(Response.<List<Checklist>>builder().message(res).build());
    }

    @PutMapping("/checklist/{id}/item/{itemId}")
    public ResponseEntity<Response> updateChecked(User user, @PathVariable Long id) {
        checklistService.updateChecked(user, id);
        return ResponseEntity.ok().body(Response.<String>builder().message("Success").build());
    }

    @DeleteMapping("/checklist/{id}/item/{itemId}")
    public ResponseEntity<Response> deleteCheckListItem(@PathVariable Long id, User user) {
        checklistService.deleteCheckListItem(id, user);
        return ResponseEntity.ok().body(Response.<String>builder().message("Deleted").build());
    }

//    @PutMapping("/checklist/{id}/item/{itemId}")
//    public ResponseEntity<Response> renameChecklistItem(User user, @PathVariable Long id, @RequestBody String name) {
//        checklistService.renameChecklistItem(user, id, name);
//        return ResponseEntity.ok().body(Response.<String>builder().message("Success").build());
//    }

}
