package pl.agh.edu.raportex;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class Raport1GeneratorTest {

	@Test
	public void test() {
		// given

		ArrayList<Record> list = new ArrayList<>();

		Record r1 = new Record("2017", "07", "Kowalski_Jan", "projekt1", "posprzątanie", 30, "C:/");
		Record r2 = new Record("2017", "07", "Kowalski_Mariusz", "projekt2", "posprzątanie2", 10, "C:/");
		Record r3 = new Record("2017", "07", "Kowalski_Mariusz", "projekt3", "posprzątanie3", 4.5, "C:/");
		Record r4 = new Record("2017", "07", "Kowalski_Mariusz", "projekt4", "posprzątanie3", 20, "C:/");

		list.add(r1);
		list.add(r2);
		list.add(r3);
		list.add(r4);

		RaportGenerator generator = new RaportGenerator();

		// when
		List<Raport1Record> result = generator.makeRaportOne(list);

		// then
		assertEquals(2, result.size());
		assertEquals(34.5, result.get(1).getTime(), 0.1);
	}

}
