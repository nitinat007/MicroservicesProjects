package com.nitin.SampleSBApplication.controller;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
public class FileHandlingRestController {

    @RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        File newFile = new File(System.getProperty("user.dir") + File.separator + "files" + File.separator + "uploadTo" + File.separator + file.getOriginalFilename());
        newFile.createNewFile();
        FileOutputStream fout = new FileOutputStream(newFile);
        fout.write(file.getBytes());
        fout.close();
        System.out.println(newFile.getAbsolutePath());
        return "uploaded file";
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public ResponseEntity<Object> downloadFile() throws IOException {

        File newFile = new File(System.getProperty("user.dir") + File.separator + "files" + File.separator + "downloadFrom" + File.separator + "file.txt");
        InputStreamResource inputStreamResource = new InputStreamResource(new FileInputStream(newFile));

        HttpHeaders headers = new HttpHeaders();

        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", newFile.getName()));
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        ResponseEntity<Object> responseEntity = ResponseEntity.ok().headers(headers).contentType(MediaType.parseMediaType("application/txt"))
                .contentLength(newFile.length()).body(inputStreamResource);

        return responseEntity;
    }

}
