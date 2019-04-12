package com.itbank.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.itbank.model.domain.Board;
import com.itbank.model.repository.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	@Qualifier("jdbcBoardDAO")
	private BoardDAO boardDAO;

	@Override
	public int insert(Board board) {
		return boardDAO.insert(board);
	}

	@Override
	public List selectAll() {
		return boardDAO.selectAll();
	}

	@Override
	public Board select(int board_id) {
		return boardDAO.select(board_id);
	}

	@Override
	public int update(Board board) {
		return boardDAO.update(board);
	}

	@Override
	public int delete(int board_id) {
		return boardDAO.delete(board_id);
	}

}
