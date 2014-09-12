package co.edu.eafit.coglovi.restservice.security;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import co.edu.eafit.coglovi.transversal.Sha;


/**
 * Interceptor que se encarga de validar el usuario y clave de servicio rest y sus roles<br>
 * Creado el 21/07/2014 a las  13:53:43 <br>
 * @author <a href="http://www.quipux.com/">Quipux Software.</a></br>
 */
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

	private AuthenticationManager authenticationManager;
	private static final Logger logger = LoggerFactory.getLogger(AuthenticationInterceptor.class);

//	@Autowired
//	private SeguridadManager seguridadManager;

	/*
	 * Decodifica la informacion de la autorizacion
	 */
	public String[] decodeHeader(String authorization) {

		try {
			byte[] decoded = Base64.decode(authorization.getBytes("UTF-8"));
			String credentials = new String(decoded);
			return credentials.split(":");
		} catch (UnsupportedEncodingException e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public AuthenticationManager getAuthenticationManager() {
		return authenticationManager;
	}
	

	/*
	 * Obtiene los recursos asignados
	 */
	private List<GrantedAuthority> getGrantedAuthorities(List<String> recursos) {
		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
		for (String role : recursos) {
			authList.add(new SimpleGrantedAuthority(role));
		}
		return authList;
	}

	/**
	 * Se ejecuta antes de llegar al servicio consumido
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		try {
			String authorization = request.getHeader("Authorization");
			if (authorization != null) {
				String[] credentials = decodeHeader(authorization);
				credentials[1] = Sha.hash256(credentials[1]);
				//UsuarioSistema usuarioSistema = null;//seguridadManager.findCoGloVi(credentials[0], credentials[1]);
//				if (usuarioSistema != null) {
//					List<GrantedAuthority> userRoles = getGrantedAuthorities(usuarioSistema.getRecursos());
//					Authentication authentication = new UsernamePasswordAuthenticationToken(credentials[0], credentials[1], userRoles);
//					SecurityContextHolder.getContext().setAuthentication(authentication);
//				}else{
//					SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("",""));
//				}
			}else{
				SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("",""));
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("",""));
		}
		return true;
	}

	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

}
