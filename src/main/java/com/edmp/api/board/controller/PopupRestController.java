//package com.edmp.api.board.controller;
//
//import java.sql.Timestamp;
//import java.text.SimpleDateFormat;
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.RequestEntity;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PatchMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.Parameter;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import kr.co.ezpmp.core.controller.StandardController;
//
//@RestController
//@RequestMapping("/popup/*")
//@Tag(name = "popup", description = "팝업 API")
//public class PopupRestController extends StandardController {
//
//	@GetMapping("{popupNo}")
//	@Operation(summary = "팝업 조회", description = "팝업를 조회합니다.")
//	public ResponseEntity<Object> getPopup(@Parameter(description = "조회 할 팝업 ID") @PathVariable int popupNo) throws Exception {
//		Map<String, Object> result = new HashMap<>();
//		Map<String, Object> param = new HashMap<>();
//		param.put("popupNo", popupNo);
//
//		result = dbSvc.dbDetail("popup.getPopup", param);
//
//		if(result != null) {
//			if(result.get("startDt") != null) {
//				String startDt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Timestamp) result.get("startDt"));
//				result.replace("startDt", startDt);
//			}
//
//			if(result.get("endDt") != null) {
//				String endDt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Timestamp) result.get("endDt"));
//				result.replace("endDt", endDt);
//			}
//
//			if(result.get("updateDt") != null) {
//				String updateDt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Timestamp) result.get("updateDt"));
//				result.replace("updateDt", updateDt);
//			}
//		}
//
//		return ResponseEntity.status(result != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST).body(result);
//	}
//
//	@PostMapping("list")
//	@Operation(summary = "팝업 목록 조회", description = "팝업 목록을 조회합니다.")
//	public ResponseEntity<Object> getPopupList(@Parameter(description = "검색 조건") HttpServletRequest request, HttpServletResponse response, RequestEntity<Map<String, Object>> requestEntity) throws Exception {
//		Map<String, Object> mapReq = setRequestMap(request, response, requestEntity);
//		Map<String, Object> result = new HashMap<>();
//
//		mapReq.put("startPage", (int) mapReq.get("currentPage")*(int)mapReq.get("pageSize"));
//
//		result.put("totalCount", dbSvc.dbInt("popup.getTotalCount", mapReq));
//		result.put("list", dbSvc.dbList("popup.getPopupList", mapReq));
//
//		return ResponseEntity.status(HttpStatus.OK).body(result);
//	}
//
//	@PostMapping
//	@Operation(summary = "팝업 추가", description = "팝업을 추가합니다.")
//	public ResponseEntity<Object> addPopup(@Parameter(description = "추가할 팝업 정보") HttpServletRequest request, HttpServletResponse response, RequestEntity<Map<String, Object>> requestEntity) throws Exception {
//		Map<String, Object> mapReq = setRequestMap(request, response, requestEntity);
//		mapReq.put("projectCd", "EDMP"); // 임시
//		int affectedRow = dbSvc.dbInsert("popup.addPopup", mapReq);
//
//		return new ResponseEntity<Object>(affectedRow > 0 ? HttpStatus.OK : HttpStatus.NO_CONTENT);
//	}
//
//	@PatchMapping
//	@Operation(summary = "팝업 수정", description = "팝업을 수정합니다.")
//	public ResponseEntity<Object> setPopup(@Parameter(description = "수정할 팝업 정보") HttpServletRequest request, HttpServletResponse response, RequestEntity<Map<String, Object>> requestEntity) throws Exception {
//		Map<String, Object> mapReq = setRequestMap(request, response, requestEntity);
//		int affectedRow = dbSvc.dbUpdate("popup.setPopup", mapReq);
//
//		return new ResponseEntity<Object>(affectedRow > 0 ? HttpStatus.OK : HttpStatus.NO_CONTENT);
//	}
//
//	@DeleteMapping("{popupNo}")
//	@Operation(summary = "팝업 삭제", description = "팝업을 삭제합니다.")
//	public ResponseEntity<Object> delPopup(@Parameter(description = "삭제할 팝업 정보") @PathVariable int popupNo) throws Exception {
//		Map<String, Object> param = new HashMap<>();
//		param.put("popupNo", popupNo);
//		param.put("projectCd", "EDMP");
//		int affectedRow = dbSvc.dbDelete("popup.delPopup", param);
//
//		return new ResponseEntity<Object>(affectedRow > 0 ? HttpStatus.OK : HttpStatus.NO_CONTENT);
//	}
//
//	@GetMapping("list")
//	@Operation(summary = "현재 활성화된 팝업 목록 조회", description = "현재 활성화된 팝업 목록을 조회합니다.")
//	public ResponseEntity<Object> getPopupListInDateTime() throws Exception {
//		return ResponseEntity.status(HttpStatus.OK).body(dbSvc.dbList("popup.getPopupListInDateTime"));
//	}
//}
