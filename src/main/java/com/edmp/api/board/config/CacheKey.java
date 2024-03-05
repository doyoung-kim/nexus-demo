package com.edmp.api.board.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.TimeUnit;

/******************************************************************
 * <pre>
 * com.edmp.api.board.config
 * ㄴ CacheKey.java
 *  </pre>
 * @author   : Lim Jae Sung
 * @version  : 1.0
 * @since 2021/04/05
 * @see <b>Copyright (C) by OSC Company All right reserved.</b>
 *******************************************************************/
@Getter
@AllArgsConstructor
public enum CacheKey {

	BOARD_SAMPLE     ( "ezpmp:board:sample:%s:%s:%s",  30, TimeUnit.DAYS    );

	private String   key;
	private long     timeOut;
	private TimeUnit timeUnit;

}
