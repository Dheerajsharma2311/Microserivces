package com.decrypt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.decrypt.entity.Decrypt;

@Repository
public interface DecryptRepository  extends JpaRepository<Decrypt,Long> {
    Decrypt findByDecryptId(Long decryptId);
}
