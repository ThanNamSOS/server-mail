package com.example.sendmail.repository;


import com.example.sendmail.entity.mail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MailRepository extends JpaRepository<mail,Integer> {
}
