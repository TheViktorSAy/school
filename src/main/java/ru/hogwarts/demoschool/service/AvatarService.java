package ru.hogwarts.demoschool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.demoschool.model.Avatar;
import ru.hogwarts.demoschool.repository.AvatarRepository;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;

@Service
public class AvatarService {
    private final AvatarRepository avatarRepository;

    @Autowired
    public AvatarService(AvatarRepository avatarRepository) {
        this.avatarRepository = avatarRepository;
    }

    public Avatar saveAvatar(MultipartFile file, Long studentId) throws IOException {
        Avatar avatar = new Avatar();
        avatar.setFilePath(file.getOriginalFilename());
        avatar.setFileSize(file.getSize());
        avatar.setMediaType(file.getContentType());
        avatar.setData(file.getBytes());

        String filePath = "C:\\Users\\vkostenko\\Desktop\\Sky PRO\\" + file.getOriginalFilename();
        saveFileToDisk(filePath, file.getBytes());
        return avatarRepository.save(avatar);
    }

    public Optional<Avatar> getAvatar(Long id) {
        return avatarRepository.findById(id);
    }

    private void saveFileToDisk(String filePath, byte[] data) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(new File(filePath))) {
            fos.write(data);
        }
    }
}