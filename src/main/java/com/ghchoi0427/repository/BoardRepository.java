package com.ghchoi0427.repository;

import com.ghchoi0427.domain.User;

public interface BoardRepository extends JpaRepository<Board, Long>{
    Board findByUser(User user);
}
