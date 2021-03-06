package com.springbook.biz.board;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

// for revert
@XmlRootElement(name = "boardList")
@XmlAccessorType(XmlAccessType.FIELD)
public class BoardListVo {
	@XmlElement(name = "board")
	private List<BoardVo> boardList;

	public List<BoardVo> getBoardList() {
		return boardList;
	}

	public void setBoardList(List<BoardVo> boardList) {
		this.boardList = boardList;
	}
}
