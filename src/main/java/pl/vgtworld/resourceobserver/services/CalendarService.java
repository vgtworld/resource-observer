package pl.vgtworld.resourceobserver.services;

import pl.vgtworld.resourceobserver.core.CalendarUtil;
import pl.vgtworld.resourceobserver.services.dto.Scan;
import pl.vgtworld.resourceobserver.services.dto.calendars.DayVersionsCell;
import pl.vgtworld.resourceobserver.services.dto.calendars.MonthVersionsTable;
import pl.vgtworld.resourceobserver.services.dto.calendars.WeekVersionsRow;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Stateless
public class CalendarService {

	@EJB
	private StatsService statsService;

	public MonthVersionsTable findResourceVersionsInMonth(int resourceId, int year, int month) {
		List<Scan> versions = statsService.findResourceVersionsInMonth(resourceId, year, month);
		CalendarGroupByDayHelper groupByDayHelper = new CalendarGroupByDayHelper();
		Map<Integer, List<Scan>> versionsByDay = groupByDayHelper.groupVersionsByDayOfMonth(versions);
		int days = CalendarUtil.getNumberOfDaysInMonth(year, month);

		MonthVersionsTable monthContainer = new MonthVersionsTable();
		monthContainer.setWeekDayNames(CalendarUtil.getWeekDayNames(Locale.getDefault()));
		monthContainer.setWeeks(new ArrayList<>());
		WeekVersionsRow weekContainer = new WeekVersionsRow();
		weekContainer.setDays(new ArrayList<>());
		int emptyDays = CalendarUtil.getEmptyDaysInFirstWeekOfMonth(year, month, Locale.getDefault());
		for (int i = 1; i <= emptyDays; ++i) {
			weekContainer.getDays().add(new DayVersionsCell());
		}
		int currentlyProcessedWeekDay = emptyDays;
		for (int i = 1; i <= days; ++i) {
			++currentlyProcessedWeekDay;
			if (currentlyProcessedWeekDay == 1) {
				weekContainer = new WeekVersionsRow();
				weekContainer.setDays(new ArrayList<>());
			}
			DayVersionsCell dayCell = new DayVersionsCell();
			dayCell.setDayOfMonth(i);
			CalendarUniqueVersionsHelper uniqueVersionsHelper = new CalendarUniqueVersionsHelper();
			dayCell.setVersions(uniqueVersionsHelper.extractUniqueVersionListBySnapshotId(versionsByDay.get(i)));
			weekContainer.getDays().add(dayCell);
			if (currentlyProcessedWeekDay == 7 || i == days) {
				monthContainer.getWeeks().add(weekContainer);
				currentlyProcessedWeekDay = 0;
			}
		}
		while (weekContainer.getDays().size() < 7) {
			weekContainer.getDays().add(new DayVersionsCell());
		}
		return monthContainer;
	}

}
