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

// POJO ����� ��Ʈ�ѷ�
// ������̼� ��� ���ο����� ������̼��� ������ �� �ִ� ������ Ŭ������ �޼����̴�
@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;

	@RequestMapping("/test")
	public String babo() {
		System.out.println("�ٺ� ���� ����");
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

	// ���� �߻� ��, ���ܸ� ó��
	@ExceptionHandler(RegistFailException.class)
	public ModelAndView handleException(RegistFailException e) {
		ModelAndView mav = new ModelAndView("/board/error");
		mav.addObject("err", e);
		return mav;
	}
}