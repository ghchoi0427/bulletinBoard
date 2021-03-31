package com.ghchoi0427.service;

import org.hibernate.service.spi.InjectService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class BoardServiceImplement implements BoardService {

    @InjectService
    BoardDAO boardDao;

    @Override
    public void create(BoardVO vo) throws Exception {
        String title = vo.getTitle();
        String content = vo.getContent();
        String writer = vo.getWriter();

        title = title.replace("<", "&lt;");
        title = title.replace(">", "&gt;");
        writer = writer.replace("<", "&lt");
        writer = writer.replace(">", "&gt");

        title = title.replace(" ", "&nbsp;&nbsp;");
        writer = writer.replace(" ", "&nbsp;&nbsp;");

        content = content.replace("\n", "<br>");
        vo.setTitle(title);
        vo.setContent(content);
        vo.setWriter(writer);
        boardDao.create(vo);
    }

    @Override
    public BoardVO read(int bno) throws Exception {
        return boardDao.read(bno);
    }

    @Override
    public void update(BoardVO vo) throws Exception {
        boardDao.update(vo);
    }

    @Override
    public void delete(int bno) throws Exception {
        boardDao.delete(dno);
    }

    @Override
    public List<BoardVO> listAll() throws Exception {
        return boardDao.listAll();
    }

    @Override
    public void increaseViewcnt(int bno, HttpSession session) throws Exception {
        long update_time = 0;

        if (session.getAttribute("update_time_" + bno) != null) {
            update_time = (long) session.getAttribute("update_time_" + bno);
        }

        long current_time = System.currentTimeMillis();

        if (current_time - update_time > 5 * 1000) {
            boardDao.increaseViewcnt(bno);
            session.setAttribute("update_time_" + bno, current_time);
        }
    }

}
