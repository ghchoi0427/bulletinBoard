package com.ghchoi0427.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/board/*")
public class BoardController {

    //게시글 목록
    @RequestMapping("list.do")
    public ModelAndView list() throws Exception {
        List<Board> list = boardService.listAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("board/list");
        modelAndView.addAllObjects("list", list);
        return modelAndView;
    }

    //게시글 작성 화면
    @RequestMapping(value = "write.do", method = RequestMethod.GET)
    public String write() {
        return "board/write";
    }

    //게시글 작성처리
    @RequestMapping(value = "insert.do", method = RequestMethod.POST)
    public String insert(@ModelAttribute BoardVO vo) throws Exception {
        boardService.create(vo);
        return "redirect:list.do";
    }

    //게시글 상세내용 조회
    @RequestMapping(value = "view.do", method = RequestMethod.GET)
    public ModelAndView view(@RequestParam int bno, HttpSession httpSession) throws Exception {
        boardService.increaseViewCnt(bno, session);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("board/view");
        modelAndView.addObject("dto", boardService.read(bno));
        return modelAndView;
    }

    @RequestMapping(value = "update.do", method = RequestMethod.POST)
    public String update(@ModelAttribute BoardVO vo) throws Exception {
        boardService.update(vo);
        return "redirect:list.do";
    }

    @RequestMapping("delete.do")
    public String delete(@RequestParam int bno) throws Exception {
        boardService.delete(bno);
        return "redirect:list.do";
    }
}
