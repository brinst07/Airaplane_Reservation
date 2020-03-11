package controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import dao.AirplaneTicketDao;
import data.Database;
import data.Session;
import service.AirportService;
import service.CityService;
import service.CleardelayService;
import service.CountryService;
import service.ReservationTicketService;
import service.Sitservice;
import service.TimeTableService;
import service.UserService;
import vo.AirplaneTicketVO;
import vo.AirplaneVO;
import vo.UserVO;

public class ReservationTicketController {
	
	ReservationTicketService rt = new ReservationTicketService();

	public void start() {
		rt.start();
	}
}