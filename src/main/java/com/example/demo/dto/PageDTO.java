package com.example.demo.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PageDTO {
    private List<QuestionDTO> questions;
    private boolean showPrevious;//是否显示 <上一页
    private boolean showNext;//是否显示 下一页>
    private boolean showFirstPage=false;//是否显示 第一页<<
    private boolean showEnd;//是否显示 最后一页>>

    private Integer page;//当前页面
    private List<Integer> pages=new ArrayList<>();
    private Integer totalPage;


    public void setPageDTO(Integer totalCount, Integer page, Integer size) {

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
        this.page=page;

        pages.add(page);
        for(int i=1;i<=3;i++){
            if(page>i){
                pages.add(0,page-i);//这个是加在头部位置
            }
            if(page+i<=totalPage){
                pages.add(page+i);
            }
        }

        //是否展示上一页
        if(page==1){
            showPrevious=false;
        }else{
            showPrevious=true;
        }

        //是否展示下一页
        if(page==totalPage){
            showNext=false;
        }else{
            showNext=true;
        }

        //是否显示 第一页<<
        if(pages.contains(1)){
            showFirstPage=false;
        }else{
            showFirstPage=true;
        }

        //是否显示 最后一页>>
        if(pages.contains(totalPage)){
            showEnd=false;
        }else{
            showEnd=true;
        }

    }
}
