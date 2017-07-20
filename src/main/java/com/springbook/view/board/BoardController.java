package com.springbook.view.board;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
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

@SessionAttributes("board")
@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/dataTransform.do")
	@ResponseBody
	public List<BoardVo> dataTransform(BoardVo vo) {
		vo.setSearchCondition("TITLE");
		vo.setSearchKeyword("");
		List<BoardVo> boardList = boardService.getBoardList(vo);
		return boardList;
	}
	
	@RequestMapping("/dataTransformXml.do")
	@ResponseBody
	public BoardListVo dataTransformXml(BoardVo vo) {
		vo.setSearchCondition("TITLE");
		vo.setSearchKeyword("");
		List<BoardVo> boardList = boardService.getBoardList(vo);
		BoardListVo boardListVo = new BoardListVo();
		boardListVo.setBoardList(boardList);
		return boardListVo;
	}
	
	@RequestMapping("/updateBoard.do")
	public String updateBoard(@ModelAttribute("board") BoardVo vo, BoardDao boardDao) {
		boardService.updateBoard(vo);
		return "getBoardList.do";
	}
	
	@RequestMapping("/deleteBoard.do")
	public String deleteBoard(BoardVo vo, BoardDao boardDao) {
		boardService.deleteBoard(vo);
		return "getBoardList.do";
	}

	@ModelAttribute("conditionMap")
	public Map<String, String> searchConditionMap() {
		Map<String, String> conditionMap = new HashMap<String, String>();
		conditionMap.put("내용", "CONTENT");
		conditionMap.put("제목", "TITLE");
		return conditionMap;
	}
	
	@RequestMapping(value="/insertBoard.do")
	public String insertBoard(BoardVo vo, BoardDao boardDao) throws IOException {
		MultipartFile uploadFile = vo.getUploadFile();
		if(!uploadFile.isEmpty()) {
			String fileName = uploadFile.getOriginalFilename();
			uploadFile.transferTo(new File("D:/" + fileName));
		}
		boardService.insertBoard(vo);
		return "redirect:getBoardList.do";
	}


}
