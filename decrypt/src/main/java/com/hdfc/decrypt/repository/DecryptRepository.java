package com.hdfc.decrypt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hdfc.decrypt.entity.DecryptData;

@Repository
public interface DecryptRepository  extends JpaRepository<DecryptData,Long> {
 //   Decrypt findByDecryptId(Long decryptId);
}
