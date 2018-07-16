package com.asc.holiday.utils;

import com.asc.holiday.entity.Holiday;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * http://tools.2345.com/rili.htm 网抓
 * @author dijian
 * @create 2017年1月22日
 */
public class HolidayUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(HolidayUtil.class);

	/**
	 * 抓取本月节假日
	 * @return
	 */
	public static List<Holiday> getHolidayList() {
		 Calendar c = Calendar.getInstance();
		 int year = c.get(Calendar.YEAR);
		 int curMonth = c.get(Calendar.MONTH)+1;
		 return getHolidayList(year, curMonth);
	}

	/**
	 * 抓取指定年月节假日
	 * @param year
	 * @param month
	 * @return
	 */
	public static List<Holiday> getHolidayList(int year, int month) {
		WebClient webClient = null;
		List<Holiday> holidayList = new ArrayList<Holiday>();
		try {
			webClient = new WebClient();
            new InterceptWebConnection(webClient);
			webClient.getCache().clear();
			
			webClient.getOptions().setJavaScriptEnabled(true);
			webClient.getOptions().setActiveXNative(false);
			webClient.getOptions().setCssEnabled(false);
			webClient.getOptions().setThrowExceptionOnScriptError(false);
			webClient.waitForBackgroundJavaScript(8 * 1000);
			webClient.setAjaxController(new NicelyResynchronizingAjaxController());

			webClient.getOptions().setTimeout(60000);// 毫秒

			HtmlPage page = webClient.getPage("http://tools.2345.com/rili.htm");


			String monthStr = "";

			DomElement yearElement = page.getElementById("current-data");
			String yearTxt =  (year - 1) + "年";
			yearElement.setTextContent(yearTxt);
			page = (HtmlPage) page.executeJavaScript("javascript:pushBtm('YD');").getNewPage();
			Thread.sleep(2000);

			DomElement monthElement = page.getElementById("current-data-month");
			String monthTxt = (month - 1) + "月";
			monthElement.setTextContent(monthTxt);

			page = (HtmlPage) page.executeJavaScript("javascript:pushBtm('MD');").getNewPage();
			Thread.sleep(2000);

			if (month < 10) {
				monthStr = "0" + month;
			} else {
				monthStr = "" + month;
			}

			List<HtmlElement> htmlElements =  (List<HtmlElement>) page.getByXPath("//ol//li");
			LOGGER.debug("获取到元素个数："+ htmlElements.size());

			for (HtmlElement element : htmlElements) {
				if (element.getAttribute("class").indexOf("cross_month") != -1) {
					continue;
				}

				String style = element.getAttribute("style");
				if (StringUtils.isNotBlank(style)) {
					style = style.replace(" ", "");
				}

				if (style.indexOf("display:none") != -1) {
					continue;
				}

				List<HtmlElement> solar = element.getElementsByAttribute("div","class", "solar");
				String day = solar.get(0).asText();

				String curDay = year + "-" + monthStr + "-" + day;
				Date cDate = DateUtils.stringToDate(curDay, "yyyy-MM-dd");

				if (element.getAttribute("class").indexOf("rest") != -1) {
					Holiday holiday = new Holiday();
					holiday.setcDate(cDate);
					holiday.setHoliday(1);
					holidayList.add(holiday);
				} else if (element.getAttribute("class").indexOf("weekend") != -1) {
					List<HtmlElement> status = element.getElementsByAttribute("span", "class", "status");
					if (status.isEmpty() || !"班".equals(status.get(0).asText())) {
						Holiday holiday = new Holiday();
						holiday.setcDate(cDate);
						holiday.setHoliday(1);
						holidayList.add(holiday);
					} else {
						Holiday holiday = new Holiday();
						holiday.setcDate(cDate);
						holiday.setHoliday(2);
						holidayList.add(holiday);
					}
				} else {
					Holiday holiday = new Holiday();
					holiday.setcDate(cDate);
					holiday.setHoliday(2);
					holidayList.add(holiday);
				}
			}
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			LOGGER.error("网抓错误："+e.getMessage());
			e.printStackTrace();
		} finally {
			if (webClient != null) {
				webClient.close();
			}
		}
		return holidayList;
	}

}
