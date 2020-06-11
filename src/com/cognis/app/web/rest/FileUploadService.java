package com.cognis.app.web.rest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.cognis.app.model.SaloonErrorModel;
import com.cognis.app.web.utils.ErrorCode;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

@Path("/files")
public class FileUploadService extends AppBaseService {
	private static final Logger logger = Logger.getLogger(FileUploadService.class);
	private static final String FOLDER_PATH = "C:\\Users\\user\\OneDrive\\Desktop\\chandra\\SaloonWebApp\\public_html\\image";

	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public  Response uploadFile(@FormDataParam("file") InputStream fis,
			@FormDataParam("file") FormDataContentDisposition fdcd) {

		OutputStream outpuStream = null;
		String fileName = fdcd.getFileName();
		System.out.println("File Name: " + fdcd.getFileName());
		String filePath = FOLDER_PATH + File.separator + fileName;
		try {
			int read = 0;
			byte[] bytes = new byte[1024];
			outpuStream = new FileOutputStream(new File(filePath));

			while ((read = fis.read(bytes)) != -1) {
				outpuStream.write(bytes, 0, read);
			}
			outpuStream.flush();
			outpuStream.close();
		} catch (IOException e) {
			e.printStackTrace();
			logger.info(e.getStackTrace());
			SaloonErrorModel error = new SaloonErrorModel(ErrorCode.SC_APPLICATION_ERROR, e.getMessage(), false);
			return Response.status(500).entity(serializeToJSONString(error)).build();
		} finally {
			if (outpuStream != null) {
				try {
					outpuStream.close();
				} catch (Exception ex) {
				}
			}
		}
		return Response.status(200).entity("Success").build();
	}
}