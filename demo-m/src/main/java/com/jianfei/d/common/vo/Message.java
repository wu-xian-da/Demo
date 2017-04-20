package com.jianfei.d.common.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 系统消息
* @author ZhangBo   
* @date 2017年4月14日 下午3:07:53
 */
@Getter
@Setter
@AllArgsConstructor
public class Message {

    private String info;
    
    private MessageStatus status = MessageStatus.SUC;
    
}
