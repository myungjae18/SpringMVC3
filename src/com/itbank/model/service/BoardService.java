package com.itbank.model.service;

import java.util.List;

import com.itbank.model.domain.Board;

public interface BoardService {
	public int insert(Board board);

	public List selectAll();

	public Board select(int board_id);

	public int update(Board board);

	public int delete(int board_id);
}
