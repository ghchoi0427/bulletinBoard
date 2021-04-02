package com.ghchoi0427;

import com.ghchoi0427.domain.User;
import com.ghchoi0427.domain.Board;
import com.ghchoi0427.domain.enums.BoardType;
import com.ghchoi0427.repository.BoardRepository;
import com.ghchoi0427.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

public class AppRunner implements ApplicationRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BoardRepository boardRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        User user = userRepository.save(User.builder()
                .name("ghchoi0427")
                .email("ghchoi0427@gmail.com")
                .createdDate(LocalDateTime.now())
                .build());

        IntStream.rangeClosed(1, 200).forEach(index ->
                boardRepository.save(Board.builder()
                        .title("Content " + index)
                        .subTitle("Order " + index)
                        .content("Content Example " + index)
                        .boardType(BoardType.free)
                        .createdDate(LocalDateTime.now())
                        .updatedDate(LocalDateTime.now())
                        .user(user).build()));

    }
}
