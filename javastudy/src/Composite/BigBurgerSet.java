package Composite;

import lombok.Data;

// �ڹٴ� ���� ����� �ȵ�(�θ� �θ��� �� ����)
@Data
public class BigBurgerSet {
	private BigBurger bigBurger;
	private Coke coke;
	private FrenceFried frenceFried;
	
	public BigBurgerSet(BigBurger bigBurger) {
		this(
			 bigBurger,
			 new Coke(1500,"��ī�ݶ�"),
			 new FrenceFried(2000,"����Ĩ")
			);
	}
	
	public BigBurgerSet() {
		this(
			 new BigBurger(),
			 new Coke(),
			 new FrenceFried()
			);
	}

	public BigBurgerSet(BigBurger bigBurger, Coke coke, FrenceFried frenceFried) {
		this.bigBurger = bigBurger;
		this.coke = coke;
		this.frenceFried = frenceFried;
	}
}