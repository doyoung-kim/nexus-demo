package com.edmp.api.board.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/version")
@Tag(name = "version", description = "버전 API")
public class VersionController {

	@GetMapping(value="")
	@Operation(summary = "버전 조회", description = "버전을 조회합니다.")
	public ResponseEntity<Object> getBoard() {

		String version = "v2.0";
		return ResponseEntity.status(HttpStatus.OK).body(version);
	}

}
