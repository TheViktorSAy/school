package ru.hogwarts.demoschool.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.demoschool.model.Avatar;
import ru.hogwarts.demoschool.service.AvatarService;

import java.io.IOException;

@RestController
@RequestMapping("/avatar")
public class AvatarController {
    private final AvatarService avatarService;

    @Autowired
    public AvatarController(AvatarService avatarService) {
        this.avatarService = avatarService;
    }

    @Operation(summary = "Upload an avatar for a student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Avatar uploaded successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/upload")
    public ResponseEntity<Avatar> uploadAvatar(
            @Parameter(description = "The file to upload", required = true) @RequestParam("file") MultipartFile file,
            @Parameter(description = "The ID of the student", required = true) @RequestParam Long studentId) {
        try {
            Avatar avatar = avatarService.saveAvatar(file, studentId);
            return new ResponseEntity<>(avatar, HttpStatus.CREATED);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getAvatar(@PathVariable Long id) {
        return avatarService.getAvatar(id)
                .map(avatar -> ResponseEntity.ok()
                        .contentType(org.springframework.http.MediaType.parseMediaType(avatar.getMediaType()))
                        .body(avatar.getData()))
                .orElse(ResponseEntity.notFound().build());
    }
}