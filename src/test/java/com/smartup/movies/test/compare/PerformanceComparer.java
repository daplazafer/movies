package com.smartup.movies.test.compare;

import java.util.Iterator;

import com.smartup.movies.dto.PerformanceActorDTO;
import com.smartup.movies.dto.PerformanceDTO;
import com.smartup.movies.entity.Performance;

public class PerformanceComparer {

	private PerformanceComparer() {
	}

	public static boolean comparePerformanceToPerformanceDTO(Performance p, PerformanceDTO pdto) {
		return p.getId().getActor().getId().equals(pdto.getActor_id())
				&& p.getId().getMovie().getId().equals(pdto.getMovie_id());
	}

	public static boolean comparePerformanceToPerformanceActorDTO(Performance p, PerformanceActorDTO pdto) {
		return p.getId().getActor().getId().equals(pdto.getActor_id());
	}

	public static boolean comparePerformanceLists(Iterable<Performance> a, Iterable<PerformanceActorDTO> b) {
		Iterator<Performance> ita = a.iterator();
		Iterator<PerformanceActorDTO> itb = b.iterator();
		while (ita.hasNext() && itb.hasNext())
			if (!comparePerformanceToPerformanceActorDTO(ita.next(), itb.next()))
				return false;
		if (ita.hasNext() != itb.hasNext())
			return false;
		return true;
	}

}
