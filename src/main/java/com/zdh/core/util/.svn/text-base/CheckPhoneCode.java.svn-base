package com.zdh.core.util;

public class CheckPhoneCode {

	public static double getMinutesDiff(String startDate, String endDate) {
		double ret = 0;
		if ("".equals(startDate) || "".equals(endDate)) {
			// return ret;
		} else {
			String startDateStr[] = startDate.split(":");
			String endDateStr[] = endDate.split(":");
			if (startDateStr[1].startsWith("0")) {
				startDateStr[1] = startDateStr[1].substring(1);
			}
			if (startDateStr[2].startsWith("0")) {
				startDateStr[2] = startDateStr[2].substring(1);
			}
			if (endDateStr[1].startsWith("0")) {
				endDateStr[1] = endDateStr[1].substring(1);
			}
			if (endDateStr[2].startsWith("0")) {
				endDateStr[2] = endDateStr[2].substring(1);
			}
			if (startDateStr[0].equals(endDateStr[0])) {
				double s = Double.valueOf(startDateStr[1]) + (Double.valueOf((Double.valueOf(startDateStr[2])) / 60));
				double e = Integer.parseInt(endDateStr[1]) + (Double.valueOf((Double.valueOf(endDateStr[2])) / 60));
				ret = e - s;
			} else {
				ret = 15;
			}
		}
		return ret;
	}
}
