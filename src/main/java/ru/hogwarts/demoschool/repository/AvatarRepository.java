package ru.hogwarts.demoschool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.demoschool.model.Avatar;

public interface AvatarRepository extends JpaRepository<Avatar, Long> {
}