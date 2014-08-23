package co.edu.eafit.coglovi.gestionapp.model.security;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class QuipuxUsuarioCAS extends UsernamePasswordAuthenticationToken implements UserDetails {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2332802455200689975L;
	private UsuarioSistema usuarioSistema;
   
	public QuipuxUsuarioCAS(Object principal, Object credentials) {
		super(principal, credentials);
		// TODO Auto-generated constructor stub
	}
	
	public QuipuxUsuarioCAS(Object principal, Object credentials,
			Collection<? extends GrantedAuthority> authorities) {
		super(principal, credentials, authorities);
		
	}

	

	public UsuarioSistema getUsuarioSistema() {
		return usuarioSistema;
	}

	public void setUsuarioSistema(UsuarioSistema usuarioSistema) {
		this.usuarioSistema = usuarioSistema;
	}

	@Override
	public String getPassword() {
		return usuarioSistema.getClaveAcceso();
	}

	@Override
	public String getUsername() {
		return usuarioSistema.getUsuarioAcceso();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}


	
}
