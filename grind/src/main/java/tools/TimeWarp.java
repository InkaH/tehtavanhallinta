package tools;

import java.time.LocalDateTime;

public class TimeWarp {
	
	public static LocalDateTime startOfNextWeek() {
		LocalDateTime now = LocalDateTime.now();
		int dayOfWeek = now.getDayOfWeek().getValue(); // 1 monday ... 7 sunday
		LocalDateTime startOfWeek = now.minusDays((long) (dayOfWeek - 1));
		startOfWeek = LocalDateTime.of(startOfWeek.getYear(), startOfWeek.getMonthValue(), startOfWeek.getDayOfMonth(), 0, 0, 0);
		LocalDateTime startOfNextWeek = startOfWeek.plusWeeks(1);
		return startOfNextWeek;
	}
	
	public static LocalDateTime endOfNextWeek() {
		LocalDateTime now = LocalDateTime.now();
		int dayOfWeek = now.getDayOfWeek().getValue(); // 1 monday ... 7 sunday
		LocalDateTime endOfWeek = now.plusDays((long) (7 - dayOfWeek));
		endOfWeek = LocalDateTime.of(endOfWeek.getYear(), endOfWeek.getMonthValue(), endOfWeek.getDayOfMonth(), 23, 59, 59);
		LocalDateTime endOfNextWeek = endOfWeek.plusWeeks(1);
		return endOfNextWeek;
	}
	
	public static LocalDateTime startOfCurrentWeek() {
		LocalDateTime now = LocalDateTime.now();
		int dayOfWeek = now.getDayOfWeek().getValue(); // 1 monday ... 7 sunday
		LocalDateTime startOfWeek = now.minusDays((long) (dayOfWeek - 1));
		startOfWeek = LocalDateTime.of(startOfWeek.getYear(), startOfWeek.getMonthValue(), startOfWeek.getDayOfMonth(), 0, 0, 0);
		return startOfWeek;
	}
	
	public static LocalDateTime endOfCurrentWeek() {
		LocalDateTime now = LocalDateTime.now();
		int dayOfWeek = now.getDayOfWeek().getValue(); // 1 monday ... 7 sunday
		LocalDateTime endOfWeek = now.plusDays((long) (7 - dayOfWeek));
		endOfWeek = LocalDateTime.of(endOfWeek.getYear(), endOfWeek.getMonthValue(), endOfWeek.getDayOfMonth(), 23, 59, 59);
		return endOfWeek;
	}
}
