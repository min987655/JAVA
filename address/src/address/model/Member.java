package address.model;

public class Member {
	private int id; //PK
	private String name; //이름
	private String phone; // 전화번호 (연산할 일이 없기 때문에 String)
	private String address; // 주소
	// 그룹 : 친구, 회사, 학교, 가족 (도메인 설정해줘야 함)
//	private String group; // 클라이언트는 도메인 모름, 도메인 없으면 다 들어와버림
	private GroupType group; // 타입 지정-도메인 설정함(enum)
	
	// 사용할 생성자
	public Member(String name, String phone, String address, GroupType group) {
		super();
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.group = group;
	}
	
	// 더미데이터(샘플) 생성자
	public Member(int id, String name, String phone, String address, GroupType group) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.group = group;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public GroupType getGroup() {
		return group;
	}
	public void setGroup(GroupType group) {
		this.group = group;
	}
	
	@Override
	public String toString() {
		return id+". "+name;
	}
	
}
