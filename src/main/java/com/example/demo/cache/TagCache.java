package com.example.demo.cache;

import com.example.demo.dto.TagDTO;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TagCache {
    public static List<TagDTO> get(){
        List<TagDTO> tagDTOS = new ArrayList<>();
        TagDTO program = new TagDTO();
        program.setCategoryName("开发语言");
        program.setTags(Arrays.asList("java","php","node","c","c#","c++","javascript","typescript","flutter",
                "perl","python","ruby","go","lua","erlang","scala","bash","actionscript","golang"));

        TagDTO framework = new TagDTO();
        framework.setCategoryName("平台框架");
        framework.setTags(Arrays.asList("laravel","spring","express","django","flask","yii","ruby-on-rails",
                "tornado","koa","struts"));

        TagDTO server = new TagDTO();
        server.setCategoryName("服务器");
        server.setTags(Arrays.asList("linux","unix","ubuntu","windows-server","centos","负载均衡","缓存","apache","nginx"));

        TagDTO db = new TagDTO();
        db.setCategoryName("数据库");
        db.setTags(Arrays.asList("mysql","sqlite","oracle","sql","nosql","redis","mongodb","memcached","postgresql"));

        TagDTO tool = new TagDTO();
        tool.setCategoryName("开发工具");
        tool.setTags(Arrays.asList("vim","emacs","idea","eclipse","xcode","textmate","git","github","svn","docker","visual-studio"));

        tagDTOS.add(program);
        tagDTOS.add(framework);
        tagDTOS.add(server);
        tagDTOS.add(db);
        tagDTOS.add(tool);

        return tagDTOS;
    }

    //过滤非法标签
    public static String filterInvalid(String tags){
        String[] split = StringUtils.split(tags, ",");
        List<TagDTO> tagDTOS = get();

        List<String> tagList = tagDTOS.stream().flatMap(tag -> tag.getTags().stream()).collect(Collectors.toList());
        String invalid = Arrays.stream(split).filter(t -> !tagList.contains(t)).collect(Collectors.joining(","));
        return invalid;
    }
}
