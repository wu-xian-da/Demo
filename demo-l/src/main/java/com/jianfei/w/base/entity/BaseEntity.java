package com.jianfei.w.base.entity;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@EqualsAndHashCode(of={"id"})
public class BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 12297571103460457L;
	/**
	 * Ö÷¼ü
	 */
	protected long id;

}
