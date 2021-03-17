package com.services.user.service.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

interface LoginRequestCountRepository extends JpaRepository<LoginRequestCountEntity, String>
{
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO LOGIN_REQUEST_COUNT VALUES (:login, 1) ON DUPLICATE KEY UPDATE REQUEST_COUNT=REQUEST_COUNT + 1", nativeQuery = true)
    void updateCountByLogin(String login);
}
