package com.lyarc.tp.corp.attachfile.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
/**TODO 2017-9-12 建议将ResponseBody 提取到类级别*/

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.lyarc.tp.corp.UserHolder;
import com.lyarc.tp.corp.attachfile.bean.AttachFileBean;
import com.lyarc.tp.corp.attachfile.bean.QueryFileBean;
import com.lyarc.tp.corp.attachfile.dto.AttachFileDTO;
import com.lyarc.tp.corp.attachfile.service.AttachFileService;
import com.lyarc.tp.corp.common.ResultVo;
import com.lyarc.tp.corp.common.config.storage.StorageProperties;
import com.lyarc.tp.corp.common.config.storage.StorageService;
import com.lyarc.tp.corp.login.form.LoginUser;

/**
 * 附件
 *
 * @version 1.0
 * @since JDK1.8
 * @author huanghaoqi
 * @company 洛阳艾克科技有限公司
 * @copyright (c) 2017 LuoYang ARC Co'Ltd Inc. All rights reserved.
 * @date 2017年08月31日 14:45:02
 */
@Controller
@RequestMapping(value = "/api/file")
public class AttachFileController {

	@Autowired
	private AttachFileService attachFileService;
	@Autowired
	private StorageService storageService;
	@Autowired
	private StorageProperties storage;

	/**
	 * 方法描述 获取业务对象相关的附件
	 * 
	 * @param queryFileBean
	 *            查询参数
	 * @return ResultVo<List<AttachFileDTO>>
	 *
	 * @author huanghaoqi
	 * @date 2017年08月31日 16:17:05
	 */
	@RequestMapping(value = "/getListByObj", method = RequestMethod.POST)
	@ResponseBody
	public ResultVo<List<AttachFileDTO>> getListByObj(@RequestBody QueryFileBean queryFileBean) {
		String lastFileId = queryFileBean.getLastFileId();
		if (StringUtils.isEmpty(lastFileId)) {
			queryFileBean.setLastFileId(null);
		}
		Integer rows = queryFileBean.getRows();
		if (rows == null || rows < 10 || rows > 100) {
			queryFileBean.setRows(10);
		}
		List<AttachFileDTO> attachFileDtoList = attachFileService.list(queryFileBean);
		return ResultVo.success(attachFileDtoList);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public ResultVo<List<AttachFileDTO>> list(@RequestParam("fileIds") String fileIds) {
		List<AttachFileDTO> attachFileDtoList = null;
		if (StringUtils.isNotEmpty(fileIds)) {
			String[] fileIdArr = fileIds.split(",");

			attachFileDtoList = attachFileService.listByFileIds(fileIdArr);
		}

		return ResultVo.success(attachFileDtoList);
	}

	/**
	 * 方法描述 文件上传
	 * 
	 * @param file
	 *            文件对象
	 *
	 * @author huanghaoqi
	 * @date 2017年09月01日 13:01:38
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST, headers = { "content-type=multipart/form-data" })
	@ResponseBody
	public ResultVo<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
		LoginUser loginUser = UserHolder.getUser();

		// 文件的原始名
		String originalFilename = file.getOriginalFilename();
		// 文件名
		String uploadFileName = originalFilename.substring(originalFilename.lastIndexOf('\\') + 1,
				originalFilename.indexOf('.'));
		// 文件后缀
		String uploadFileSuffix = originalFilename.substring(originalFilename.lastIndexOf('.') + 1,
				originalFilename.length());
		// 文件大小
		long fileSize = file.getSize();

		String fileCatalog = file.getContentType();
		String fileId = (UUID.randomUUID() + "_" + uploadFileSuffix);
		fileId = fileId.replaceAll("-", "");
		String fileName = fileId + "." + uploadFileSuffix;
		// 文件路径
		String filePath = storage.getLocation() + "/" + fileName;

		AttachFileBean attachFileBean = new AttachFileBean();
		attachFileBean.setCreator(loginUser.getUserId().toString());
		attachFileBean.setDeleted(false);
		attachFileBean.setDeleter(null);
		attachFileBean.setDeleteTime(null);
		attachFileBean.setFileCatalog(fileCatalog);
		attachFileBean.setFileId(fileId);
		attachFileBean.setFileName(uploadFileName);
		attachFileBean.setFileMd5(null);
		attachFileBean.setFileNameExt(uploadFileSuffix);
		attachFileBean.setFilePath(filePath);
		attachFileBean.setFileSize((int) fileSize);
		attachFileBean.setModule(null);
		attachFileBean.setObjId(null);
		attachFileBean.setTitle(null);

		attachFileService.insert(attachFileBean);
		storageService.store(file, fileName);
		return ResultVo.success(fileId);
	}

	/**
	 * 方法描述 文件下载
	 * 
	 * @param fileId
	 *            文件ID
	 *
	 * @author huanghaoqi
	 * @date 2017年09月01日 13:02:22
	 */
	@RequestMapping(value = "/download", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Resource> download(HttpServletResponse response, @RequestParam("fileId") String fileId)
			throws IOException {
		AttachFileBean attachFileBean = attachFileService.getDetail(fileId);
		String fileName = fileId + "." + attachFileBean.getFileNameExt();
		Resource file = storageService.loadAsResource(fileName);
		String downLoadFileName = attachFileBean.getFileName() + "." + attachFileBean.getFileNameExt();
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
				"attachment; filename*=UTF-8''" + URLEncoder.encode(downLoadFileName, "UTF-8")).body(file);
	}

	/**
	 * 方法描述 文件预览
	 *
	 * @param fileId
	 *            文件ID
	 *
	 * @author huanghaoqi
	 * @date 2017年09月01日 13:02:22
	 */
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Resource> view(HttpServletResponse response, @RequestParam("fileId") String fileId) {
		AttachFileBean attachFileBean = attachFileService.getDetail(fileId);
		String fileName = fileId + "." + attachFileBean.getFileNameExt();
		Resource file = storageService.loadAsResource(fileName);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, attachFileBean.getFileCatalog()).body(file);
	}

	/**
	 * 方法描述 删除附件
	 * 
	 * @param fileIds
	 *            附件ID集合
	 * @return ResultVo
	 *
	 * @author huanghaoqi
	 * @date 2017年09月04日 11:05:29
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public ResultVo delete(@RequestParam("fileIds") String[] fileIds) {
		LoginUser loginUser = UserHolder.getUser();
		AttachFileBean attachFileBean = new AttachFileBean();
		attachFileBean.setDeleteTime(LocalDateTime.now());
		attachFileBean.setDeleted(true);
		attachFileBean.setDeleter(loginUser.getUserId().toString());
		attachFileService.update(attachFileBean, fileIds);
		return ResultVo.success();
	}
}
