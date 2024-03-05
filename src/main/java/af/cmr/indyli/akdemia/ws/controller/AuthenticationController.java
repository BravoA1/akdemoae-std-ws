package af.cmr.indyli.akdemia.ws.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import af.cmr.indyli.akdemia.business.dto.UserDto;
import af.cmr.indyli.akdemia.business.exception.AkdemiaBusinessException;
import af.cmr.indyli.akdemia.business.service.IUserService;
import af.cmr.indyli.akdemia.ws.form.ApiResponse;
import af.cmr.indyli.akdemia.ws.form.AuthToken;
import af.cmr.indyli.akdemia.ws.security.JwtTokenUtil;
import af.cmr.indyli.akdemia.ws.utils.Constants;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/token")
public class AuthenticationController {
	
	@Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private IUserService userService;
    
    @RequestMapping(value = "/generate-token", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse<AuthToken>> register(@RequestBody UserDto loginUser) throws AkdemiaBusinessException {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getLogin(), loginUser.getPassword()));
            final UserDto user = userService.findByLogin(loginUser.getLogin());
            final String token = jwtTokenUtil.generateToken(user);
            return ResponseEntity.ok(new ApiResponse<>(200, "success", new AuthToken(token, user.getLogin())));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponse<>(401, "Login ou mot de passe incorrect", null));
        }
    }
    
    @RequestMapping(value = "/is-token-valid", method = RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<Boolean> register(@RequestBody AuthToken tokenValue) throws AuthenticationException, AkdemiaBusinessException {
    	if (StringUtils.isNoneBlank(tokenValue.getToken())){
    		tokenValue.setToken(Constants.TOKEN_PREFIX + tokenValue.getToken());
    		Boolean isTokenValid  = jwtTokenUtil.isTokenExpired(tokenValue.getToken());
    		return new ApiResponse<Boolean>(200, "success",isTokenValid);
    	} else {
    		return new ApiResponse<Boolean>(200, "success",Boolean.FALSE);
    	}
    }
}
