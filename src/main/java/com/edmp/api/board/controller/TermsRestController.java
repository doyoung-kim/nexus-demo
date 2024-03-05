//package com.edmp.api.board.controller;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
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
//import com.fasterxml.jackson.core.JsonProcessingException;
//
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.Parameter;
//import io.swagger.v3.oas.annotations.tags.Tag;
//
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@RestController
//@RequestMapping("/terms/*")
//@Tag(name = "약관", description = "약관 API")
//public class TermsRestController extends StandardController {
//	public TermsRestController() {
//		System.out.println(this.getClass());
//	}
//
//	@GetMapping("test")
//	public ResponseEntity<Object> get() throws JsonProcessingException {
//		return new ResponseEntity<Object>(HttpStatus.OK);
//	}
//
//	@PostMapping("get")
//	@Operation(summary = "약관 조회", description = "약관을 조회합니다.")
//	public ResponseEntity<Object> getTerms(@Parameter(description = "projectCd, siteId, termsKey, langCd") HttpServletRequest request, HttpServletResponse response, RequestEntity<Map<String, Object>> requestEntity) throws JsonProcessingException {
//		Map<String, Object> mapReq = setRequestMap(request, response, requestEntity);
//		Map<String, Object> result = new HashMap<>();
//
//		try {
//			result = dbSvc.dbDetail("terms.getTerms", mapReq);
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
//		return ResponseEntity.status(!result.isEmpty() ? HttpStatus.OK : HttpStatus.BAD_REQUEST).body(result);
//	}
//
//	@PostMapping("list")
//	@Operation(summary = "약관 목록 조회", description = "약관 목록을 조회합니다.")
//	public ResponseEntity<Object> getTermsList(@Parameter(description = "projectCd, siteId") HttpServletRequest request, HttpServletResponse response, RequestEntity<Map<String, Object>> requestEntity) throws JsonProcessingException {
//		Map<String, Object> mapReq	= super.setRequestMap(request, response, requestEntity);
//		List<Map<String,Object>> result = new ArrayList<>();
//
//		try {
//			result = dbSvc.dbList("terms.getTermsList", mapReq);
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
//		return ResponseEntity.status(result.size() > 0 ? HttpStatus.OK : HttpStatus.BAD_REQUEST).body(result);
//	}
//	@PatchMapping
//	@Operation(summary = "약관 수정", description = "약관을 수정합니다.")
//	public ResponseEntity<Object> setTerms(@Parameter(description = "수정 할 약관 정보") HttpServletRequest request, HttpServletResponse response, RequestEntity<Map<String, Object>> requestEntity) throws Exception {
//		Map<String, Object> mapReq = setRequestMap(request, response, requestEntity);
//		int affectedRow = 0;
//
//		try {
//			affectedRow = dbSvc.dbUpdate("terms.setTerms", mapReq);
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
//		return ResponseEntity.status(affectedRow > 0 ? HttpStatus.OK : HttpStatus.BAD_REQUEST).body(mapReq);
//	}
//	@PostMapping
//	@Operation(summary = "약관 추가", description = "약관을 추가합니다.")
//	public ResponseEntity<Object> addTerms(@Parameter(description = "추가 할 약관 정보") HttpServletRequest request, HttpServletResponse response, RequestEntity<Map<String, Object>> requestEntity) throws Exception {
//		Map<String, Object> mapReq = setRequestMap(request, response, requestEntity);
//		int affectedRow = 0;
//		try {
//			affectedRow = dbSvc.dbInsert("terms.addTerms", mapReq);
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
//		return ResponseEntity.status(affectedRow > 0 ? HttpStatus.OK : HttpStatus.BAD_REQUEST).body(mapReq);
//	}
//	@DeleteMapping("{termsNo}")
//	@Operation(summary = "약관 삭제", description = "약관을 삭제합니다.")
//	public ResponseEntity<Object> delTerms(@Parameter(description = "삭제 할 약관 ID") @PathVariable int termsNo, HttpServletRequest request, HttpServletResponse response, RequestEntity<Map<String, Object>> requestEntity) throws Exception {
//		Map<String, Object> mapReq = setRequestMap(request, response, requestEntity);
//		Map<String, Object> param = new HashMap<>();
//		param.put("termsNo", termsNo);
//		int affectedRow = 0;
//
//		try {
//			affectedRow = dbSvc.dbDelete("terms.delTerms", param);
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
//		return new ResponseEntity<Object>(affectedRow > 0 ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
//	}
//}
