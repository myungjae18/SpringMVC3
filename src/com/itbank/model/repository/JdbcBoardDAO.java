package com.itbank.model.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.itbank.common.board.BoardMapper;
import com.itbank.exception.RegistFailException;
import com.itbank.model.domain.Board;

@Repository
public class JdbcBoardDAO implements BoardDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int insert(Board board) throws RegistFailException {
		String sql = "insert into board(board_id, writer, title, content)";
		sql += " values(seq_board.nextval, ?, ?, ?)";
		int result = jdbcTemplate.update(sql, board.getWriter(), board.getTitle(), board.getContent());
		return result;
	}

	@Override
	public List selectAll() {
		List boardList = null;

		String sql = "select * from board order by board_id desc";
		boardList = jdbcTemplate.query(sql, new BoardMapper());

		return boardList;
	}

	@Override
	public Board select(int board_id) {
		Board board = null;

		String sql = "select * from board where board_id=?";
		board = jdbcTemplate.queryForObject(sql, new BoardMapper(), board_id);

		return board;
	}

	@Override
	public int update(Board board) {
		String sql = "update board set writer=?, title=?, content=? where board_id=?";
		int result = jdbcTemplate.update(sql, board.getWriter(), board.getTitle(), board.getContent(),
				board.getBoard_id());
		return result;
	}

	@Override
	public int delete(int board_id) {
		String sql = "delete from board where board_id=?";
		int result = jdbcTemplate.update(sql, board_id);
		return result;
	}
}