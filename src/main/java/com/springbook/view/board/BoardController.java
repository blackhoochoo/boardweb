package com.springbook.view.board;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.springbook.biz.board.BoardListVo;
import com.springbook.biz.board.BoardService;
import com.springbook.biz.board.BoardVo;
import com.springbook.biz.board.impl.BoardDao;

// "board"라는 이름으로 SessionAttributes를 지정한다. 추후 ModelAttribute에 값이 없는 경우 이 값에서 보충 된다.
@SessionAttributes("board")
@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	// json 변환
	/*
	@RequestMapping("/dataTransform.do")
	@ResponseBody
	public List<BoardVo> dataTransform(BoardVo vo) {
		vo.setSearchCondition("TITLE");
		vo.setSearchKeyword("");
		List<BoardVo> boardList = boardService.getBoardList(vo);
		return boardList;
	}
	*/
	
	@RequestMapping("/dataTransform.do")
	@ResponseBody
	public BoardListVo dataTransform(BoardVo vo) {
		vo.setSearchCondition("TITLE");
		vo.setSearchKeyword("");
		List<BoardVo> boardList = boardService.getBoardList(vo);
		BoardListVo boardListVo = new BoardListVo();
		boardListVo.setBoardList(boardList);
		return boardListVo;
	}

	// 글 등록
	@RequestMapping("/insertBoard.do")
	public String insertBoard(BoardVo vo, BoardDao boardDao) throws IOException {
		MultipartFile uploadFile = vo.getUploadFile();
		if(! vo.getUploadFile().isEmpty()) {
			String fileName = uploadFile.getOriginalFilename();
			uploadFile.transferTo(new File("D:/dev/" + fileName));
		}
		boardService.insertBoard(vo);
		return "redirect:getBoardList.do";
	}

	// 글 수정
	@RequestMapping("/updateBoard.do")
	public String updateBoard(@ModelAttribute("board") BoardVo vo, BoardDao boardDao) {
		System.out.println("번호: " + vo.getSeq());
		System.out.println("제목: " + vo.getTitle());
		System.out.println("작성자이름: " + vo.getWriter());
		boardService.updateBoard(vo);
		return "getBoardList.do";
	}

	// 글 삭제
	@RequestMapping("deleteBoard")
	public String deleteBoard(BoardDao boardDao, BoardVo vo) {
		boardService.deleteBoard(vo);
		return "getBoardList.do";
	}

	// 글 상세조회
	@RequestMapping
	public String getBoard(BoardVo vo, BoardDao boardDao, Model model) {
		model.addAttribute("board", boardService.getBoard(vo));
		return "/getBoard.jsp";
	}

	// 검색 조건 목록 설정
	// @ModelAttribute 가 적용된 함수는 하위 또는 다른 함수보다 먼저 실행되며, Model객체에 리턴 객체를 저장함.
	@ModelAttribute("conditionMap")
	public Map<String, String> searchConditionMap() {
		Map<String, String> conditionMap = new LinkedHashMap<String, String>();
		conditionMap.put("제목", "TITLE");
		conditionMap.put("내용", "CONTENT");
		return conditionMap;
	}

	// 글 목록 검색
	@RequestMapping("/getBoardList.do")
	public String getBoardList(BoardVo vo, BoardDao boardDao, Model model) {
		System.out.println("글 목록검색. " + vo);
		// Null Check
		if(vo.getSearchCondition() == null) vo.setSearchCondition("TITLE");
		if(vo.getSearchKeyword() == null) vo.setSearchKeyword("");
		model.addAttribute("boardList", boardService.getBoardList(vo)); // Model 정보 저장
		// ViewResolver 적용시에는 확장자를 제거해야 함
		return "/getBoardList.jsp";
	}
}
