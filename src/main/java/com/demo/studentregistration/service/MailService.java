package com.demo.studentregistration.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;

import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.demo.studentregistration.dto.EmailDTO;
import com.demo.studentregistration.model.Student;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MailService {

	private final JavaMailSender javaMailSender;
	private final StudentService studentService;

	String[] xlxTitle = new String[] { "No.", "Name", "Father Name", "Date Of Birth", "Contact Number", "Email",
			"Address" };

	public void sendEmail(EmailDTO mail) {
		System.out.println(" sendEmail ... ");

		SimpleMailMessage msg = new SimpleMailMessage();

		msg.setTo(mail.getTo());
		msg.setFrom(mail.getFrom());
		msg.setSubject(mail.getSubject());
		msg.setText(mail.getContent());

		javaMailSender.send(msg);

	}

	public void sendEmailWithAttachment(EmailDTO mail) throws MessagingException, IOException {

		System.out.println(" ... sendEmailWithAttachment ... ");

//		byte[] bytes = mail.getAttachInputStreamSource(); // str.getBytes(StandardCharsets.UTF_8);

		FileInputStream in = new FileInputStream(
				new File("C:\\Users\\khinezinmyint\\Downloads\\review and submit issue.xlsx"));
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		IOUtils.copy(in, out);
		IOUtils.closeQuietly(in);
		IOUtils.closeQuietly(out);
		byte[] bytes = out.toByteArray();
		InputStream inputStream = new ByteArrayInputStream(bytes);

		MimeMessage msg = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(msg, true);

		helper.setTo(mail.getTo());
		helper.setFrom(mail.getFrom());
		helper.setSubject(mail.getSubject());
		helper.setText(mail.getContent());
//		helper.addAttachment(mail.getAttachName(), new InputStreamResource(inputStream));
		helper.addAttachment(mail.getAttachName(), writeExcel());

		javaMailSender.send(msg);

		System.out.println("successfully send email ... | " + mail.toString());

	}

	private DataSource writeExcel() {

		Workbook xlsFile = new HSSFWorkbook();
		CreationHelper helper = xlsFile.getCreationHelper();
		Sheet sheet1 = xlsFile.createSheet("Sheet #1");

		List<Student> studentList = studentService.findAll();

		int rowCount = 0;
		int indexNo = 1;

		// header
		System.out.println("Excel : write header ");

		createHeaderRow(sheet1);

		for (Student student : studentList) {

			System.out.println("Excel : write row " + rowCount);

			Row row = sheet1.createRow(++rowCount);
			writeBook(student, row, indexNo);

			indexNo++;

		}

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			xlsFile.write(bos);
			System.out.println(" bos ... " + bos);
		} catch (IOException e) {
			e.printStackTrace();
		}

		DataSource fds = new ByteArrayDataSource(bos.toByteArray(), "application/vnd.ms-excel");
		System.out.println(" DataSource ... " + fds);

		return fds;
	}

	private void createHeaderRow(Sheet sheet) {

		CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
		Font font = sheet.getWorkbook().createFont();
		font.setFontHeightInPoints((short) 16);
		cellStyle.setFont(font);

		Row row = sheet.createRow(0);

		for (int i = 1; i <= xlxTitle.length; i++) {
			Cell cellTitle = row.createCell(i);
			cellTitle.setCellStyle(cellStyle);
			cellTitle.setCellValue(xlxTitle[i - 1]);
		}

	}

	private void writeBook(Student student, Row row, int rowCount) {

		Cell cell = row.createCell(1);
		cell.setCellValue(String.valueOf(rowCount));

		cell = row.createCell(2);
		cell.setCellValue(Objects.isNull(student.getName()) ? "" : student.getName());

		cell = row.createCell(3);
		cell.setCellValue(Objects.isNull(student.getFathername()) ? "" : student.getFathername());

		cell = row.createCell(4);
		cell.setCellValue(Objects.isNull(student.getDob()) ? "" : student.getDob());

		cell = row.createCell(5);
		cell.setCellValue(Objects.isNull(student.getContactNo()) ? "" : student.getContactNo());

		cell = row.createCell(6);
		cell.setCellValue(Objects.isNull(student.getEmail()) ? "" : student.getEmail());

		cell = row.createCell(7);
		cell.setCellValue(Objects.isNull(student.getAddress()) ? "" : student.getAddress());

	}

}
