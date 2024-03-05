//package com.edmp.api.board.controller;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.dao.DataAccessException;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.RequestEntity;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PatchMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.Parameter;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import kr.co.ezpmp.core.controller.StandardController;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//
//@RestController
//@RequiredArgsConstructor
//@Slf4j
//@RequestMapping("/board/*")
//@Tag(name = "board", description = "게시판 API")
//public class BoardRestController extends StandardController {
//	@GetMapping("{boardNo}")
//	@Operation(summary = "게시판 조회", description = "게시판을 조회합니다.")
//	public ResponseEntity<Object> getBoard(@Parameter(description = "조회할 게시판 ID") @PathVariable int boardNo, HttpServletRequest request, HttpServletResponse response, RequestEntity<Map<String, Object>> requestEntity) throws Exception {
//		Map<String, Object> mapReq = setRequestMap(request, response, requestEntity);
//		Map<String, Object> result = new HashMap<>();
//		Map<String, Object> param = new HashMap<>();
//		param.put("boardNo", boardNo);
//		try {
//			result = dbSvc.dbDetail("board.getBoard", param);
//		}  catch (NullPointerException | ArrayIndexOutOfBoundsException e1) {
//			super.errorLogWrite(request, mapReq, e1);
//		}  catch (DataAccessException e2) {
//			String sqlId 	= "";
//			String sqlMsg 	= e2.getMessage();
//
//			if(sqlMsg.indexOf("/*") > 0) {
//				sqlId = sqlMsg.substring(sqlMsg.indexOf("/*") + 2, sqlMsg.indexOf("*/") - 1);
//				mapReq.put("sql_id", sqlId);
//			}
//
//			super.errorLogWrite(request, mapReq, e2);
//			log.error(this.getClass().getName(), e2);
//
//			errorGoPage(request, response);
//
//		}  catch (Exception e) {
//			// TODO Auto-generated catch block
//			log.error(e.getMessage());
//		}
//		return ResponseEntity.status(result != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST).body(result);
//	}
//
//	@PostMapping("list")
//	@Operation(summary = "게시판 목록 조회", description = "게시판 목록을 조회합니다.")
//	public ResponseEntity<Object> getBoardList(@Parameter(description = "검색 조건") HttpServletRequest request, HttpServletResponse response, RequestEntity<Map<String, Object>> requestEntity) throws Exception {
//		Map<String, Object> mapReq = setRequestMap(request, response, requestEntity);
//		Map<String, Object> result = new HashMap<>();
//
//		try {
//			result.put("list", dbSvc.dbList("board.getBoardList", mapReq));
//			result.put("totalCount", dbSvc.dbInt("board.getBoardTotalCount", mapReq));
//		}  catch (NullPointerException | ArrayIndexOutOfBoundsException e1) {
//			super.errorLogWrite(request, mapReq, e1);
//		}  catch (DataAccessException e2) {
//			String sqlId 	= "";
//			String sqlMsg 	= e2.getMessage();
//
//			if(sqlMsg.indexOf("/*") > 0) {
//				sqlId = sqlMsg.substring(sqlMsg.indexOf("/*") + 2, sqlMsg.indexOf("*/") - 1);
//				mapReq.put("sql_id", sqlId);
//			}
//
//			super.errorLogWrite(request, mapReq, e2);
//			log.error(this.getClass().getName(), e2);
//
//			errorGoPage(request, response);
//
//		}  catch (Exception e) {
//			// TODO Auto-generated catch block
//			log.error(e.getMessage());
//		}
//
//		return ResponseEntity.status(HttpStatus.OK).body(result);
//	}
//
//	@PostMapping
//	@Operation(summary = "게시판 추가", description = "게시판을 추가합니다.")
//	public ResponseEntity<Object> addBoard(@Parameter(description = "추가할 게시판 정보") HttpServletRequest request, HttpServletResponse response, RequestEntity<Map<String, Object>> requestEntity) throws Exception {
//		Map<String, Object> mapReq = setRequestMap(request, response, requestEntity);
//		mapReq.put("projectCd","EDMP"); // 임시
//		int affectedRow = 0;
//		try {
//			affectedRow = dbSvc.dbInsert("board.addBoard", mapReq);
//		}  catch (NullPointerException | ArrayIndexOutOfBoundsException e1) {
//			super.errorLogWrite(request, mapReq, e1);
//		}  catch (DataAccessException e2) {
//			String sqlId 	= "";
//			String sqlMsg 	= e2.getMessage();
//
//			if(sqlMsg.indexOf("/*") > 0) {
//				sqlId = sqlMsg.substring(sqlMsg.indexOf("/*") + 2, sqlMsg.indexOf("*/") - 1);
//				mapReq.put("sql_id", sqlId);
//			}
//
//			super.errorLogWrite(request, mapReq, e2);
//			log.error(this.getClass().getName(), e2);
//
//			errorGoPage(request, response);
//
//		}  catch (Exception e) {
//			// TODO Auto-generated catch block
//			log.error(e.getMessage());
//		}
//		return new ResponseEntity<Object>(affectedRow > 0 ? HttpStatus.OK : HttpStatus.NO_CONTENT);
//	}
//
//	@PatchMapping
//	@Operation(summary = "게시판 수정", description = "게시판을 수정합니다.")
//	public ResponseEntity<Object> setBoard(@Parameter(description = "수정할 게시판 정보") HttpServletRequest request, HttpServletResponse response, RequestEntity<Map<String, Object>> requestEntity) throws Exception {
//		Map<String, Object> mapReq = setRequestMap(request, response, requestEntity);
//		int affectedRow = 0;
//		try {
//			affectedRow = dbSvc.dbUpdate("board.setBoard", mapReq);
//		}  catch (NullPointerException | ArrayIndexOutOfBoundsException e1) {
//			super.errorLogWrite(request, mapReq, e1);
//		}  catch (DataAccessException e2) {
//			String sqlId 	= "";
//			String sqlMsg 	= e2.getMessage();
//
//			if(sqlMsg.indexOf("/*") > 0) {
//				sqlId = sqlMsg.substring(sqlMsg.indexOf("/*") + 2, sqlMsg.indexOf("*/") - 1);
//				mapReq.put("sql_id", sqlId);
//			}
//
//			super.errorLogWrite(request, mapReq, e2);
//			log.error(this.getClass().getName(), e2);
//
//			errorGoPage(request, response);
//
//		}  catch (Exception e) {
//			// TODO Auto-generated catch block
//			log.error(e.getMessage());
//		}
//		return new ResponseEntity<Object>(affectedRow > 0 ? HttpStatus.OK : HttpStatus.NO_CONTENT);
//	}
//
//	@DeleteMapping("{boardNo}")
//	@Operation(summary = "게시판 삭제", description = "게시판을 삭제합니다.")
//	public ResponseEntity<Object> removeBoard(@Parameter(description = "삭제할 게시판 정보") @PathVariable int boardNo, HttpServletRequest request, HttpServletResponse response, RequestEntity<Map<String, Object>> requestEntity) throws Exception {
//		Map<String, Object> mapReq = setRequestMap(request, response, requestEntity);
//		Map<String, Object> param = new HashMap<>();
//		param.put("boardNo", boardNo);
//		param.put("updateUserId", 0);
//		int affectedRow = 0;
//		try {
//			affectedRow = dbSvc.dbDelete("board.delBoard", param);
//		}  catch (NullPointerException | ArrayIndexOutOfBoundsException e1) {
//			super.errorLogWrite(request, mapReq, e1);
//		}  catch (DataAccessException e2) {
//			String sqlId 	= "";
//			String sqlMsg 	= e2.getMessage();
//
//			if(sqlMsg.indexOf("/*") > 0) {
//				sqlId = sqlMsg.substring(sqlMsg.indexOf("/*") + 2, sqlMsg.indexOf("*/") - 1);
//				mapReq.put("sql_id", sqlId);
//			}
//
//			super.errorLogWrite(request, mapReq, e2);
//			log.error(this.getClass().getName(), e2);
//
//			errorGoPage(request, response);
//
//		}  catch (Exception e) {
//			// TODO Auto-generated catch block
//			log.error(e.getMessage());
//		}
//		return new ResponseEntity<Object>(affectedRow > 0 ? HttpStatus.OK : HttpStatus.NO_CONTENT);
//	}
//}
