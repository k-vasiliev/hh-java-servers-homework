package entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Counter {
	private String date;
	private Integer count;
	
	public Counter() {
		this.count = 0;
		this.date = (new Date()).toString();
	}
	
	public Counter(Integer count) {
		this.count = count;
		this.date = (new Date()).toString();
	}
	
	public void setCount(Integer count) {
		this.count = count;
	}
	
	public Integer getCount() {
		return count;
	}
	
	public void setDate(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy  HH:mm:ss");
		this.date = dateFormat.format(date);
	}
	
	public String getDate() {
		return date;
	}
	
	public void increaseCount() {
		count++;
	}
	
	public void decreaseCount(Integer value) {
		count -= value;
	}
	
	public void clearCount() {
		count = 0;
	}
}
