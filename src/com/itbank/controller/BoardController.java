package com.itbank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.itbank.exception.RegistFailException;
import com.itbank.model.domain.Board;
import com.itbank.model.service.BoardService;

// POJO 기반의 컨트롤러
// 어노테이션 기반 매핑에서는 어노테이션을 지정할 수 있는 영역이 클래스와 메서드이다
@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;

	@RequestMapping("/test")
	public String babo() {
		System.out.println("바보 가동 성공");
		return "babo";
	}

	@RequestMapping(value = "/board/list", method = RequestMethod.GET)
	public ModelAndView selectAll() {
		ModelAndView mav = new ModelAndView();

		List boardList = boardService.selectAll();
		mav.addObject("boardList", boardList);

		return mav;
	}

	@RequestMapping(value = "/board/write", method = RequestMethod.POST)
	public ModelAndView insert(Board board) {
		ModelAndView mav = new ModelAndView();

		boardService.insert(board);
		mav.setViewName("redirect:/board/list");

		return mav;
	}

	@RequestMapping(value = "/board/detail", method = RequestMethod.GET)
	public ModelAndView select(int board_id) {
		ModelAndView mav = new ModelAndView();

		Board board = boardService.select(board_id);
		mav.addObject("board", board);

		return mav;
	}

	@RequestMapping(value = "/board/delete", method = RequestMethod.POST)
	public String delete(int board_id) {
		boardService.delete(board_id);
		return "redirect:/board/list";
	}

	@RequestMapping(value = "/board/update", method = RequestMethod.POST)
	public String update(Board board) {
		boardService.update(board);
		return "redirect:/board/detail?board_id=" + board.getBoard_id();
	}

	// 예외 발생 시, 예외를 처리
	@ExceptionHandler(RegistFailException.class)
	public ModelAndView handleException(RegistFailException e) {
		ModelAndView mav = new ModelAndView("/board/error");
		mav.addObject("err", e);
		return mav;
	}
}