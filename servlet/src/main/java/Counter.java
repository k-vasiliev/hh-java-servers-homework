public class Counter {
	private Integer count;
	
	Counter(int count) {
		this.count = count;
	}
	
	public int getCount() {
		return count;
	}
	
	public void setCount(int count) {
		this.count = count;
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
