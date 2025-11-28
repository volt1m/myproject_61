package com.cuit.house.service;

import com.google.common.io.Files;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.List;

@Service
public class FileService {
    @Value("${file.path}")
    private String filePath;

    //存储图片到e盘,并且返回存储地址
    public List<String> getImgPath(List<MultipartFile> files){
        List<String> paths= Lists.newArrayList();
        files.forEach(file ->{
           //这个方法就是把图片存储到本地
            File localFile = null;
            try {
                localFile = saveToLocal(file,filePath);
                String absPath = localFile.getAbsolutePath().replace("\\", "/");
                String basePath = filePath.replace("\\", "/");
                String path = StringUtils.substringAfterLast(absPath, basePath);
                paths.add(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            //但是我只需要相对路径
        });
        return paths;
    }

    private File saveToLocal(MultipartFile file, String filePath) throws IOException {
        //用时间戳
        File newFile=new File(filePath+"/"+ Instant.now().getEpochSecond()+"/"+file.getOriginalFilename());
        if(!newFile.exists()){
            newFile.getParentFile().mkdirs();
            newFile.createNewFile();
        }
        Files.write(file.getBytes(),newFile);
        return newFile;
    }
}
