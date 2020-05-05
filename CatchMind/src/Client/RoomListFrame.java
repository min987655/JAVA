package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;

public class RoomListFrame extends JFrame {
	private JPanel MainPanel, RoomListPanel, ButtonPanel;
	private JLabel RoomListLabel;
	private JButton RoomCreateButton, GoRoomButton;
	private JTable RoomListTable;
	private DefaultTableModel dtm;
	private JScrollPane jsp;

	RoomListFrame(Object c) {
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		setBounds(580, 300, 520, 580);

		Object[][] RoomList = {};
		String[] Colume = { "방 제목", "host", "인원" };
		dtm = new DefaultTableModel(RoomList, Colume);

		MainPanel = new JPanel();
		RoomListPanel = new JPanel();
		ButtonPanel = new JPanel();

		RoomListLabel = new JLabel("Room List");

		RoomCreateButton = new JButton("방생성");
		GoRoomButton = new JButton("방입장");
		RoomCreateButton.addActionListener((ActionListener) c);
		GoRoomButton.addActionListener((ActionListener) c);
		RoomListTable = new JTable(dtm);
		jsp = new JScrollPane(RoomListTable);

		add(MainPanel);
		MainPanel.add(RoomListPanel);
		MainPanel.add(ButtonPanel);
		MainPanel.setLayout(null);
		RoomListPanel.setBounds(0, 0, 500, 500);
		ButtonPanel.setBounds(0, 500, 500, 50);

		RoomListPanel.setLayout(new BorderLayout());
		RoomListPanel.add(RoomListLabel, BorderLayout.NORTH);
		RoomListPanel.add(jsp, BorderLayout.CENTER);

		ButtonPanel.setLayout(new FlowLayout());
		ButtonPanel.add(RoomCreateButton);
		ButtonPanel.add(GoRoomButton);
	}

	public void setRoomList(String s) {
		String[] addrow = s.split("/");
		dtm.addRow(addrow);
	}

	public String getRoomName() {
		int x = RoomListTable.getSelectedRow();
		String s = (String) RoomListTable.getValueAt(x, 1);
		return s;
	}

	public DefaultTableModel getTbModel() {
		return dtm;
	}

	public String getroomcreatebuttonactioncommend() {
		return RoomCreateButton.getActionCommand();
	}

	public String getGoRoomButtonactioncommend() {
		return GoRoomButton.getActionCommand();
	}

	public void RoomListUpdate(String s) {
		String[] g = s.split("/");
		boolean j = false;
		int d = dtm.getRowCount();
		if (d != 0) {
			for (int i = 0; i < d; i++) {
				String h = String.valueOf(dtm.getValueAt(i, 0));
				if (h.equals(g[0])) {
					dtm.removeRow(i);
					dtm.addRow(g);
					j = true;
					break;
				}
			}
		}
		if (j == false || d == 0) {
			dtm.addRow(g);
		}
	}

	public void RoomListRemove(String s) {
		String name = s;
		int h = dtm.getRowCount();
		for (int i = 0; i < h; i++) {
			System.out.println(s);
			String g = String.valueOf(dtm.getValueAt(i, 0));
			if (g.equals(s)) {
				dtm.removeRow(i);
				break;
			}
		}
	}
}