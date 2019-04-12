package com.itbank.model.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.itbank.model.domain.Board;

@Repository
public class MybatisBoardDAO implements BoardDAO {
	private SqlSessionTemplate sessionTemplate;

	public void setSessionTemplate(SqlSessionTemplate sessionTemplate) {
		this.sessionTemplate = sessionTemplate;
	}

	@Override
	public int insert(Board board) {
		return sessionTemplate.insert("Board.insert", board);
	}

	@Override
	public List selectAll() {
		return sessionTemplate.selectList("Board.selectAll");
	}

	@Override
	public Board select(int board_id) {
		return sessionTemplate.selectOne("Board.select", board_id);
	}

	@Override
	public int update(Board board) {
		return sessionTemplate.update("Board.update", board);
	}

	@Override
	public int delete(int board_id) {
		return sessionTemplate.delete("Board.delete", board_id);
	}
}
