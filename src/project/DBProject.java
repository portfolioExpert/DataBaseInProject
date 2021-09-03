package project;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class DBProject extends JFrame {
	Scanner sc = new Scanner(System.in);
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/madang?serverTimezone=Asia/Seoul&useSSL=false";
	String user = "madang";
	String pw = "madang";
	static Connection con = null;
	PreparedStatement pstmt = null;
	Statement stmt = null;
	ResultSet rs = null;
	int r = 0;

	JButton reset, minput, mdel, mupdate, mreturn, search1, search2, search3, search4, csearch, lentb;
	JTextArea marea, uarea;
	String[] tablename = { "캠핑카 대여회사", "캠핑카", "고객", "캠핑카 정비소", "정비 정보" };
	JComboBox<String> table = new JComboBox<String>(tablename);// 관리자 테이블
	JPanel managerp, userp, managerupper, userupper;

	// Label, TextField를 저장하는 패널들
	JPanel mdown = new JPanel();

	JButton inb = new JButton("입력");
	JButton delb = new JButton("삭제(주키만 입력하면 삭제)");
	JButton updateb = new JButton("변경(주키 변경 불가)");
	JButton returncar = new JButton("캠핑카 반환");

	// 캠핑카 대여회사 속성값
	JLabel cid = new JLabel("캠핑카 대여회사ID(주키)");
	JTextField idt = new JTextField(30);
	JLabel cname = new JLabel("     캠핑카 회사 명");
	JTextField namet = new JTextField(30);
	JLabel cadd = new JLabel("       회사 주소");
	JTextField caddt = new JTextField(30);
	JLabel cnumber = new JLabel("회사 전화번호");
	JTextField cnumbert = new JTextField(30);
	JLabel cmname = new JLabel("담당자 이름");
	JTextField cmnamet = new JTextField(30);
	JLabel cmemail = new JLabel("담당자 이메일");
	JTextField cmemailt = new JTextField(30);

	// 캠핑카 속성값
	JLabel carid = new JLabel("캠핑카 등록ID(주키)");
	JTextField caridt = new JTextField(30);
	JLabel carname = new JLabel("캠핑카 이름 ");
	JTextField carnamet = new JTextField(30);
	JLabel carnumber = new JLabel("          차량 번호");
	JTextField carnumbert = new JTextField(30);
	JLabel carenable = new JLabel("          승차 인원 수");
	JTextField carenablet = new JTextField(30);
	JLabel carmaker = new JLabel("            제조 회사");
	JTextField carmakert = new JTextField(30);
	JLabel carmakeyear = new JLabel("제조 연도");
	JTextField carmakeyeart = new JTextField(30);
	JLabel stackdis = new JLabel("         누적 주행거리");
	JTextField stackdist = new JTextField(30);
	JLabel lentval = new JLabel("        대여 비용");
	JTextField lentvalt = new JTextField(30);
	JLabel lentcID = new JLabel("대여 회사 ID(외래키)");
	JTextField lentcIDt = new JTextField(30);
	JLabel regidate = new JLabel("등록일자");
	JTextField regidatet = new JTextField(30);

	// 고객 속성 값
	JLabel licenid = new JLabel("운전면허증(주키)");
	JTextField licenidt = new JTextField(30);
	JLabel clname = new JLabel("       고객명");
	JTextField clnamet = new JTextField(30);
	JLabel cladd = new JLabel("               고객 주소");
	JTextField claddt = new JTextField(30);
	JLabel clnumber = new JLabel("       고객 전화번호");
	JTextField clnumbert = new JTextField(30);
	JLabel clemail = new JLabel("        고객 이메일 정보");
	JTextField clemailt = new JTextField(30);

	// 캠핑카 정비소 속성 값
	JLabel repaid = new JLabel("정비소 ID(주키)");
	JTextField repaidt = new JTextField(30);
	JLabel repaname = new JLabel("               정비소 명");
	JTextField repanamet = new JTextField(30);
	JLabel repaadd = new JLabel("정비소 주소");
	JTextField repaaddt = new JTextField(30);
	JLabel repanumber = new JLabel("정비소 전화번호");
	JTextField repanumbert = new JTextField(30);
	JLabel repamname = new JLabel("담당자 이름");
	JTextField repamnamet = new JTextField(30);
	JLabel repamemail = new JLabel("            이메일 정보");
	JTextField repamemailt = new JTextField(30);

	// 정비 정보 속성값
	JLabel spnumber = new JLabel("고유 정비 번호(주키)");
	JTextField spnumbert = new JTextField(30);
	JLabel carinfo = new JLabel("캠핑카 등록ID");
	JTextField carinfot = new JTextField(30);
	JLabel repainfo = new JLabel("                    정비소 ID");
	JTextField repainfot = new JTextField(30);
	JLabel cominfo = new JLabel("캠핑카 대여회사ID");
	JTextField cominfot = new JTextField(30);
	JLabel liceninfo = new JLabel("고객 운전면허증 정보");
	JTextField liceninfot = new JTextField(30);
	JLabel repairinfo = new JLabel("정비 내역");
	JTextField repairinfot = new JTextField(30);
	JLabel repairdate = new JLabel("                      수리 날짜");
	JTextField repairdatet = new JTextField(30);
	JLabel repairprice = new JLabel("수리 비용");
	JTextField repairpricet = new JTextField(30);
	JLabel paydate = new JLabel("                     납입 기간");
	JTextField paydatet = new JTextField(30);
	JLabel otherrepair = new JLabel("기타 정비내역 정보");
	JTextField otherrepairt = new JTextField(30);

	// 캠핑카 반환 UI
	JLabel returncarid = new JLabel("캠핑카 등록ID를 입력하세요");
	JTextField returncaridt = new JTextField(30);

	public DBProject() {
		connect();

		setTitle("17013139/김영현");
		setSize(1000, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container c = getContentPane();
		c.setLayout(new GridLayout(1, 2));

		// 관리자와 유저 패널을 각각 만든다
		managerp = new JPanel();// 관리자 패널
		userp = new JPanel();// 사용자 패널

		managerp.setLayout(new BorderLayout());
		userp.setLayout(new BorderLayout());

		// user위쪽
		managerupper = new JPanel();
		managerupper.setLayout(new GridLayout(3, 4));
		JLabel manager = new JLabel("관리자");
		reset = new JButton("초기화");
		reset.addActionListener(new Action());
		minput = new JButton("입력//삭제//변경");
		minput.addActionListener(new Action());
		mreturn = new JButton("반환");
		mreturn.addActionListener(new Action());
		search1 = new JButton("검색1");
		search1.addActionListener(new Action());
		search2 = new JButton("검색2");
		search2.addActionListener(new Action());
		search3 = new JButton("검색3");
		search3.addActionListener(new Action());
		search4 = new JButton("검색4");
		search4.addActionListener(new Action());
		marea = new JTextArea();

		managerupper.add(manager);
		managerupper.add(table);
		managerupper.add(reset);
		managerupper.add(minput);
		managerupper.add(mreturn);
		managerupper.add(search1);
		managerupper.add(search2);
		managerupper.add(search3);
		managerupper.add(search4);

		managerp.add(managerupper, BorderLayout.NORTH);
		mdown.add(marea);
		managerp.add(mdown, BorderLayout.CENTER);

		userupper = new JPanel();
		userupper.setLayout(new GridLayout(3, 4));
		JLabel user = new JLabel("사용자");
		csearch = new JButton("캠핑카 검색");
		csearch.addActionListener(new Action());
		lentb = new JButton("캠핑카 대여 버튼");
		lentb.addActionListener(new Action());
		uarea = new JTextArea();

		// 입력, 삭제, 변경 버튼 리스너
		inb.addActionListener(new Action());
		delb.addActionListener(new Action());
		updateb.addActionListener(new Action());
		returncar.addActionListener(new Action());

		userupper.add(user);
		userupper.add(csearch);
		userupper.add(lentb);
		userp.add(userupper, BorderLayout.NORTH);
		userp.add(uarea, BorderLayout.CENTER);

		c.add(managerp);
		c.add(userp);
		setVisible(true);
	}

	// 이벤트 처리
	public class Action implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ev) {
			// TODO Auto-generated method stub

			// 초기화 버튼
			if (ev.getSource() == reset) {
				// 테이블 있으면 삭제
				String query = "drop table if exists madang.Return_Information;";
				try {
					stmt = con.createStatement();
					stmt.execute(query);
					query = "drop table if exists madang.Car_Repair_Inform;";
					stmt.execute(query);
					query = "drop table if exists madang.Cars_Rent;";
					stmt.execute(query);
					query = "drop table if exists madang.Cars;";
					stmt.execute(query);
					query = "drop table if exists madang.Car_Rent_Comp;";
					stmt.execute(query);
					query = "drop table if exists madang.Customers;";
					stmt.execute(query);
					query = "drop table if exists madang.Car_Garage;";
					stmt.execute(query);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				// 테이블 생성
				try {
					query = "CREATE TABLE Car_Garage (\n" + "  car_garage_id INTEGER PRIMARY KEY NOT NULL,\n"
							+ "  garage_name VARCHAR(45) NOT NULL,\n" + "  garage_addr VARCHAR(45) NOT NULL,\n"
							+ "  garage_phone VARCHAR(45) NOT NULL,\n" + "  garage_admin_name VARCHAR(45) NOT NULL,\n"
							+ "  garage_admin_email VARCHAR(45) NOT NULL\n" + "  );";
					stmt.execute(query);
					query = "  CREATE TABLE Customers (\n" + "  cust_driver_license_id INTEGER  PRIMARY KEY NOT NULL,\n"
							+ "  cust_name VARCHAR(45) NOT NULL,\n" + "  cust_addr VARCHAR(45) NOT NULL,\n"
							+ "  cust_phone VARCHAR(45) NOT NULL,\n" + "  cust_email VARCHAR(45) NOT NULL\n" + ");";
					stmt.execute(query);
					query = "CREATE TABLE Car_Rent_Comp  (\n" + "  car_rent_comp_id INTEGER PRIMARY KEY NOT NULL ,\n"
							+ "  comp_name VARCHAR(45) NOT NULL,\n" + "  comp_addr VARCHAR(45) NOT NULL,\n"
							+ "  comp_phone VARCHAR(45) NOT NULL,\n" + "  comp_admin_name VARCHAR(45) NOT NULL,\n"
							+ "  comp_admin_email VARCHAR(45) NOT NULL\n" + "  );";
					stmt.execute(query);
					query = " CREATE TABLE Cars(\n" + "  car_reg_id INTEGER PRIMARY KEY NOT NULL,\n"
							+ "  car_rent_comp_id INTEGER NOT NULL,\n" + "  car_name VARCHAR(45) NOT NULL,\n"
							+ "  car_number VARCHAR(45) NOT NULL,\n" + "  car_capa INTEGER NOT NULL,\n"
							+ "  car_maker VARCHAR(45) NOT NULL,\n" + "  car_makeryear VARCHAR(45) NOT NULL,\n"
							+ "  car_rent_price INTEGER NOT NULL,\n" + "  car_reg_date DATE NOT NULL,\n"
							+ "  car_distance INTEGER,\n" + "  car_used_check INTEGER,\n"
							+ "  FOREIGN KEY (car_rent_comp_id) REFERENCES Car_Rent_Comp(car_rent_comp_id)\n" + " );";
					stmt.execute(query);
					query = "CREATE TABLE Cars_Rent(\n" + "  car_rent_id INTEGER PRIMARY KEY NOT NULL,\n"
							+ "  car_reg_id INTEGER  NOT NULL,\n" + "  cust_driver_license_id INTEGER NOT NULL,\n"
							+ "  car_rent_comp_id INTEGER NOT NULL,\n" + "  car_rent_start_date DATE NOT NULL,\n"
							+ "  car_rent_period INTEGER NOT NULL,\n" + "  charge_price INTEGER NOT NULL,\n"
							+ "  price_deadline DATE NOT NULL,\n" + "  add_amount_contents VARCHAR(45) NULL,\n"
							+ "  add_amount_price INTEGER NULL,\n"
							+ "	FOREIGN KEY (car_reg_id) REFERENCES Cars(car_reg_id),\n"
							+ "   FOREIGN KEY (car_rent_comp_id) REFERENCES Car_Rent_Comp(car_rent_comp_id),\n"
							+ "	FOREIGN KEY (cust_driver_license_id)REFERENCES Customers(cust_driver_license_id)\n"
							+ ");";
					stmt.execute(query);
					query = "CREATE TABLE  Car_Repair_Inform (\n" + "  repair_number INTEGER PRIMARY KEY NOT NULL,\n"
							+ "  car_reg_id INTEGER NOT NULL,\n" + "  car_garage_id INTEGER NOT NULL,\n"
							+ "  car_rent_comp_id INTEGER NOT NULL,\n" + "  cust_driver_license_id INTEGER NOT NULL,\n"
							+ "  repair_contents VARCHAR(45) NOT NULL,\n" + "  repair_date DATE NOT NULL,\n"
							+ "  repair_price INTEGER NOT NULL,\n" + "  repair_deadline DATE NOT NULL,\n"
							+ "  add_repair_contents VARCHAR(45) ,\n"
							+ "  FOREIGN KEY (car_garage_id) REFERENCES Car_Garage(car_garage_id),\n"
							+ "  FOREIGN KEY (car_reg_id) REFERENCES Cars(car_reg_id),\n"
							+ "  FOREIGN KEY (car_rent_comp_id) REFERENCES Car_Rent_Comp(car_rent_comp_id),\n"
							+ "  FOREIGN KEY (cust_driver_license_id) REFERENCES Customers(cust_driver_license_id)\n"
							+ "  );";
					stmt.execute(query);
					query = "  CREATE TABLE Return_Information (\n" + "  Information_id INTEGER NOT NULL,\n"
							+ "  car_rent_id INTEGER NOT NULL,\n" + "  car_reg_id INTEGER NOT NULL,\n"
							+ "  need_repair INTEGER NULL,\n" + "  car_coment_front VARCHAR(45),\n"
							+ "  car_coment_rear VARCHAR(45),\n" + "  car_coment_left VARCHAR(45),\n"
							+ "  car_coment_right VARCHAR(45),\n"
							+ "  FOREIGN KEY (car_rent_id) REFERENCES Cars_Rent(car_rent_id),\n"
							+ "  FOREIGN KEY (car_reg_id) REFERENCES Cars(car_reg_id)\n" + "  );";
					stmt.execute(query);
				} catch (SQLException e) {
					e.printStackTrace();
				}

				// 데이터 삽입
				insertdata();
			}
			// 입력, 삭제, 변경 버튼
			else if (ev.getSource() == minput) {
				marea.setVisible(false);

				// 캠핑카 대여회사 경우
				if (table.getSelectedIndex() == 0) {
					mdown.removeAll();
					mdown.add(cid);
					mdown.add(idt);
					mdown.add(cname);
					mdown.add(namet);
					mdown.add(cadd);
					mdown.add(caddt);
					mdown.add(cnumber);
					mdown.add(cnumbert);
					mdown.add(cmname);
					mdown.add(cmnamet);
					mdown.add(cmemail);
					mdown.add(cmemailt);
					mdown.add(inb);
					mdown.add(delb);
					mdown.add(updateb);
					mdown.updateUI();
				}
				// 캠핑카일 경우
				else if (table.getSelectedIndex() == 1) {
					mdown.removeAll();
					// 캠핑카 패널 삽입
					mdown.add(carid);
					mdown.add(caridt);
					mdown.add(carname);
					mdown.add(carnamet);
					mdown.add(carnumber);
					mdown.add(carnumbert);
					mdown.add(carenable);
					mdown.add(carenablet);
					mdown.add(carmaker);
					mdown.add(carmakert);
					mdown.add(carmakeyear);
					mdown.add(carmakeyeart);
					mdown.add(stackdis);
					mdown.add(stackdist);
					mdown.add(lentval);
					mdown.add(lentvalt);
					mdown.add(lentcID);
					mdown.add(lentcIDt);
					mdown.add(regidate);
					mdown.add(regidatet);
					mdown.add(inb);
					mdown.add(delb);
					mdown.add(updateb);
					mdown.updateUI();
				}
				// 고객일 경우
				else if (table.getSelectedIndex() == 2) {
					mdown.removeAll();
					mdown.add(licenid);
					mdown.add(licenidt);
					mdown.add(clname);
					mdown.add(clnamet);
					mdown.add(cladd);
					mdown.add(claddt);
					mdown.add(clnumber);
					mdown.add(clnumbert);
					mdown.add(clemail);
					mdown.add(clemailt);
					mdown.add(inb);
					mdown.add(delb);
					mdown.add(updateb);
					mdown.updateUI();
				}
				// 캠핑카 정비소 경우
				else if (table.getSelectedIndex() == 3) {
					mdown.removeAll();
					mdown.add(repaid);
					mdown.add(repaidt);
					mdown.add(repaname);
					mdown.add(repanamet);
					mdown.add(repaadd);
					mdown.add(repaaddt);
					mdown.add(repanumber);
					mdown.add(repanumbert);
					mdown.add(repamname);
					mdown.add(repamnamet);
					mdown.add(repamemail);
					mdown.add(repamemailt);
					mdown.add(inb);
					mdown.add(delb);
					mdown.add(updateb);
					mdown.updateUI();
				}
				// 정비정보 경우
				else if (table.getSelectedIndex() == 4) {
					mdown.removeAll();
					mdown.add(spnumber);
					mdown.add(spnumbert);
					mdown.add(carinfo);
					mdown.add(carinfot);
					mdown.add(repainfo);
					mdown.add(repainfot);
					mdown.add(cominfo);
					mdown.add(cominfot);
					mdown.add(liceninfo);
					mdown.add(liceninfot);
					mdown.add(repairinfo);
					mdown.add(repairinfot);
					mdown.add(repairdate);
					mdown.add(repairdatet);
					mdown.add(repairprice);
					mdown.add(repairpricet);
					mdown.add(paydate);
					mdown.add(paydatet);
					mdown.add(otherrepair);
					mdown.add(otherrepairt);
					mdown.add(inb);
					mdown.add(delb);
					mdown.add(updateb);
					mdown.updateUI();
				}
			}
			// 캠핑카 반환
			else if (ev.getSource() == mreturn) {
				mdown.removeAll();
				mdown.add(returncarid);
				mdown.add(returncaridt);
				mdown.add(returncar);
				mdown.updateUI();
			}
			// 검색1
			else if (ev.getSource() == search1) {
				mdown.removeAll();
				mdown.add(marea);
				marea.setVisible(true);
				marea.setText("");
				String sql = "select C.cust_name,R.car_reg_id, R.car_rent_start_date, R.charge_price\n"
						+ "from Customers C, Cars_Rent R \n"
						+ "where  C.cust_driver_license_id=R.cust_driver_license_id ;";
				try {
					stmt = con.createStatement();
					rs = stmt.executeQuery(sql);
					marea.append("고객이름-- 차량 ID-- 차량 대여일 -- 청구 가격  을 검색한다.\n(JOIN cust_driver_license_id,Cars)\n\n");
					marea.append("cust_name \tcar_reg_id \tcar_rent_start_date \tcharge_price\n");
					while (rs.next()) {
						String str = rs.getString(1) + "\t" + rs.getInt(2) + "\t" + rs.getString(3) + "\t"
								+ rs.getInt(4);
						marea.append(str);
						marea.append("\n");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				mdown.updateUI();
			}
			// 검색2
			else if (ev.getSource() == search2) {
				mdown.removeAll();
				mdown.add(marea);
				marea.setVisible(true);
				marea.setText("");
				String sql = "select C.car_reg_id, C.car_name, R.cust_driver_license_id, R.car_rent_start_date\n"
						+ "from Cars_Rent R ,Cars C \n" + "where  C.car_rent_comp_id=R.car_rent_comp_id ;";
				try {
					stmt = con.createStatement();
					rs = stmt.executeQuery(sql);
					marea.append("차량ID-- 차량 이름-- 사용자 면허 ID-- 사용자 렌트시작일 을 검색한다. \n (JOIN car_rent_comp_id,Cars)\n\n");
					marea.append("car_reg_id \tcar_name \tcust_driver_license_id \tcar_rent_start_date\n");
					while (rs.next()) {
						String str = rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getInt(3) + "\t"
								+ rs.getString(4);
						marea.append(str);
						marea.append("\n");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				mdown.updateUI();
			}
			// 검색3
			else if (ev.getSource() == search3) {
				mdown.removeAll();
				mdown.add(marea);
				marea.setVisible(true);
				marea.setText("");
				String sql = "select G.car_garage_id,C.car_reg_id, C.car_name, G.repair_contents\n"
						+ "from Car_Repair_Inform G, Cars C \n" + "where  G.car_reg_id=C.car_reg_id ;";
				try {
					stmt = con.createStatement();
					rs = stmt.executeQuery(sql);
					marea.append("정비소ID-- 차량ID-- 차량 이름-- 수리 내용 을 검색한다.\n (JOIN Car_Repair_Inform,Cars)\n\n");
					marea.append("car_garage_id \tcar_reg_id \tcar_name \trepair_contents\n");
					while (rs.next()) {
						String str = rs.getInt(1) + "\t" + rs.getInt(2) + "\t" + rs.getString(3) + "\t"
								+ rs.getString(4);
						marea.append(str);
						marea.append("\n");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				mdown.updateUI();
			}
			// 검색4
			else if (ev.getSource() == search4) {
				mdown.removeAll();
				mdown.add(marea);
				marea.setVisible(true);
				marea.setText("");
				String sql = "select G.car_garage_id,G.garage_phone,G.garage_admin_name,C.car_reg_id,C.repair_price\n"
						+ "from Car_Garage G, Car_Repair_Inform  C \n" + "where G.car_garage_id=C.car_garage_id ;";
				try {
					stmt = con.createStatement();
					rs = stmt.executeQuery(sql);
					marea.append(
							"정비소ID-- 정비소 전화번호-- 정비소 관리자-- 차량등록ID--차량수리비   를 검색한다.\n (JOIN Car_Garage,Car_Repair_Inform)\n\n ");
					marea.append("car_garage_id \tgarage_phone \tgarage_admin_name \tcar_reg_id \trepair_price\n");
					while (rs.next()) {
						String str = rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t"
								+ rs.getInt(4) + "\t" + rs.getInt(5);
						marea.append(str);
						marea.append("\n");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				mdown.updateUI();
			}
			// 사용자 검색
			else if (ev.getSource() == csearch) {
				uarea.setVisible(true);
				uarea.setText("");
				String sql = "select car_reg_id,car_rent_comp_id,car_name,car_capa,car_rent_price,car_used_check from cars order by car_rent_price";

				try {
					stmt = con.createStatement();
					rs = stmt.executeQuery(sql);
					uarea.append("사용자가\n차량등록ID--회사ID--차량이름-- 수용인원-- 가격--사용가능여부(1불가능 0 가능) 을 검색한다.\n\n ");
					uarea.append(
							"reg_id         rent_comp_id           name              capa                rent_price     car_used_check \n");
					while (rs.next()) {
						String str = rs.getInt(1) + "\t" + rs.getInt(2) + "\t" + rs.getString(3) + "\t" + rs.getInt(4)
								+ "\t" + rs.getInt(5) + "\t" + rs.getInt(6);
						uarea.append(str);
						uarea.append("\n");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			// 사용자 대여
			else if (ev.getSource() == lentb) {
				new newWindow();
			}
			// 관리자 삽입
			else if (ev.getSource() == inb) {
				// 캠핑카 대여회사 테이블 삽입
				if (table.getSelectedIndex() == 0) {
					int id;
					String name, add, phone, mname, memail;
					try {
						id = Integer.parseInt(idt.getText());
						name = namet.getText();
						add = caddt.getText();
						phone = cnumbert.getText();
						mname = cmnamet.getText();
						memail = cmemailt.getText();

						String query = "INSERT INTO Car_Rent_Comp (Car_Rent_Comp.car_rent_comp_id, Car_Rent_Comp.comp_name, Car_Rent_Comp.comp_addr, Car_Rent_Comp.comp_phone,"
								+ " Car_Rent_Comp.comp_admin_name, Car_Rent_Comp.comp_admin_email) VALUES(?,?,?,?,?,?);";
						try {
							pstmt = con.prepareStatement(query);
							pstmt.setInt(1, id);
							pstmt.setString(2, name);
							pstmt.setString(3, add);
							pstmt.setString(4, phone);
							pstmt.setString(5, mname);
							pstmt.setString(6, memail);

							r = pstmt.executeUpdate();
							JOptionPane.showMessageDialog(null, "입력 성공");

						} catch (SQLException e) {
							e.printStackTrace();
						}
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "입력이 잘못 되었습니다", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}
				// 캠핑카 테이블 삽입
				else if (table.getSelectedIndex() == 1) {
					int carid, comid, capa, price, dis, used = 0;
					String name, number, maker, makeryear, date;
					try {
						carid = Integer.parseInt(caridt.getText());
						comid = Integer.parseInt(lentcIDt.getText());
						name = carnamet.getText();
						number = carnumbert.getText();
						capa = Integer.parseInt(carenablet.getText());
						maker = carmakert.getText();
						makeryear = carmakeyeart.getText();
						price = Integer.parseInt(lentvalt.getText());
						date = regidatet.getText();
						dis = Integer.parseInt(stackdist.getText());

						String query = "INSERT INTO Cars (Cars.car_reg_id, Cars.car_rent_comp_id, Cars.car_name, Cars.car_number, Cars.car_capa, "
								+ "Cars.car_maker, Cars.car_makeryear, Cars.car_rent_price, Cars.car_reg_date, Cars.car_distance, Cars.car_used_check) VALUES(?,?,?,?,?,?,?,?,?,?,?);";
						try {
							pstmt = con.prepareStatement(query);
							pstmt.setInt(1, carid);
							pstmt.setInt(2, comid);
							pstmt.setString(3, name);
							pstmt.setString(4, number);
							pstmt.setInt(5, capa);
							pstmt.setString(6, maker);
							pstmt.setString(7, makeryear);
							pstmt.setInt(8, price);
							pstmt.setString(9, date);
							pstmt.setInt(10, dis);
							pstmt.setInt(11, used);

							r = pstmt.executeUpdate();
							JOptionPane.showMessageDialog(null, "입력 성공");

						} catch (SQLException e) {
							e.printStackTrace();
						}
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "입력이 잘못 되었습니다", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}
				// 고객일 경우 삽입
				else if (table.getSelectedIndex() == 2) {
					int liid;
					String name, add, number, email;
					try {
						liid = Integer.parseInt(licenidt.getText());
						name = clnamet.getText();
						add = claddt.getText();
						number = clnumbert.getText();
						email = clemailt.getText();
						String query = "INSERT INTO Customers (Customers.cust_driver_license_id, Customers.cust_name, Customers.cust_addr, "
								+ "Customers.cust_phone, Customers.cust_email) VALUES(?,?,?,?,?);";
						try {
							pstmt = con.prepareStatement(query);
							pstmt.setInt(1, liid);
							pstmt.setString(2, name);
							pstmt.setString(3, add);
							pstmt.setString(4, number);
							pstmt.setString(5, email);

							r = pstmt.executeUpdate();
							JOptionPane.showMessageDialog(null, "입력 성공");

						} catch (SQLException e) {
							e.printStackTrace();
						}
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "입력이 잘못 되었습니다", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}
				// 캠핑카 정비소 일경우 삽입
				else if (table.getSelectedIndex() == 3) {
					int repairid;
					String name, add, number, adname, ademail;
					try {
						repairid = Integer.parseInt(repaidt.getText());
						name = repanamet.getText();
						add = repaaddt.getText();
						number = repanumbert.getText();
						adname = repamnamet.getText();
						ademail = repamemailt.getText();
						String query = "INSERT INTO Car_Garage (Car_Garage.car_garage_id, Car_Garage.garage_name, Car_Garage.garage_addr, "
								+ "Car_Garage.garage_phone, Car_Garage.garage_admin_name, Car_Garage.garage_admin_email) VALUES(?,?,?,?,?,?);";
						try {
							pstmt = con.prepareStatement(query);
							pstmt.setInt(1, repairid);
							pstmt.setString(2, name);
							pstmt.setString(3, add);
							pstmt.setString(4, number);
							pstmt.setString(5, adname);
							pstmt.setString(6, ademail);

							r = pstmt.executeUpdate();
							JOptionPane.showMessageDialog(null, "입력 성공");

						} catch (SQLException e) {
							e.printStackTrace();
						}
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "입력이 잘못 되었습니다", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}
				// 수리가 필요할 경우 정비정보 삽입
				else if (table.getSelectedIndex() == 4) {
					int check_comp = -1;
					int repairinfoid, carregiid, carrepairid, carlentcomid, custlicenid, repairpriceinfo;
					String repairabout, repairdateinfo, paydateinfo, otherrepairinfo;
					try {
						repairinfoid = Integer.parseInt(spnumbert.getText());
						carregiid = Integer.parseInt(carinfot.getText());
						carrepairid = Integer.parseInt(repainfot.getText());
						carlentcomid = Integer.parseInt(cominfot.getText());
						custlicenid = Integer.parseInt(liceninfot.getText());
						repairabout = repairinfot.getText();
						repairdateinfo = repairdatet.getText();
						repairpriceinfo = Integer.parseInt(repairpricet.getText());
						paydateinfo = paydatet.getText();
						otherrepairinfo = otherrepairt.getText();
						String query = "INSERT INTO Car_Repair_Inform (Car_Repair_Inform.repair_number, Car_Repair_Inform.car_reg_id, Car_Repair_Inform.car_garage_id, "
								+ "Car_Repair_Inform.car_rent_comp_id, Car_Repair_Inform.cust_driver_license_id, Car_Repair_Inform.repair_contents, "
								+ "Car_Repair_Inform.repair_date, Car_Repair_Inform.repair_price, Car_Repair_Inform.repair_deadline, Car_Repair_Inform.add_repair_contents) VALUES(?,?,?,?,?,?,?,?,?,?);";
						try {
							String sql = "select car_reg_id from Return_Information where need_repair = 1 and car_reg_id = "
									+ carinfot.getText() + ";";
							stmt = con.createStatement();

							String check_comp_q = "select car_rent_comp_id from Cars where car_reg_id =" + carregiid
									+ ";";
							rs = stmt.executeQuery(check_comp_q);
							if (rs.next()) {
								check_comp = rs.getInt(1);
							}

							rs = stmt.executeQuery(sql);
							// 수리 필요여부 판단
							int val = 0;
							while (rs.next()) {
								val = rs.getInt(1);
							}

							if ((val == carregiid) && (check_comp == carlentcomid)) {
								pstmt = con.prepareStatement(query);
								pstmt.setInt(1, repairinfoid);
								pstmt.setInt(2, carregiid);
								pstmt.setInt(3, carrepairid);
								pstmt.setInt(4, carlentcomid);
								pstmt.setInt(5, custlicenid);
								pstmt.setString(6, repairabout);
								pstmt.setString(7, repairdateinfo);
								pstmt.setInt(8, repairpriceinfo);
								pstmt.setString(9, paydateinfo);
								pstmt.setString(10, otherrepairinfo);

								r = pstmt.executeUpdate();
								JOptionPane.showMessageDialog(null, "입력 성공");
							} else
								JOptionPane.showMessageDialog(null, "해당 캠핑카가 수리가 필요없거나 회사ID를 확인 하여 주시기 바랍니다.", "ERROR",
										JOptionPane.ERROR_MESSAGE);

						} catch (SQLException e) {
							e.printStackTrace();
						}
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "입력이 잘못 되었습니다", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			// 관리자 삭제 버튼
			else if (ev.getSource() == delb) {
				// 대여회사의 경우 삭제
				if (table.getSelectedIndex() == 0) {
					String query = "delete from Car_Rent_Comp where car_rent_comp_id = '" + idt.getText() + "';";
					try {
						stmt = con.createStatement();
						r = stmt.executeUpdate(query);
						if (r == 1)
							JOptionPane.showMessageDialog(null, "삭제 완료");
						else
							JOptionPane.showMessageDialog(null, "외래키 참조 중 또는 값을 확인 바랍니다", "ERROR",
									JOptionPane.ERROR_MESSAGE);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				// 캠핑카의 경우 삭제
				else if (table.getSelectedIndex() == 1) {
					String query = "delete from Cars where car_reg_id = '" + caridt.getText() + "';";
					try {
						stmt = con.createStatement();
						r = stmt.executeUpdate(query);
						if (r == 1)
							JOptionPane.showMessageDialog(null, "삭제 완료");
						else
							JOptionPane.showMessageDialog(null, "외래키 참조 중 또는 값을 확인 바랍니다", "ERROR",
									JOptionPane.ERROR_MESSAGE);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				// 고객의 경우 삭제
				else if (table.getSelectedIndex() == 2) {
					String query = "delete from Customers where cust_driver_license_id = '" + licenidt.getText() + "';";
					try {
						stmt = con.createStatement();
						r = stmt.executeUpdate(query);
						if (r == 1)
							JOptionPane.showMessageDialog(null, "삭제 완료");
						else
							JOptionPane.showMessageDialog(null, "외래키 참조 중 또는 값을 확인 바랍니다", "ERROR",
									JOptionPane.ERROR_MESSAGE);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				// 정비소의 경우 삭제
				else if (table.getSelectedIndex() == 3) {
					String query = "delete from Car_Garage where car_garage_id = '" + repaidt.getText() + "';";
					try {
						stmt = con.createStatement();
						r = stmt.executeUpdate(query);
						if (r == 1)
							JOptionPane.showMessageDialog(null, "삭제 완료");
						else
							JOptionPane.showMessageDialog(null, "외래키 참조 중 또는 값을 확인 바랍니다", "ERROR",
									JOptionPane.ERROR_MESSAGE);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				// 정비정보의 경우 삭제
				else if (table.getSelectedIndex() == 4) {
					String query = "delete from Car_Repair_Inform where repair_number = " + spnumbert.getText() + ";";
					try {
						stmt = con.createStatement();
						r = stmt.executeUpdate(query);
						if (r == 1)
							JOptionPane.showMessageDialog(null, "삭제 완료");
						else
							JOptionPane.showMessageDialog(null, "외래키 참조 중 또는 값을 확인 바랍니다", "ERROR",
									JOptionPane.ERROR_MESSAGE);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
			// 관리자 변경 버튼
			else if (ev.getSource() == updateb) {
				if (table.getSelectedIndex() == 0) {
					String name, add, phone, mname, memail;
					try {
						name = namet.getText();
						add = caddt.getText();
						phone = cnumbert.getText();
						mname = cmnamet.getText();
						memail = cmemailt.getText();

						String query = "UPDATE Car_Rent_Comp set comp_name = ?, comp_addr = ?, comp_phone = ?,"
								+ "comp_admin_name = ?, comp_admin_email = ? where car_rent_comp_id = " + idt.getText()
								+ ";";
						try {
							pstmt = con.prepareStatement(query);
							pstmt.setString(1, name);
							pstmt.setString(2, add);
							pstmt.setString(3, phone);
							pstmt.setString(4, mname);
							pstmt.setString(5, memail);

							r = pstmt.executeUpdate();
							JOptionPane.showMessageDialog(null, "변경 성공");

						} catch (SQLException e) {
							e.printStackTrace();
						}
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "입력이 잘못 되었습니다", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
				} else if (table.getSelectedIndex() == 1) {
					int capa, price, dis, comid;
					String name, number, maker, makeryear, date;
					try {
						comid = Integer.parseInt(lentcIDt.getText());
						name = carnamet.getText();
						number = carnumbert.getText();
						capa = Integer.parseInt(carenablet.getText());
						maker = carmakert.getText();
						makeryear = carmakeyeart.getText();
						price = Integer.parseInt(lentvalt.getText());
						date = regidatet.getText();
						dis = Integer.parseInt(stackdist.getText());

						String query = "UPDATE Cars set car_rent_comp_id = ? ,car_name = ?, car_number = ?, car_capa = ?, "
								+ "car_maker = ?, car_makeryear = ?, car_rent_price = ?, car_reg_date = ?, car_distance = ? where car_reg_id = "
								+ caridt.getText() + ";";
						try {
							pstmt = con.prepareStatement(query);
							pstmt.setInt(1, comid);
							pstmt.setString(2, name);
							pstmt.setString(3, number);
							pstmt.setInt(4, capa);
							pstmt.setString(5, maker);
							pstmt.setString(6, makeryear);
							pstmt.setInt(7, price);
							pstmt.setString(8, date);
							pstmt.setInt(9, dis);

							r = pstmt.executeUpdate();
							JOptionPane.showMessageDialog(null, "변경 성공");

						} catch (SQLException e) {
							e.printStackTrace();
						}
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "입력이 잘못 되었습니다", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
				} else if (table.getSelectedIndex() == 2) {
					String name, add, number, email;
					try {
						name = clnamet.getText();
						add = claddt.getText();
						number = clnumbert.getText();
						email = clemailt.getText();
						String query = "UPDATE Customers set cust_name = ?, cust_addr = ?, "
								+ "cust_phone = ?, cust_email = ? where cust_driver_license_id = " + licenidt.getText()
								+ ";";
						try {
							pstmt = con.prepareStatement(query);
							pstmt.setString(1, name);
							pstmt.setString(2, add);
							pstmt.setString(3, number);
							pstmt.setString(4, email);

							r = pstmt.executeUpdate();
							JOptionPane.showMessageDialog(null, "변경 성공");

						} catch (SQLException e) {
							e.printStackTrace();
						}
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "입력이 잘못 되었습니다", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
				} else if (table.getSelectedIndex() == 3) {
					String name, add, number, adname, ademail;
					try {
						name = repanamet.getText();
						add = repaaddt.getText();
						number = repanumbert.getText();
						adname = repamnamet.getText();
						ademail = repamemailt.getText();
						String query = "UPDATE Car_Garage set garage_name = ?, garage_addr = ?, "
								+ "garage_phone = ?, garage_admin_name = ?, garage_admin_email = ? where car_garage_id = "
								+ repaidt.getText() + ";";
						try {
							pstmt = con.prepareStatement(query);
							pstmt.setString(1, name);
							pstmt.setString(2, add);
							pstmt.setString(3, number);
							pstmt.setString(4, adname);
							pstmt.setString(5, ademail);

							r = pstmt.executeUpdate();
							JOptionPane.showMessageDialog(null, "변경 성공");

						} catch (SQLException e) {
							e.printStackTrace();
						}
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "입력이 잘못 되었습니다", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}

				else if (table.getSelectedIndex() == 4) {
					int check_comp = -1;
					int carregiid, carrepairid, carlentcomid, custlicenid, repairpriceinfo;
					String repairabout, repairdateinfo, paydateinfo, otherrepairinfo;
					try {
						carregiid = Integer.parseInt(carinfot.getText());
						carrepairid = Integer.parseInt(repainfot.getText());
						carlentcomid = Integer.parseInt(cominfot.getText());
						custlicenid = Integer.parseInt(liceninfot.getText());
						repairabout = repairinfot.getText();
						repairdateinfo = repairdatet.getText();
						repairpriceinfo = Integer.parseInt(repairpricet.getText());
						paydateinfo = paydatet.getText();
						otherrepairinfo = otherrepairt.getText();
						String query = "UPDATE Car_Repair_Inform set car_reg_id = ?, car_garage_id = ?, "
								+ "car_rent_comp_id = ?, cust_driver_license_id = ?, repair_contents = ?, "
								+ "repair_date = ?, repair_price = ?, repair_deadline = ?, add_repair_contents = ? where repair_number = "
								+ spnumbert.getText() + ";";
						try {
							String sql = "select car_reg_id from Return_Information where need_repair = 1 and car_reg_id = "
									+ carinfot.getText() + ";";
							stmt = con.createStatement();

							String check_comp_q = "select car_rent_comp_id from Cars where car_reg_id =" + carregiid
									+ ";";
							rs = stmt.executeQuery(check_comp_q);
							if (rs.next()) {
								check_comp = rs.getInt(1);
							}

							rs = stmt.executeQuery(sql);
							// 수리 필요여부 판단
							int val = 0;
							while (rs.next()) {
								val = rs.getInt(1);
							}
							if ((val == carregiid) && (check_comp == carlentcomid)) {
								pstmt = con.prepareStatement(query);
								pstmt.setInt(1, carregiid);
								pstmt.setInt(2, carrepairid);
								pstmt.setInt(3, carlentcomid);
								pstmt.setInt(4, custlicenid);
								pstmt.setString(5, repairabout);
								pstmt.setString(6, repairdateinfo);
								pstmt.setInt(7, repairpriceinfo);
								pstmt.setString(8, paydateinfo);
								pstmt.setString(9, otherrepairinfo);

								r = pstmt.executeUpdate();
								JOptionPane.showMessageDialog(null, "변경 성공");
							} else
								JOptionPane.showMessageDialog(null, "해당 캠핑카가 수리가 필요없거나 회사ID를 확인 하여 주시기 바랍니다.", "ERROR",
										JOptionPane.ERROR_MESSAGE);

						} catch (SQLException e) {
							e.printStackTrace();
						}
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "입력이 잘못 되었습니다", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			// 캠핑카 반환버튼 클릭시
			else if (ev.getSource() == returncar) {
				try {
					String sql = "select car_used_check from Cars where car_reg_id = " + returncaridt.getText() + ";";
					int val = 0;
					stmt = con.createStatement();
					rs = stmt.executeQuery(sql);
					while (rs.next()) {
						val = rs.getInt(1);
					}
					// 캠핑카가 대여중이면
					if (val == 1) {
						String query = "UPDATE Cars set car_used_check = 0 where car_reg_id = " + returncaridt.getText()
								+ ";";
						pstmt = con.prepareStatement(query);
						r = pstmt.executeUpdate();
						JOptionPane.showMessageDialog(null, "반환 성공");
					} else
						JOptionPane.showMessageDialog(null, "캠핑카가 대여중이 아닙니다", "ERROR", JOptionPane.ERROR_MESSAGE);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	// DB연결
	public void connect() {
		try {
			Class.forName(driver);
			System.out.println("드라이버 로드 성공");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로드 실패");
		}
		try {
			con = DriverManager.getConnection(url, user, pw);
			System.out.println("데이터베이스 연결 성공");
		} catch (SQLException e) {
			System.out.println("데이터베이스 연결 실패");
		}
	}

	public void insertdata() {
		String sql = "INSERT INTO CUSTOMERS(cust_driver_license_id, cust_name, cust_addr,cust_phone ,cust_email) VALUES"
				+ "(1, '재연', 'korea seoul', '010-001-0001','01@navercom'),"
				+ "(2, '지훈', 'korea gwangju', '010-001-0002','02@navercom'),"
				+ "(3, '재진', 'korea mokpo', '010-001-0003','03@navercom'),"
				+ "(4, '성호', 'korea seoul', '010-001-0004','04@navercom'),"
				+ "(5, '재형', 'korea gwangju', '010-001-0005','05@navercom'),"
				+ "(6, '시리', 'korea mokpo', '010-001-0006','06@navercom'),"
				+ "(7, '된장', 'korea Pocheon', '010-001-0007','07@navercom'),"
				+ "(8, '승훈', 'korea Gyeongju', '010-001-0008','08@navercom'),"
				+ "(9, '지홍', 'korea seoul', '010-001-0009','09@navercom'),"
				+ "(10, '성렬', 'korea Gyeongju', '010-001-0010','10@navercom'),"
				+ "(11, '준서', 'korea seoul', '010-001-0010','11@navercom'),"
				+ "(12, '영애', 'korea seoul', '010-001-0010','12@navercom'),"
				+ "(13, '제임스', 'korea Busan', '010-001-0010','13@navercom'),"
				+ "(14, '어빙', 'korea seoul', '010-001-0010','14@navercom'),"
				+ "(15, '창렬', 'korea Busan', '010-001-0010','15@navercom');";
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		sql = "INSERT INTO Car_Rent_Comp(car_rent_comp_id, comp_name, comp_addr, comp_phone, comp_admin_name, comp_admin_email) VALUES"
				+ "(1,'AveryCar','korea seoul','010-002-0101','Avery','01@gmail.com'),"
				+ "(2,'AdelaCar','korea seoul','010-002-0102','Adela','02@gmail.com'),"
				+ "(3,'AdaleeCar','korea seoul','010-002-0103','Adalee','03@gmail.com'),"
				+ "(4,'BibianeCar','korea seoul','010-002-0104','Bibiane','04@gmail.com'),"
				+ "(5,'BiancaCar','korea seoul','010-002-0105','Bianca','05@gmail.com'),"
				+ "(6,'BonitaCar','korea seoul','010-002-0106','Bonita','06@gmail.com'),"
				+ "(7,'Bonocar','korea seoul','010-002-0107','Bono','07@gmail.com'),"
				+ "(8,'BelitaCar','korea seoul','010-002-0108','Belita','08@gmail.com'),"
				+ "(9,'CharlotteCar','korea seoul','010-002-0109','Charlotte','09@gmail.com'),"
				+ "(10,'CynthiaCar','korea seoul','010-002-0110','Cynthia','10@gmail.com'),"
				+ "(11,'DanaCar','korea seoul','010-002-0111','Dana','11@gmail.com'),"
				+ "(12,'DiorCar','korea seoul','010-002-0112','Dior','12@gmail.com'),"
				+ "(13,'DorisCar','korea seoul','010-002-0113','Doris','13@gmail.com'),"
				+ "(14,'EdithCar','korea seoul','010-002-0114','Edith','14@gmail.com'),"
				+ "(15,'asdCar','korea seoul','010-002-0100','asd','15@gmail.com');";
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		sql = "INSERT INTO Car_Garage(car_garage_id, garage_name, garage_addr, garage_phone, garage_admin_name, garage_admin_email) VALUES"
				+ "(1,'1garage','korea seoul','0101-111-0001','한솔','01@yahoo.com'),"
				+ "(2,'2garage','korea seoul','0101-111-0002','가람','02@yahoo.com'),"
				+ "(3,'3garage','korea seoul','0101-111-0003','찬식','03@yahoo.com'),"
				+ "(4,'4garage','korea seoul','0101-111-0004','율무','04@yahoo.com'),"
				+ "(5,'5garage','korea seoul','0101-111-0005','한빛','05@yahoo.com'),"
				+ "(6,'6garage','korea seoul','0101-111-0006','벅스','06@yahoo.com'),"
				+ "(7,'7garage','korea seoul','0101-111-0007','멜론','07@yahoo.com'),"
				+ "(8,'8garage','korea seoul','0101-111-0008','나미','08@yahoo.com'),"
				+ "(9,'9garage','korea seoul','0101-111-0009','르브','09@yahoo.com'),"
				+ "(10,'10garage','korea seoul','0101-111-0010','론제','10@yahoo.com'),"
				+ "(11,'11garage','korea seoul','0101-111-0011','임스','11@yahoo.com'),"
				+ "(12,'12garage','korea seoul','0101-111-0012','어빙','12@yahoo.com'),"
				+ "(13,'13garage','korea seoul','0101-111-0013','에이셉','13@yahoo.com'),"
				+ "(14,'14garage','korea seoul','0101-111-0014','라키','14@yahoo.com'),"
				+ "(15,'15garage','korea seoul','0101-111-0015','트레비','15@yahoo.com');";
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		sql = "INSERT INTO Cars(car_reg_id, car_rent_comp_id, car_name, car_number, car_capa, car_maker, car_makeryear,car_rent_price,car_reg_date,car_distance,car_used_check) VALUES"
				+ "(1,1,'sonata','1234',4,'APPLE',STR_TO_DATE('2004-07-10','%Y-%m-%d'),2000,STR_TO_DATE('2014-07-10','%Y-%m-%d'),10,0),"
				+ "(2,2,'carnival','1111',8,'APPLE',STR_TO_DATE('2004-07-10','%Y-%m-%d'),1000,STR_TO_DATE('2014-07-10','%Y-%m-%d'),10,0),"
				+ "(3,3,'e300','1222',4,'APPLE',STR_TO_DATE('2004-07-10','%Y-%m-%d'), 3000,STR_TO_DATE('2014-07-10','%Y-%m-%d'),10,0),"
				+ "(4,4,'sonata','1233',4,'APPLE',STR_TO_DATE('2004-07-10','%Y-%m-%d'),4000,STR_TO_DATE('2014-07-10','%Y-%m-%d'),10,0),"
				+ "(5,5,'carnival','1432',8,'APPLE',STR_TO_DATE('2004-07-10','%Y-%m-%d'),5000,STR_TO_DATE('2014-07-10','%Y-%m-%d'),10,0),"
				+ "(6,6,'sonata','9999',4,'APPLE',STR_TO_DATE('2004-07-10','%Y-%m-%d'),6000,STR_TO_DATE('2014-07-10','%Y-%m-%d'),10,0),"
				+ "(7,7,'e300','2222',4,'APPLE',STR_TO_DATE('2004-07-10','%Y-%m-%d'),1000,STR_TO_DATE('2014-07-10','%Y-%m-%d'),10,0),"
				+ "(8,8,'sonata','3333',4,'APPLE',STR_TO_DATE('2004-07-10','%Y-%m-%d'),2000,STR_TO_DATE('2014-07-10','%Y-%m-%d'),10,1),"
				+ "(9,9,'sonata','4444',2,'APPLE',STR_TO_DATE('2004-07-10','%Y-%m-%d'),3000,STR_TO_DATE('2014-07-10','%Y-%m-%d'),10,1),"
				+ "(10,10,'sonata','0000',4,'APPLE',STR_TO_DATE('2004-07-10','%Y-%m-%d'),4000,STR_TO_DATE('2014-07-10','%Y-%m-%d'),10,1),"
				+ "(11,11,'sonata','9209',4,'APPLE',STR_TO_DATE('2004-07-10','%Y-%m-%d'),5000,STR_TO_DATE('2014-07-10','%Y-%m-%d'),10,1),"
				+ "(12,12,'e300','6165',4,'APPLE',STR_TO_DATE('2004-07-10','%Y-%m-%d'),6000,STR_TO_DATE('2014-07-10','%Y-%m-%d'),10,1),"
				+ "(13,13,'sonata','0115',2,'APPLE',STR_TO_DATE('2004-07-10','%Y-%m-%d'),7000,STR_TO_DATE('2014-07-10','%Y-%m-%d'),10,1),"
				+ "(14,14,'e300','6711',4,'APPLE',STR_TO_DATE('2004-07-10','%Y-%m-%d'),8000,STR_TO_DATE('2014-07-10','%Y-%m-%d'),10,1),"
				+ "(15,15,'sonata','8294',4,'APPLE',STR_TO_DATE('2004-07-10','%Y-%m-%d'),9000,STR_TO_DATE('2014-07-10','%Y-%m-%d'),10,1);";
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		sql = "INSERT INTO Cars_Rent(car_rent_id, car_reg_id, cust_driver_license_id, car_rent_comp_id, car_rent_start_date, car_rent_period,charge_price,price_deadline,add_amount_contents,add_amount_price) VALUES"
				+ "(1,1,1,1,STR_TO_DATE('2014-08-10','%Y-%m-%d'),7,14000,STR_TO_DATE('2014-08-17','%Y-%m-%d'),1000,1000),"
				+ "(2,2,2,2,STR_TO_DATE('2014-08-10','%Y-%m-%d'),7,7000,STR_TO_DATE('2014-08-17','%Y-%m-%d'),1000,1000),"
				+ "(3,3,3,3,STR_TO_DATE('2014-08-10','%Y-%m-%d'),7,21000,STR_TO_DATE('2014-08-17','%Y-%m-%d'),1000,1000),"
				+ "(4,4,4,4,STR_TO_DATE('2014-08-10','%Y-%m-%d'),7,28000,STR_TO_DATE('2014-08-17','%Y-%m-%d'),1000,1000),"
				+ "(5,5,5,5,STR_TO_DATE('2014-08-10','%Y-%m-%d'),7,35000,STR_TO_DATE('2014-08-17','%Y-%m-%d'),1000,1000),"
				+ "(6,6,6,7,STR_TO_DATE('2014-08-10','%Y-%m-%d'),7,42000,STR_TO_DATE('2014-08-17','%Y-%m-%d'),1000,1000),"
				+ "(7,7,7,7,STR_TO_DATE('2014-08-10','%Y-%m-%d'),7,7000,STR_TO_DATE('2014-08-17','%Y-%m-%d'),1000,1000),"
				+ "(8,8,8,8,STR_TO_DATE('2014-08-10','%Y-%m-%d'),7,14000,STR_TO_DATE('2014-08-17','%Y-%m-%d'),1000,1000),"
				+ "(9,9,9,9,STR_TO_DATE('2014-08-10','%Y-%m-%d'),7,21000,STR_TO_DATE('2014-08-17','%Y-%m-%d'),1000,1000),"
				+ "(10,10,10,10,STR_TO_DATE('2014-08-10','%Y-%m-%d'),7,28000,STR_TO_DATE('2014-08-17','%Y-%m-%d'),1000,1000),"
				+ "(11,11,11,11,STR_TO_DATE('2014-08-10','%Y-%m-%d'),7,35000,STR_TO_DATE('2014-08-17','%Y-%m-%d'),1000,1000),"
				+ "(12,12,12,12,STR_TO_DATE('2014-08-10','%Y-%m-%d'),7,42000,STR_TO_DATE('2014-08-17','%Y-%m-%d'),1000,1000),"
				+ "(13,13,13,13,STR_TO_DATE('2014-08-10','%Y-%m-%d'),7,49000,STR_TO_DATE('2014-08-17','%Y-%m-%d'),1000,1000),"
				+ "(14,14,14,14,STR_TO_DATE('2014-08-10','%Y-%m-%d'),7,56000,STR_TO_DATE('2014-08-17','%Y-%m-%d'),1000,1000),"
				+ "(15,15,15,15,STR_TO_DATE('2014-08-10','%Y-%m-%d'),7,63000,STR_TO_DATE('2014-08-17','%Y-%m-%d'),1000,1000);";
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		sql = "INSERT INTO Car_Repair_Inform(repair_number, car_reg_id, car_garage_id, car_rent_comp_id, cust_driver_license_id, repair_contents,repair_date,repair_price,repair_deadline,add_repair_contents) VALUES"
				+ "(1,1,1,1,1,'oil change',STR_TO_DATE('2014-08-18','%Y-%m-%d'),5000,STR_TO_DATE('2014-08-10','%Y-%m-%d'),'wash'),"
				+ "(2,2,2,2,2,'oil change',STR_TO_DATE('2014-08-18','%Y-%m-%d'),5000,STR_TO_DATE('2014-08-10','%Y-%m-%d'),'wash'),"
				+ "(3,3,3,3,3,'oil change',STR_TO_DATE('2014-08-18','%Y-%m-%d'),5000,STR_TO_DATE('2014-08-10','%Y-%m-%d'),'wash'),"
				+ "(4,4,4,4,4,'oil change',STR_TO_DATE('2014-08-18','%Y-%m-%d'),5000,STR_TO_DATE('2014-08-10','%Y-%m-%d'),'wash'),"
				+ "(5,5,5,5,5,'oil change',STR_TO_DATE('2014-08-18','%Y-%m-%d'),5000,STR_TO_DATE('2014-08-10','%Y-%m-%d'),'wash'),"
				+ "(6,6,6,6,6,'oil change',STR_TO_DATE('2014-08-18','%Y-%m-%d'),5000,STR_TO_DATE('2014-08-10','%Y-%m-%d'),'wash'),"
				+ "(7,7,7,7,7,'oil change',STR_TO_DATE('2014-08-18','%Y-%m-%d'),5000,STR_TO_DATE('2014-08-10','%Y-%m-%d'),'wash'),"
				+ "(8,8,8,8,8,'oil change',STR_TO_DATE('2014-08-18','%Y-%m-%d'),5000,STR_TO_DATE('2014-08-10','%Y-%m-%d'),'wash'),"
				+ "(9,9,9,9,9,'oil change',STR_TO_DATE('2014-08-18','%Y-%m-%d'),5000,STR_TO_DATE('2014-08-10','%Y-%m-%d'),'wash'),"
				+ "(10,10,10,10,10,'oil change',STR_TO_DATE('2014-08-18','%Y-%m-%d'),5000,STR_TO_DATE('2014-08-10','%Y-%m-%d'),'wash'),"
				+ "(11,11,11,11,11,'oil change',STR_TO_DATE('2014-08-18','%Y-%m-%d'),5000,STR_TO_DATE('2014-08-10','%Y-%m-%d'),'wash'),"
				+ "(12,12,12,12,12,'oil change',STR_TO_DATE('2014-08-18','%Y-%m-%d'),5000,STR_TO_DATE('2014-08-10','%Y-%m-%d'),'wash'),"
				+ "(13,13,13,13,13,'oil change',STR_TO_DATE('2014-08-18','%Y-%m-%d'),5000,STR_TO_DATE('2014-08-10','%Y-%m-%d'),'wash'),"
				+ "(14,14,14,14,14,'oil change',STR_TO_DATE('2014-08-18','%Y-%m-%d'),5000,STR_TO_DATE('2014-08-10','%Y-%m-%d'),'wash'),"
				+ "(15,15,15,15,15,'oil change',STR_TO_DATE('2014-08-18','%Y-%m-%d'),5000,STR_TO_DATE('2014-08-10','%Y-%m-%d'),'wash');";
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		sql = "INSERT INTO Return_Information(Information_id, car_rent_id, car_reg_id, need_repair, car_coment_front, car_coment_rear,car_coment_left,car_coment_right) VALUES"
				+ "(1,1,1,0,'normal','normal','normal','normal')," + "(2,2,2,0,'normal','normal','normal','normal'),"
				+ "(3,3,3,0,'normal','normal','normal','normal')," + "(4,4,4,0,'normal','normal','normal','normal'),"
				+ "(5,5,5,0,'normal','normal','normal','normal')," + "(6,6,6,0,'normal','normal','normal','normal'),"
				+ "(7,7,7,0,'normal','normal','normal','normal')," + "(8,8,8,1,'normal','normal','normal','normal'),"
				+ "(9,9,9,1,'normal','normal','normal','normal')," + "(10,10,10,1,'normal','normal','normal','normal'),"
				+ "(11,11,11,1,'normal','normal','normal','normal'),"
				+ "(12,12,12,1,'normal','normal','normal','normal'),"
				+ "(13,13,13,1,'normal','normal','normal','normal'),"
				+ "(14,14,14,1,'normal','normal','normal','normal'),"
				+ "(15,15,15,1,'normal','normal','normal','normal');";
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	class newWindow extends JFrame {
		JButton Okbutton;
		JTextArea inputarea;
		JPanel NewWindowContainer, upper, middle;
		JLabel info, r1, r2, r3, r4, r5, r6, r7, r8;
		JTextField rent_idF, reg_idF, licenseF, rent_comp_idF, periodF, priceF;
		JTextField start_dateF, price_deadlineF;
		int check, company_check;
		int rent_id, reg_id, license, rent_comp_id, period, price;
		String start_date, price_deadline;

		newWindow() {
			setTitle("캠핑카 대여 선택창");

			NewWindowContainer = new JPanel(new GridLayout(2, 1));
			upper = new JPanel();
			middle = new JPanel(new GridLayout(10, 2));
			Container c = getContentPane();
			c.setLayout(new GridLayout(1, 2));

			info = new JLabel("대여 가능 여부가 0이여야 렌트가 가능합니다.");

			r1 = new JLabel("렌트ID (16이상 입력)");
			rent_idF = new JTextField(30);
			r2 = new JLabel("차량등록ID");
			reg_idF = new JTextField(30);
			r3 = new JLabel("면허ID");
			licenseF = new JTextField(30);
			r4 = new JLabel("대여회사 ID");
			rent_comp_idF = new JTextField(30);
			r5 = new JLabel("대여 시작 일");
			start_dateF = new JTextField(30);
			r6 = new JLabel("대여 기간 ");
			periodF = new JTextField(30);

			r8 = new JLabel("지불 기한");
			price_deadlineF = new JTextField(30);

			Okbutton = new JButton("입력");
			Okbutton.addActionListener(new Listener());
			upper.add(info);

			middle.add(r1);
			middle.add(rent_idF);
			middle.add(r2);
			middle.add(reg_idF);
			middle.add(r3);
			middle.add(licenseF);
			middle.add(r4);
			middle.add(rent_comp_idF);
			middle.add(r5);
			middle.add(start_dateF);
			middle.add(r6);
			middle.add(periodF);

			middle.add(r8);
			middle.add(price_deadlineF);

			middle.add(Okbutton);

			NewWindowContainer.add(upper);
			NewWindowContainer.add(middle);
			c.add(NewWindowContainer);
			setSize(500, 500);
			setResizable(false);
			setVisible(true);
		}

		class Listener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				String query;
				String Cars_Rent_update;
				String Cars_updata;
				try {
					stmt = con.createStatement();

					rent_id = Integer.parseInt(rent_idF.getText());
					reg_id = Integer.parseInt(reg_idF.getText());
					license = Integer.parseInt(licenseF.getText());
					rent_comp_id = Integer.parseInt(rent_comp_idF.getText());
					start_date = start_dateF.getText();
					period = Integer.parseInt(periodF.getText());
					price_deadline = price_deadlineF.getText();

					query = "select car_used_check,car_rent_price,car_rent_comp_id from Cars where car_reg_id ="
							+ reg_id + ";";
					rs = stmt.executeQuery(query);
					if (rs.next()) {
						check = rs.getInt(1);
						price = rs.getInt(2);
						company_check = rs.getInt(3);
					}
					Cars_Rent_update = "INSERT INTO Cars_Rent VALUES(" + rent_id + "," + reg_id + "," + license + ","
							+ rent_comp_id + "," + "STR_TO_DATE('" + start_date + "','%Y-%m-%d')," + period + ","
							+ (price * period) + "," + "STR_TO_DATE('" + price_deadline + "','%Y-%m-%d'),1000,1000);";
					Cars_updata = "UPDATE Cars SET car_used_check= 1 WHERE car_reg_id =" + reg_id + ";";
					if (company_check == rent_comp_id) {
						if (check == 0) {
							stmt.executeUpdate(Cars_Rent_update);
							stmt.executeUpdate(Cars_updata);
							JOptionPane.showMessageDialog(null, "입력 성공");
						} else
							JOptionPane.showMessageDialog(null, "이미 대여중인 캠핑카 입니다", "ERROR", JOptionPane.ERROR_MESSAGE);
					} else
						JOptionPane.showMessageDialog(null, "해당 회사차량이 아닙니다", "ERROR", JOptionPane.ERROR_MESSAGE);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DBProject go = new DBProject();
		// DB연결 종료
		go.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println("시스템 종료");
				System.exit(0);
			}
		});
	}

}
