package af.cmr.indyli.akdemia.ws.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import af.cmr.indyli.akdemia.business.dto.ThemeDto;
import af.cmr.indyli.akdemia.business.exception.AkdemiaBusinessException;
import af.cmr.indyli.akdemia.business.service.IThemeService;
import af.cmr.indyli.akdemia.business.utils.AkdemiaConstantes.AkdemiaConstantesService;
import af.cmr.indyli.akdemia.ws.utils.AkdemiaConstantesWeb.AkdemiaConstantesURI;
import jakarta.annotation.Resource;

@RestController
@RequestMapping(AkdemiaConstantesURI.PATH_THEME)
public class ThemeRestController {
	
	@Resource(name = AkdemiaConstantesService.THEME_SERVICE_KEY)
	private IThemeService themeService;
	
	@RequestMapping(method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ThemeDto>> listAllTheme() {
		List<ThemeDto> themesList = themeService.findAll();
		return ResponseEntity.ok(themesList);
	}


	@RequestMapping(value=AkdemiaConstantesURI.PATH_THEME_ID,method = RequestMethod.GET)
	public ResponseEntity<ThemeDto> getThemeById(@PathVariable Integer themeId)  {
		ThemeDto foundExposedAlerte = null;
		try {
			foundExposedAlerte = themeService.findById(themeId);
		} catch (AkdemiaBusinessException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.ok(foundExposedAlerte);
	}

	@RequestMapping(value=AkdemiaConstantesURI.PATH_THEME_ID,method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteThemeById(@PathVariable Integer themeId)  {
		try {
			themeService.deleteById(themeId);
		} catch (AkdemiaBusinessException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(method = RequestMethod.POST,
			consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createTheme(@RequestBody ThemeDto theme) {
		try {
			return ResponseEntity.ok(this.themeService.create(theme));
		} catch (AkdemiaBusinessException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@PutMapping(value = "/{themeId}",
			consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> updateTheme(@RequestBody ThemeDto theme, @PathVariable("themeId") Integer themeId) {
		try {
			this.themeService.update(theme);
		} catch (AkdemiaBusinessException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
		return ResponseEntity.noContent().build();
	}

}
