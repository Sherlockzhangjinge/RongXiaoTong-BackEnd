package com.qst.crop.controller;

import com.qst.crop.common.Result;
import com.qst.crop.common.StatusCode;
import com.qst.crop.exception.FileFormatException;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/file")
public class OrderFileController {

    @Value("${application.upload-path}")
    private String baseUploadDir;



    // 文件上传
    @PostMapping("/upload/{type}")
    @ApiOperation(value = "文件上传")
    public Result<String> upload(@PathVariable("type") String type, @RequestParam("file") MultipartFile file) {
        List<String> ALLOWED_EXTENSIONS = Arrays.asList(".jpg", ".jpeg", ".png", ".gif", ".webp", ".mp4");
        // 验证文件类型
        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null) {
            return new Result<>(false, StatusCode.ERROR, "文件名不能为空", "文件名不能为空");
        }
        String fileExtension = getFileExtension(originalFilename);
        if (!ALLOWED_EXTENSIONS.contains(fileExtension)) {
            return new Result<>(false, StatusCode.ERROR, "文件格式不正确", "文件格式不正确");
        }

        try {
            // 创建目录
            Path targetDir = Paths.get(baseUploadDir, type);
            if (Files.notExists(targetDir)) {
                Files.createDirectories(targetDir);
            }

            // 创建文件
            String newFileName = UUID.randomUUID().toString().replaceAll("-", "") + fileExtension;
            Path targetFile = targetDir.resolve(newFileName);

            // 保存文件
            file.transferTo(targetFile.toFile());

            return new Result<>(true, StatusCode.OK, "文件上传成功", newFileName);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result<>(false, StatusCode.ERROR, "文件上传失败：" + e.getMessage(), "文件上传失败：" + e.getMessage());
        }
    }

    // 获取文件扩展名
    private String getFileExtension(String filename) {
        int lastIndexOfDot = filename.lastIndexOf('.');
        return (lastIndexOfDot == -1) ? "" : filename.substring(lastIndexOfDot).toLowerCase();
    }


    @PostMapping("/uploadInfo/{type}")
    @ApiOperation(value = "文件上传")
    public Result<String> upload1(@PathVariable("type") String type, @RequestParam("file") MultipartFile file) {
        List<String> ALLOWED_EXTENSIONS = Arrays.asList(".jpg", ".jpeg", ".png", ".gif", ".webp", ".mp4");
        // 验证文件类型
        String originalFilename1 = file.getOriginalFilename();
        if (originalFilename1 == null) {
            return new Result<>(false, StatusCode.ERROR, "文件名不能为空", "文件名不能为空");
        }
        String fileExtension = getFileExtension(originalFilename1);
        if (!ALLOWED_EXTENSIONS.contains(fileExtension)) {
            return new Result<>(false, StatusCode.ERROR, "文件格式不正确", "文件格式不正确");
        }

        try {
            // 创建目录
            Path targetDir = Paths.get(baseUploadDir, type);
            if (Files.notExists(targetDir)) {
                Files.createDirectories(targetDir);
            }

            // 创建文件
            String newFileName = UUID.randomUUID().toString().replaceAll("-", "") + fileExtension;
            Path targetFile = targetDir.resolve(newFileName);

            // 保存文件
            file.transferTo(targetFile.toFile());

            return new Result<>(true, StatusCode.OK, "文件上传成功", newFileName);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result<>(false, StatusCode.ERROR, "文件上传失败：" + e.getMessage(), "文件上传失败：" + e.getMessage());
        }
    }

    // 获取文件扩展名
    private String getFileExtension1(String filename) {
        int lastIndexOfDot = filename.lastIndexOf('.');
        return (lastIndexOfDot == -1) ? "" : filename.substring(lastIndexOfDot).toLowerCase();
    }
}