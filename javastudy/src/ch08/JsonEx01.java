package ch08;

import com.google.gson.Gson;

class Family {
	int number = 2;
	String father = "ȫ�Ǽ�";
	String mother = "�ἶ";
}

class Person {
	String name = "ȫ�浿";
	int age = 25;
	String gender = "��";
	String add = "����Ư���� ��õ�� ��";
	String[] hobby = { "��", "����" };
	Family family = new Family();
	String company = "��� ������ �ȴޱ� �츸��";
}

public class JsonEx01 {
	public static void main(String[] args) {
		Gson gson = new Gson();
		// fromJson() �Լ� : json(String) -> java
		// toJson() �Լ� : java -> json(String)
		String personJson = gson.toJson(new Person());
		System.out.println(personJson);
	}
}
