package com.springbook.biz.board;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class BoardServiceClient {
	public static void main(String[] args) {
		AbstractApplicationContext container = new GenericXmlApplicationContext("applicationContext.xml");
		
		BoardService boardService = (BoardService)container.getBean("boardService");

		// 3. 글 등록 기능 테스트
		BoardVo vo = new BoardVo();
//		vo.setSeq(1);
		vo.setTitle("후하");
		vo.setWriter("홍길동");;
		vo.setContent("임시 내용........");
		boardService.insertBoard(vo);
		
//		List<BoardVo> boardList = boardService.getBoardList(vo);
//		for(BoardVo board : boardList) {
//			System.out.println(board);
//		}
		
		container.close();
	}
}
