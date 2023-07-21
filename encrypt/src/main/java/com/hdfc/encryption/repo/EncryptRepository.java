package com.hdfc.encryption.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hdfc.encryption.entity.Encrypt;

@Repository
public interface EncryptRepository extends JpaRepository<Encrypt, Long> {

//    Encrypt findByEncryptId(Long encryptId);
}
