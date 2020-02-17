package com.example.demo.service;

import com.example.demo.dto.NotificationDTO;
import com.example.demo.dto.PageDTO;
import com.example.demo.dto.QuestionDTO;
import com.example.demo.enums.NotificationTypeEnum;
import com.example.demo.mapper.NotificationMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.*;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class NotificationService {

    @Autowired
    private NotificationMapper notificationMapper;

    @Autowired
    private UserMapper userMapper;

    public PageDTO list(Long userId, Integer page, Integer size) {
        PageDTO<NotificationDTO> pageDTO=new PageDTO<>();
        Integer totalPage;

        NotificationExample example = new NotificationExample();
        example.createCriteria()
                .andReceiverEqualTo(userId);
        Integer totalCount=(int)notificationMapper.countByExample(example);

        if(totalCount%size==0){
            totalPage=totalCount/size;
        }else{
            totalPage=totalCount/size+1;
        }

        if(page<1){
            page=1;
        }
        if(page>totalPage){
            page=totalPage;
        }
        pageDTO.setPageDTO(totalPage,page);

        //5*(i-1)
        Integer offset=size*(page-1);
        NotificationExample example1 = new NotificationExample();
        example1.createCriteria()
                .andReceiverEqualTo(userId);
        List<Notification> notifications=notificationMapper.selectByExampleWithRowbounds(example1,new RowBounds(offset,size));

        if(notifications.size()==0){
            return pageDTO;
        }

        List<NotificationDTO> notificationDTOS=new ArrayList<>();
        for (Notification notification : notifications) {
            NotificationDTO notificationDTO = new NotificationDTO();
            BeanUtils.copyProperties(notification,notificationDTO);
            notificationDTO.setType(NotificationTypeEnum.nameOfType(notification.getType()));
            notificationDTOS.add(notificationDTO);
        }

        pageDTO.setData(notificationDTOS);
        return pageDTO;
    }
}
