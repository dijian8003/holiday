package com.asc.holiday.entity;

import com.asc.holiday.utils.DateUtils;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * 节假日
 * @author dijian
 * @create 2017年8月21日
 */
@Entity
@Table(name="CJ_HOLIDAY")
public class Holiday implements Serializable {


	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy="com.asc.holiday.utils.StringSequenceGenerator")
	private String id;

	private Date cDate;

	private int holiday;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getcDate() {
		return cDate;
	}

	public void setcDate(Date cDate) {
		this.cDate = cDate;
	}

	public int getHoliday() {
		return holiday;
	}

	public void setHoliday(int holiday) {
		this.holiday = holiday;
	}

	@Override
	public String toString() {
		return DateUtils.dateToString(cDate,"yyy-MM-dd") + ":"+(holiday==1?"节假日":"工作日");
	}

}
