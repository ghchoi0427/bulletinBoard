package com.ghchoi0427.repository;

import com.ghchoi0427.domain.Board;
import com.ghchoi0427.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Board findByUser(User user);
}
