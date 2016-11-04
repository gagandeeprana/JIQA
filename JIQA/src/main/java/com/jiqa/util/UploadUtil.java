package com.jiqa.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

public class UploadUtil {

	private String uploadImagePath = "/usr/local/apache-tomcat/webapps/CategoryImages";
	
	static final Logger logger = Logger.getLogger(UploadUtil.class);
	
    public void processRequest(MultipartFile multipart, String title) throws ServletException, IOException {

        String rootpath = uploadImagePath;
        String filename = "", msg = "";
        try {	
        	
        	logger.info("UploadUtil: processRequest: ");
            if (multipart != null) {
                InputStream is = null;
                FileOutputStream fos = null;
//                String header = multipart.getHeader("content-disposition");
//                filename = getFileName(header);
                filename = multipart.getOriginalFilename();
            	logger.info("UploadUtil: processRequest: filename: " + filename);
            	rootpath = rootpath + File.separator + title + File.separator + filename;
//            	rootpath = rootpath.replace(" ", "%20");
                File fileToUpload = new File(rootpath);

                if (!fileToUpload.getParentFile().exists()) {
                    fileToUpload.getParentFile().mkdirs();
                }

                if (fileToUpload.isFile()) {
                    msg = "file already exists";
                } else {
                	logger.info("UploadUtil: processRequest: going to upload: ");

                    is = multipart.getInputStream();
                    fos = new FileOutputStream(fileToUpload);
                    byte[] buffer = new byte[4096];
                    int count = 0;
                    while ((count = is.read(buffer)) != -1) {
                        fos.write(buffer, 0, count);
                    }
                    fos.flush();
                    fos.close();
                	logger.info("UploadUtil: processRequest: uploading Done");

                }
            }
        } catch(Exception e) {
        	logger.error("UploadUtil: processRequest: Exception is: " + e.getMessage());
        }
        
        
      //***************************************************************************************************
        
      /*  String existingBucketName  = "*** Provide-Your-Existing-BucketName ***"; 
        String keyName             = "*** Provide-Key-Name ***";
        String filePath            = "*** Provide-File-Path ***";   
        
        AmazonS3 s3Client = new AmazonS3Client(new ProfileCredentialsProvider());        

        // Create a list of UploadPartResponse objects. You get one of these
        // for each part upload.
        List<PartETag> partETags = new ArrayList<PartETag>();

        // Step 1: Initialize.
        InitiateMultipartUploadRequest initRequest = new 
             InitiateMultipartUploadRequest(existingBucketName, keyName);
        InitiateMultipartUploadResult initResponse = 
        	                   s3Client.initiateMultipartUpload(initRequest);

        File file = new File(filePath);
        long contentLength = file.length();
        long partSize = 5242880; // Set part size to 5 MB.

        try {
            // Step 2: Upload parts.
            long filePosition = 0;
            for (int i = 1; filePosition < contentLength; i++) {
                // Last part can be less than 5 MB. Adjust part size.
            	partSize = Math.min(partSize, (contentLength - filePosition));
            	
                // Create request to upload a part.
                UploadPartRequest uploadRequest = new UploadPartRequest()
                    .withBucketName(existingBucketName).withKey(keyName)
                    .withUploadId(initResponse.getUploadId()).withPartNumber(i)
                    .withFileOffset(filePosition)
                    .withFile(file)
                    .withPartSize(partSize);

                // Upload part and add response to our list.
                partETags.add(
                		s3Client.uploadPart(uploadRequest).getPartETag());

                filePosition += partSize;
            }

            // Step 3: Complete.
            CompleteMultipartUploadRequest compRequest = new 
                         CompleteMultipartUploadRequest(
                                    existingBucketName, 
                                    keyName, 
                                    initResponse.getUploadId(), 
                                    partETags);

            s3Client.completeMultipartUpload(compRequest);
        } catch (Exception e) {
            s3Client.abortMultipartUpload(new AbortMultipartUploadRequest(
                    existingBucketName, keyName, initResponse.getUploadId()));
        }*/
    }

    /*private String getFileName(String header) {
        String[] data = header.split(";");
        return data[2].substring(11, data[2].length() - 1);
    }*/
    
    
}

