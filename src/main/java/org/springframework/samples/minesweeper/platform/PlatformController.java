package org.springframework.samples.minesweeper.platform;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PlatformController {

  	private static final String VIEWS_PLATFORM_LIST = "platforms/listPlatform";
	private static final String VIEWS_PLATFORM_UPDATE_FORM = "platforms/updatePlatformForm";

	private final PlatformService platformService;

	@Autowired
	public PlatformController(PlatformService clinicService) {
		this.platformService = clinicService;
	}
    
    @GetMapping(value = { "/platforms" })
	public String showPlatformList(Map<String, Object> model) {
		
		List<Platform> platforms = new ArrayList<>(this.platformService.findPlatforms());

		model.put("platforms", platforms);
		return VIEWS_PLATFORM_LIST;
	}

    @GetMapping(value = "/platforms/update")
	public String initUpdateForm(Map<String, Object> model, @RequestParam() String platformId) {
        Platform platform = this.platformService.findPlatform(platformId);
		model.put("platform", platform);
		return VIEWS_PLATFORM_UPDATE_FORM;
	}
	@PostMapping(value = "/platforms/update")
	public String processUpdateForm(@Valid Platform platform, BindingResult result, @RequestParam String platformId) {
		if (result.hasErrors()) {
			return VIEWS_PLATFORM_UPDATE_FORM;
		}
		else {
            Integer id = Integer.valueOf(platformId);
            platform.setId(id);
			this.platformService.savePlatform(platform);
			return "redirect:/platforms";
		}
	}
}
