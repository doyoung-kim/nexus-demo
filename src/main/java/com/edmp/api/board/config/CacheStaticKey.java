package com.edmp.api.board.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

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
public class CacheStaticKey {

	public static final String CONTROLLER_SAMPLE = "ezpmp:controller:sample";
	public static final int EXPIRED = 30;


}
