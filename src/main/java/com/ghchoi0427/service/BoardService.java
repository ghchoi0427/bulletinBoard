package com.ghchoi0427.service;

import javax.servlet.http.HttpSession;

public interface BoardService {

    public void create(BoardVO vo) throws Exception;

    public BoardVO read(int bno) throws Exception;

    public void update(BoardVO vo) throws Exception;

    public void delete(int bno) throws Exception;

    public List<BoardVO> listAll() throws Exception;

    public void increaseViewcnt(int bno, HttpSession session) throws Exception;
}
