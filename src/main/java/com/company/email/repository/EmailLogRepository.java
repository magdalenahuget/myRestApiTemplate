package com.company.email.repository;

import com.company.email.model.EmailLog;
import org.springframework.data.jpa.repository.JpaRepository;

//zapisywać jakieś dane dotyczące e-maili w bazie danych (np. logi, potwierdzenia),
public interface EmailLogRepository extends JpaRepository<EmailLog, Long> {
}