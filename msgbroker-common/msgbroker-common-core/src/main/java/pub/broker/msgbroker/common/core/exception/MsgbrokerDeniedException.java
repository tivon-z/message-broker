/*
 * Copyright (c) 2020 PIG4CLOUD Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package pub.broker.msgbroker.common.core.exception;

import lombok.NoArgsConstructor;

/**
 * @author broker.pub
 * @date 2018年06月22日16:22:03 403 授权拒绝
 */
@NoArgsConstructor
public class MsgbrokerDeniedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MsgbrokerDeniedException(String message) {
		super(message);
	}

	public MsgbrokerDeniedException(Throwable cause) {
		super(cause);
	}

	public MsgbrokerDeniedException(String message, Throwable cause) {
		super(message, cause);
	}

	public MsgbrokerDeniedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
