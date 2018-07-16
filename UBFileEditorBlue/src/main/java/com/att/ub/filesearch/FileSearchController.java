package com.att.ub.filesearch;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.att.ub.exceptions.FileSearchNotFoundException;

@Controller
public class FileSearchController {

	private static final String FILE_NAME = "filename";

	@Value("${ub.path.filehandler}")
	private String filehandler;

	@Value("${ub.path.copybookhandler}")
	private String copybookhandler;

	@Value("${ub.path.copybooktarget}")
	private String copybooktarget;

	private static final String PATH_FILE_NAME = "pathfile";

	@Autowired
	private FileSearchBO fileSearchBO;

	@RequestMapping(value = { "/search", "/search/" })
	@ResponseBody
	public String list(@RequestParam String path) {

		String pathDir = path.isEmpty() ? filehandler : path;

		try {
			return fileSearchBO.listFilePath(pathDir);
		} catch (FileSearchNotFoundException e) {
			return (new JSONObject()).toString();
		}

	}

	@RequestMapping(value = { "/setFileName", "/setFileName/" }, method = RequestMethod.POST)
	@ResponseBody
	public boolean setSessionPath(HttpServletRequest request, @RequestParam String file) {

		request.getSession().setAttribute(FILE_NAME, file);

		return true;

	}

	@RequestMapping("/selection")
	public String selection(Model model) {
		model.addAttribute(PATH_FILE_NAME, filehandler);
		return "selection";
	}

	@RequestMapping("/filters")
	@ResponseBody
	public String filters(@RequestParam String copybook) {
		fileSearchBO.getCopybookBO(copybook);

		return "filters";
	}

	@RequestMapping("/table")
	public String table(Model model) {
		model.addAttribute(PATH_FILE_NAME, filehandler);
		return "table";
	}

	@RequestMapping("/loadDir")
	@ResponseBody
	public String loadDirectories(@RequestParam(value = "isFilePath", required = false, defaultValue = "true") boolean isFilePath, @RequestParam(value = "isTargetCopybook", required = false, defaultValue = "true") boolean isTargetCopybook) {

		return fileSearchBO.listDirectories(isFilePath, isTargetCopybook);
	}

	@RequestMapping("/getPath")
	@ResponseBody
	public String getPath(@RequestParam(value = "isFilePath", required = false, defaultValue = "true") boolean isFilePath, @RequestParam(value = "isTargetCopybook", required = false, defaultValue = "true") boolean isTargetCopybook) {

		if (isFilePath) {
			return filehandler;
		}

		if (isTargetCopybook) {
			return copybooktarget;
		}

		return copybookhandler;
	}

}
