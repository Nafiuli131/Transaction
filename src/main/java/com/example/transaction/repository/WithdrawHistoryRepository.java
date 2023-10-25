package com.example.transaction.repository;

import com.example.transaction.entity.WithdrawHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WithdrawHistoryRepository extends JpaRepository<WithdrawHistory, Long> {
}
