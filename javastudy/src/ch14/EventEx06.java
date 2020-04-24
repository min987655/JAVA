package ch14;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class EventEx06 extends JFrame {

	int imgX = 280;
	int imgY = 90;

	public EventEx06() {
		setTitle("��ǳ ����");
		setSize(800, 350);
		setLocationRelativeTo(null); // �������� ȭ�� ��� ��ġ
		setContentPane(new MyPanel());
		setVisible(true);
	}

	// paintComponent �Լ��� Frame �ȿ��� �۵�����.
	class MyPanel extends JPanel {

		public MyPanel() {
			setFocusable(true); // ��Ŀ���� �� Panel�� ��(��Ŀ���� ���
			this.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					System.out.println("keychar : " + e.getKeyChar());
					System.out.println("keycode : " + e.getKeyCode());
					switch (e.getKeyCode()) {
					case KeyEvent.VK_LEFT: // ����Ű ������ Ŭ���ϸ�
						imgX = imgX - 10;
						break;
					case KeyEvent.VK_RIGHT: // �������� Ŭ���ϸ�
						imgX = imgX + 10;
						break;
//					default: // ������ ����
//						break;

					case KeyEvent.VK_SPACE:
						new Thread(new Runnable() {

							@Override
							public void run() {
								int time = 25;
								while (time > 0) {
									imgX = imgX + 10;
									time--;
									try {
										Thread.sleep(100);
									} catch (InterruptedException e1) {
										e1.printStackTrace();
									}
									repaint();
								}
							}
						}).start();
						break;
					}
					repaint();
				}
			});
		}

		@Override
		protected void paintComponent(Graphics g) { // �� �ʿ� ����. setLotion, setText���� ���� �ڵ� repaint() ��
			super.paintComponent(g);

			ImageIcon imgIce = new ImageIcon("img/1.png");
			ImageIcon imgPa = new ImageIcon("img/2.png");

			Image ice = imgIce.getImage();
			Image pa = imgPa.getImage();

			g.drawImage(ice, 0, 0, null);
			g.drawImage(pa, imgX, imgY, null);
		}
	}

	public static void main(String[] args) {
		new EventEx06();
	}

}