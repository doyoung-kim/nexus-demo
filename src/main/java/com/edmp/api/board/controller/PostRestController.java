//package com.edmp.api.board.controller;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
//@RequestMapping("/post/*")
//@Tag(name = "post", description = "게시글 API")
//public class PostRestController extends StandardController {
//
//	public PostRestController() {
//		System.out.println(this.getClass());
//	}
//
//	@GetMapping("{postNo}")
//	@Operation(summary = "게시글 조회", description = "게시글을 조회합니다.")
//	public ResponseEntity<Object> getPost(@Parameter(description = "조회 할 게시글 ID") @PathVariable int postNo) throws Exception {
//		Map<String, Object> param = new HashMap<>();
//		param.put("postNo", postNo);
//		Map<String, Object> result = dbSvc.dbDetail("post.getPost", param);
//		if(result != null) {
//			dbSvc.dbUpdate("post.updateViewCount", param);
//		}
//		return ResponseEntity.status(result != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST).body(result);
//	}
//
//	@PostMapping("list")
//	@Operation(summary = "게시글 목록 조회", description = "게시글 목록을 조회합니다.")
//	public ResponseEntity<Object> getPostList(@Parameter(description = "검색 조건") HttpServletRequest request, HttpServletResponse response, RequestEntity<Map<String, Object>> requestEntity) throws Exception {
//		Map<String, Object> mapReq = setRequestMap(request, response, requestEntity);
//		Map<String, Object> result = new HashMap<>();
//		mapReq.put("startPage",(int)mapReq.get("currentPage")*(int)mapReq.get("pageSize"));
//		result.put("totalCount", dbSvc.dbInt("post.getPostTotalCount", mapReq));
//		result.put("list", dbSvc.dbList("post.getPostList", mapReq));
//		return ResponseEntity.status(HttpStatus.OK).body(result);
//	}
//
//	@PostMapping
//	@Operation(summary = "게시글 추가", description = "게시글을 추가합니다.")
//	public ResponseEntity<Object> addPost(@Parameter(description = "추가 할 게시글 정보") HttpServletRequest request, HttpServletResponse response, RequestEntity<Map<String, Object>> requestEntity) throws Exception {
//		Map<String, Object> mapReq = setRequestMap(request, response, requestEntity);
//		int affectedRow = dbSvc.dbInsert("post.addPost", mapReq);
//		affectedRow += dbSvc.dbInsert("post.addContent", mapReq);
//		Map<String, Object> result = new HashMap<>();
//		result.put("postNo", mapReq.get("postNo"));
//		return ResponseEntity.status(affectedRow == 2 ? HttpStatus.OK : HttpStatus.BAD_REQUEST).body(result);
//	}
//
//	@DeleteMapping("{postNo}")
//	@Operation(summary = "게시글 삭제", description = "게시글을 삭제합니다.")
//	public ResponseEntity<Object> delPost(@Parameter(description = "삭제 할 게시글 정보") @PathVariable int postNo) throws Exception {
//		Map<String, Object> param = new HashMap<>();
//		param.put("postNo", postNo);
//		param.put("updateUserId", 0);
//		int affectedRow = dbSvc.dbDelete("post.delPost", param);
//		return new ResponseEntity<Object>(affectedRow > 0 ? HttpStatus.OK : HttpStatus.NO_CONTENT);
//	}
//
//	@PatchMapping("list")
//	@Operation(summary = "게시글 리스트 삭제", description = "게시글을 삭제합니다.")
//	public ResponseEntity<Object> delPostList(@Parameter(description = "삭제할 게시글 리스트") @RequestBody List<Map<String, Object>> postList) throws Exception {
//		for(Map<String, Object> post : postList) {
//			post.put("updateUserId", 0);
//			dbSvc.dbDelete("post.delPost", post);
//		}
//
//		return new ResponseEntity<Object>(HttpStatus.OK);
//	}
//
//	@PatchMapping
//	@Operation(summary = "게시글 수정", description = "게시글을 수정합니다.")
//	public ResponseEntity<Object> setPost(@Parameter(description = "수정 할 게시글 정보") HttpServletRequest request, HttpServletResponse response, RequestEntity<Map<String, Object>> requestEntity) throws Exception {
//		Map<String, Object> mapReq = setRequestMap(request, response, requestEntity);
//		mapReq.put("updateUserId", 0);
//		int affectedRow = dbSvc.dbUpdate("post.setPost", mapReq);
//		affectedRow += dbSvc.dbUpdate("post.setPostContent", mapReq);
//		return new ResponseEntity<Object>(affectedRow == 2 ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
//	}
//
//	@PostMapping("comment/list")
//	@Operation(summary = "댓글 목록 조회", description = "댓글 목록을 조회합니다.")
//	public ResponseEntity<Object> getCommentList(@Parameter(description = "검색 조건") HttpServletRequest request, HttpServletResponse response, RequestEntity<Map<String, Object>> requestEntity) throws Exception {
//		Map<String, Object> mapReq = setRequestMap(request, response, requestEntity);
//		Map<String, Object> result = new HashMap<>();
//		mapReq.put("startPage",(int)mapReq.get("currentPage")*(int)mapReq.get("pageSize"));
//		result.put("totalCount", dbSvc.dbInt("comment.getTotalCount", mapReq));
//		result.put("list", dbSvc.dbList("comment.getCommentList", mapReq));
//
//		return ResponseEntity.status(HttpStatus.OK).body(result);
//	}
//
//	@DeleteMapping("comment/{commentId}")
//	@Operation(summary = "댓글 삭제", description = "댓글을 삭제합니다.")
//	public ResponseEntity<Object> delComment(@Parameter(description = "삭제 할 댓글 정보") @PathVariable int commentId) throws Exception {
//		Map<String, Object> param = new HashMap<>();
//		param.put("commentId", commentId);
//		Map<String, Object> comment = dbSvc.dbDetail("comment.getComment", param);
//		param.put("postNo", comment.get("postNo"));
//		param.put("count", -1);
//		int affectedRow = dbSvc.dbUpdate("post.updatePostCommentCount", param);
//		param.put("updateUserId", 0);
//		affectedRow += dbSvc.dbDelete("comment.delComment", param);
//		return new ResponseEntity<Object>(comment, affectedRow > 0 ? HttpStatus.OK : HttpStatus.NO_CONTENT);
//	}
//
//	@PostMapping("comment")
//	@Operation(summary = "댓글 추가", description = "댓글을 추가합니다.")
//	public ResponseEntity<Object> addComment(@Parameter(description = "추가 할 댓글 정보") HttpServletRequest request, HttpServletResponse response, RequestEntity<Map<String, Object>> requestEntity) throws Exception {
//		Map<String, Object> mapReq = setRequestMap(request, response, requestEntity);
//		Map<String, Object> param = new HashMap<>();
//		mapReq.put("createUserId", 0);
//		param.put("postNo", mapReq.get("postNo"));
//		param.put("count", 1);
//		int affectedRow = dbSvc.dbUpdate("post.updatePostCommentCount", param);
//		affectedRow += dbSvc.dbInsert("comment.addComment", mapReq);
//
//		return new ResponseEntity<Object>(mapReq, affectedRow > 0 ? HttpStatus.OK : HttpStatus.NO_CONTENT);
//	}
//
//	@PatchMapping("comment")
//	@Operation(summary = "댓글 수정", description = "댓글을 수정합니다.")
//	public ResponseEntity<Object> setComment(@Parameter(description = "수정 할 댓글 정보") HttpServletRequest request, HttpServletResponse response, RequestEntity<Map<String, Object>> requestEntity) throws Exception {
//		Map<String, Object> mapReq = setRequestMap(request, response, requestEntity);
//		mapReq.put("updateUserId", 0);
//		int affectedRow = dbSvc.dbUpdate("comment.setComment", mapReq);
//		return new ResponseEntity<Object>(mapReq, affectedRow > 0 ? HttpStatus.OK : HttpStatus.NO_CONTENT);
//	}
//
//	@GetMapping("notice/list/{boardNo}")
//	@Operation(summary = "공지사항 목록 조회", description = "공지사항 목록을 조회합니다.")
//	public ResponseEntity<Object> getNoticeList(@Parameter(description = "조회 할 공지사항이 소속된 게시판 ID") @PathVariable int boardNo) throws Exception {
//		Map<String, Object> param = new HashMap<>();
//		param.put("boardNo", boardNo);
//		return ResponseEntity.status(HttpStatus.OK).body(dbSvc.dbList("post.getNoticeList", param));
//	}
//
//	@PostMapping("downloadExcel")
//	@Operation(summary = "엑셀 다운로드", description = "엑셀로 다운로드합니다.")
//	public void downloadExcel(@Parameter(description = "검색 조건") HttpServletRequest request, HttpServletResponse response, RequestEntity<Map<String, Object>> requestEntity) throws Exception {
//		Map<String, Object> mapReq = setRequestMap(request, response, requestEntity);
//		List<Map<String, Object>> postList = dbSvc.dbList("post.getExcelList", mapReq);
//		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
//		response.setHeader("Content-Disposition", "attachment; filename=post_list.xlsx");
//
//		Workbook workbook = new XSSFWorkbook();
//		Sheet sheet = workbook.createSheet();
//		Row row;
//		Cell cell;
//		String[] headers = {
//			"번호",
//			"제목",
//			"조회수",
//			"비밀글 여부",
//			"댓글가능 여부",
//			"공지사항 여부",
//			"게시물삭제 여부",
//			"첨부파일 개수"
//		};
//
//		row = sheet.createRow(0);
//
//		for(int i=0; i<headers.length; i++) {
//			cell = row.createCell(i);
//			cell.setCellValue(headers[i]);
//		}
//
//		for(int i=0; i<postList.size(); i++) {
//			Map<String, Object> post = postList.get(i);
//			row = sheet.createRow(i+1);
//			cell = row.createCell(0);
//			cell.setCellValue((int) post.get("postNo"));
//			cell = row.createCell(1);
//			cell.setCellValue((String) post.get("title"));
//			cell = row.createCell(2);
//			cell.setCellValue((int) post.get("viewCnt"));
//			cell = row.createCell(3);
//			cell.setCellValue((String) post.get("secretYn"));
//			cell = row.createCell(4);
//			cell.setCellValue((String) post.get("hideCommentYn"));
//			cell = row.createCell(5);
//			cell.setCellValue((String) post.get("noticeYn"));
//			cell = row.createCell(6);
//			cell.setCellValue((String) post.get("delYn"));
//			cell = row.createCell(7);
//			cell.setCellValue((int) post.get("attachFileCnt"));
//		}
//
//		workbook.write(response.getOutputStream());
//		workbook.close();
//	}
//}
