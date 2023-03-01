package de.telran.calendar.controller;

import de.telran.calendar.entity.User;
import de.telran.calendar.entity.UserGroup;
import de.telran.calendar.service.UserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/groups")
public class UserGroupController {

    @Autowired
    private UserGroupService userGroupService;

    @GetMapping("/{id}")
    public ResponseEntity<UserGroup> getUserGroupById(@PathVariable Long id) {

        try {
            return new ResponseEntity<>(userGroupService.getUserGroupById(id), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Long> createUserGroup(@RequestBody UserGroup userGroup) {

        try {
            return new ResponseEntity<>(userGroupService.createUserGroup(userGroup), HttpStatus.CREATED);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUserGroup(@PathVariable Long id, @RequestBody UserGroup userGroup) {
        try {
            userGroupService.updateUserGroup(id, userGroup);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserGroup(@PathVariable Long id) {

        try {
            userGroupService.deleteUserGroup(id);
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("")
    public ResponseEntity<Page<UserGroup>> getUserGroupsByFilter(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "eventId", required = false) Long eventId,
            @RequestParam(required = false) Long userId,
            @PageableDefault(sort = {"createdAt"}, direction = Sort.Direction.DESC) Pageable pageable) {

        try {
            Page<UserGroup> userGroups = userGroupService.getByFilter(name, eventId, userId, pageable);
            return new ResponseEntity<>(userGroups, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
