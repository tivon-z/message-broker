package pub.broker.msgbroker.auth.support.core;

import pub.broker.msgbroker.common.core.constant.SecurityConstants;
import pub.broker.msgbroker.common.security.service.MsgbrokerUser;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenClaimsContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenClaimsSet;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;

/**
 * token 输出增强
 *
 * @author broker.pub
 * @date 2022/6/3
 */
public class CustomeOAuth2TokenCustomizer implements OAuth2TokenCustomizer<OAuth2TokenClaimsContext> {

	/**
	 * Customize the OAuth 2.0 Token attributes.
	 * @param context the context containing the OAuth 2.0 Token attributes
	 */
	@Override
	public void customize(OAuth2TokenClaimsContext context) {
		OAuth2TokenClaimsSet.Builder claims = context.getClaims();
		claims.claim(SecurityConstants.DETAILS_LICENSE, SecurityConstants.PROJECT_LICENSE);
		String clientId = context.getAuthorizationGrant().getName();
		claims.claim(SecurityConstants.CLIENT_ID, clientId);
		// 客户端模式不返回具体用户信息
		if (SecurityConstants.CLIENT_CREDENTIALS.equals(context.getAuthorizationGrantType().getValue())) {
			return;
		}

		MsgbrokerUser msgbrokerUser = (MsgbrokerUser) context.getPrincipal().getPrincipal();
		claims.claim(SecurityConstants.DETAILS_USER, msgbrokerUser);
		claims.claim(SecurityConstants.DETAILS_USER_ID, msgbrokerUser.getId());
		claims.claim(SecurityConstants.USERNAME, msgbrokerUser.getUsername());
	}

}
