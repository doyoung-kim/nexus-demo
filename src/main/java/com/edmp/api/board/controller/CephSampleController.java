//package com.edmp.api.board.controller;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.nio.file.StandardCopyOption;
//import java.util.List;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.amazonaws.services.s3.AmazonS3;
//import com.amazonaws.services.s3.model.Bucket;
//import com.amazonaws.services.s3.model.ObjectListing;
//import com.amazonaws.services.s3.model.PutObjectResult;
//import com.amazonaws.services.s3.model.S3Object;
//import com.amazonaws.services.s3.model.S3ObjectInputStream;
//
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.Parameter;
//import lombok.AllArgsConstructor;
//
///**
// * Created by rz
// */
//@RestController
//@RequestMapping("/ceph/sample")
//@AllArgsConstructor
//public class CephSampleController {
//
//    private final AmazonS3 s3client;
//
//	// 버킷관리
//	@GetMapping("/bucket")
//	@Operation(summary = "버킷 목록 조회", description = "버킷 목록을 조회합니다.")
//	public ResponseEntity<Object> listBuckets() throws Exception {
//		List<Bucket> buckets = s3client.listBuckets();
//		for(Bucket bucket : buckets) {
//		    System.out.println(bucket.getName());
//		}
//		return ResponseEntity.status(HttpStatus.OK).body(buckets);
//	}
//
//	@PostMapping("/bucket/{bucket}")
//	@Operation(summary = "버킷 생성", description = "버킷을 생성합니다.")
//	public ResponseEntity<Object> createBucket(@Parameter(description = "버킷명") @PathVariable String bucket) throws Exception {
//		Bucket bkt  = s3client.createBucket(bucket);
//		return ResponseEntity.status(HttpStatus.OK).body(bkt);
//	}
//
//	@DeleteMapping("/bucket/{bucket}")
//	@Operation(summary = "버킷 삭제", description = "버킷을 삭제합니다.")
//	public ResponseEntity<Object> getBucket(@Parameter(description = "버킷명") @PathVariable String bucket) throws Exception {
//		s3client.deleteBucket(bucket);
//		return ResponseEntity.status(HttpStatus.OK).build();
//	}
//
//	// 객체관리
//	@GetMapping("/object/{bucket}")
//	@Operation(summary = "객체 목록 조회", description = "버킷의 객체 목록을 조회합니다.")
//	public ResponseEntity<Object> listObjects(@Parameter(description = "버킷명") @PathVariable String bucket) throws Exception {
//		ObjectListing objList = s3client.listObjects(bucket);
//		return ResponseEntity.status(HttpStatus.OK).body(objList);
//	}
//
//	@PostMapping("/dummy-file/{file}")
//	@Operation(summary = "더미 파일 생성(테스트용)", description = "더미 파일을 생성합니다.(테스트용-파일의 업로드/다운로드는 독립적으로 처리하고, 버킷의 객체<->파일 간에 변환하도록 함)")
//	public ResponseEntity<Object> createDummyFile(@Parameter(description = "파일명") @PathVariable String file) throws Exception {
//		FileOutputStream fos = new FileOutputStream(new File(file));
//		fos.write(100);
//		fos.close();
//		return ResponseEntity.status(HttpStatus.OK).build();
//	}
//
//	@PostMapping("/object/{bucket}/{key}/{file}")
//	@Operation(summary = "객체 쓰기", description = "파일을 버킷의 객체로 씁니다.(파일->버킷의 객체로 저장)")
//	public ResponseEntity<Object> writeObject(@Parameter(description = "버킷명") @PathVariable String bucket, @Parameter(description = "객체키") @PathVariable String key, @Parameter(description = "파일명") @PathVariable String file) throws Exception {
//		PutObjectResult  res = s3client.putObject(
//				  bucket,
//				  key,
//				  new File(file)
//				);
//		return ResponseEntity.status(HttpStatus.OK).body(res);
//	}
//
//	@GetMapping("/object/{bucket}/{key}/{file}")
//	@Operation(summary = "객체 읽기", description = "버킷의 객체를 파일로 읽습니다.(버킷의 객체->파일로 저장)")
//	public ResponseEntity<Object> readObject(@Parameter(description = "버킷명") @PathVariable String bucket, @Parameter(description = "객체키") @PathVariable String key, @Parameter(description = "파일명") @PathVariable String file) throws Exception {
//		S3Object s3object = s3client.getObject(bucket, key);
//		S3ObjectInputStream inputStream = s3object.getObjectContent();
//		//FileUtils.copyInputStreamToFile(inputStream, new File(file));
//		//return ResponseEntity.status(HttpStatus.OK).body(buckets);
//
//		//InputStream in = new URL(FILE_URL).openStream();
//		Files.copy(inputStream, Paths.get(file), StandardCopyOption.REPLACE_EXISTING);
//
//		return ResponseEntity.status(HttpStatus.OK).body(new File(file));
//	}
//
//	@DeleteMapping("/object/{bucket}/{key}")
//	@Operation(summary = "객체 삭제", description = "버킷의 객체를 삭제합니다.")
//	public ResponseEntity<Object> deleteObject(@Parameter(description = "버킷명") @PathVariable String bucket, @Parameter(description = "객체키") @PathVariable String key) throws Exception {
//		s3client.deleteObject(bucket, key);
//		return ResponseEntity.status(HttpStatus.OK).build();
//	}
//}
