package gsontest;

import com.google.gson.Gson;

class Person {
	String name;
	int age;
	String gender;
	String add;
	String[] hobby;
	Family family;
	String company;
}

class Family {
	int number;
	String father;
	String mother;
}

public class GsonEx01 {

	public static void main(String[] args) {
		String jsonPerson = "{\"name\":\"ȫ�浿\",\"age\":25,\"gender\":\"��\",\"add\":\"����Ư���� ��õ�� ��\",\"hobby\":[\"��\",\"����\"],\"family\":{\"number\":2,\"father\":\"ȫ�Ǽ�\",\"mother\":\"�ἶ\"},\"company\":\"��� ������ �ȴޱ� �츸��\"}\r\n"
				+ "";

		Gson gson = new Gson();
		Person p = gson.fromJson(jsonPerson, Person.class);

		System.out.println(p.add);
		System.out.println(p.family.father);
	}
}
