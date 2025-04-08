package ru.hogwarts.demoschool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.demoschool.model.Avatar;
import ru.hogwarts.demoschool.model.Student;
import ru.hogwarts.demoschool.repository.AvatarRepository;
import ru.hogwarts.demoschool.repository.StudentRepository;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class AvatarService {
    private final AvatarRepository avatarRepository;
    private final StudentRepository studentRepository;
    private static final Logger logger = LoggerFactory.getLogger(AvatarService.class);

    @Autowired
    public AvatarService(AvatarRepository avatarRepository, StudentRepository studentRepository) {
        this.avatarRepository = avatarRepository;
        this.studentRepository = studentRepository;
    }

    public Avatar saveAvatar(MultipartFile file, Long studentId) throws IOException {
        Avatar avatar = new Avatar();
        avatar.setFilePath(file.getOriginalFilename());
        avatar.setFileSize(file.getSize());
        avatar.setMediaType(file.getContentType());
        avatar.setData(file.getBytes());

        // Сохранение файла на диск
        String filePath = "C:\\Users\\vkostenko\\Desktop\\Sky PRO\\" + file.getOriginalFilename();
        saveFileToDisk(filePath, file.getBytes());

        // Связываем аватар со студентом
        Optional<Student> studentOpt = studentRepository.findById(studentId);
        studentOpt.ifPresent(avatar::setStudent);

        return avatarRepository.save(avatar);
    }

    public Optional<Avatar> getAvatar(Long id) {
        return avatarRepository.findById(id);
    }

    public Page<Avatar> getAllAvatars(Pageable pageable) {
        return avatarRepository.findAll(pageable);
    }

    public boolean deleteAvatar(Long id) {
        if (avatarRepository.existsById(id)) {
            avatarRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private void saveFileToDisk(String filePath, byte[] data) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(new File(filePath))) {
            fos.write(data);
        }
    }
}