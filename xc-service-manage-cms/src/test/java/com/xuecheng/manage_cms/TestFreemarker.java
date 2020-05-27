package com.xuecheng.manage_cms;

import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.GridFSFindIterable;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.mongodb.gridfs.GridFS;
import lombok.AllArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author wangjun
 * @version 1.0
 * @date 2020/5/15 10:20
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestFreemarker {
    @Autowired
    private GridFsTemplate gridFsTemplate;
    @Autowired
    GridFSBucket gridFSBucket;

    @Test
    public void saveFile() throws FileNotFoundException {
        // 要存储的文件
        File file = new File("D:/index_banner.ftl");
        // 定义输入流
        FileInputStream fileInputStream = new FileInputStream(file);
        // 向gridfs存入文件
        ObjectId id = gridFsTemplate.store(fileInputStream, "轮播图测试001");

        System.out.println(id.toString());
    }

    @Test
    public void queryFile() throws IOException {
        String fileId = "5ebe4ee22ee64b44f0b67f6d";
        //根据id查询文件
        GridFSFile gridFSFile = gridFsTemplate.findOne(Query.query(Criteria.where("_id").is(fileId)));
        //打开下载流对象
        GridFSDownloadStream gridFSDownloadStream = gridFSBucket.openDownloadStream(gridFSFile.getObjectId());
        //创建gridFsResource，用于获取流对象
        GridFsResource gridFsResource = new GridFsResource(gridFSFile, gridFSDownloadStream);
        //获取流中的数据
        String s = IOUtils.toString(gridFsResource.getInputStream(), "UTF-8");
        System.out.println(s);
    }

    @Test
    public void delFile(){
        String grsid="5ebe2e1a2ee64b3474eaa662";
        gridFsTemplate.delete(new Query(Criteria.where("_id").is(grsid)));
    }
}
